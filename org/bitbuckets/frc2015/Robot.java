package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.autonomous.AutoCanMove;
import org.bitbuckets.frc2015.autonomous.AutoDriveTest;
import org.bitbuckets.frc2015.autonomous.DriveToAutoZone;
import org.bitbuckets.frc2015.autonomous.ThreeTotePickupAutoMode;
import org.bitbuckets.frc2015.command.*;
import org.bitbuckets.frc2015.subsystems.Drivey;
import org.bitbuckets.frc2015.subsystems.Grabby;
import org.bitbuckets.frc2015.subsystems.Stacky;
import org.bitbuckets.frc2015.subsystems.Tilty;
import org.bitbuckets.frc2015.util.SerialPortManager;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static OI oi;
    public static Drivey drivey;
    public static Grabby grabby;
    public static Stacky stacky;
    public static Tilty tilty;

    public static PowerDistributionPanel pdp;
    private static Compressor compressor;

    private Command autonomousCommand;

    public static SendableChooser autoChooser = new SendableChooser();

    private StackyUp upOne;
    private StackyDown downOne;
    private StackyDownAll downAll;
    private StackyMoveDistance downBit;


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();
        drivey = new Drivey();
        grabby = new Grabby();
        stacky = new Stacky();
        tilty = new Tilty();

        SerialPortManager.init();

        pdp = new PowerDistributionPanel();
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);

        ///////////////////COMMANDS////////////////
//        ChangeDriveMode driveMode = new ChangeDriveMode();

        GrabbyOpen grabbyOpen = new GrabbyOpen();
        GrabbyClose grabbyClose = new GrabbyClose();

        TiltUp tiltUp = new TiltUp();
        TiltDown tiltDown = new TiltDown();

        upOne = new StackyUp();
        downOne = new StackyDown();
        downAll = new StackyDownAll();
        downBit = new StackyMoveDistance(-0.5);

        ///////////////////ASSIGNMENTS/////////////
        oi.operatorGrabOpen.whenPressed(grabbyOpen);
        oi.operatorGrabClose.whenPressed(grabbyClose);

        oi.operatorTiltUp.whenActive(tiltUp);
        oi.operatorTiltDown.whenActive(tiltDown);

//        oi.operatorToteUp.whenActive(upOne);
        oi.operatorToteDown.whenActive(downOne);
        oi.operatorToteDownAll.whenPressed(downAll);
        oi.operatorToteDownBit.whenPressed(downBit);
        ///////////////////////////////////////////
        //FileManager.fetchFiles();

        //ConstantsManager.fetchConstants();

        //generate a list of autonomous programs based on all the .txt files in the local directory
        //TODO make some sort of tag at start of scripts required, so that auto scripts, constant files, etc. don't get confused
        //AutoProgramGenerator.generateAutoPrograms();

        //autonomousCommand = new AutoDriveTest();
        autoChooser.addDefault("Three Tote", new ThreeTotePickupAutoMode());
        autoChooser.addObject("Drive to AutoZone", new DriveToAutoZone());
        autoChooser.addObject("Drive Test", new AutoDriveTest());
        autoChooser.addObject("Take Can", new AutoCanMove());
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {

    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        // schedule the autonomous command (example)
        drivey.resetEncoders();
        drivey.setEncoderSetting(ControlMode.Position);
        SerialPortManager.analogGyro.reset();

        autonomousCommand = (Command) autoChooser.getSelected();
        //autonomousCommand = (Command) new AutoDriveTest();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        //Resets driving encoders to 0
        drivey.resetEncoders();
        //Sets the ControlMode of the drive controllers
        drivey.setEncoderSetting(ControlMode.Speed);
        if(autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        SerialPortManager.analogGyro.reset();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        if(RandomConstants.TESTING) {
            drivey.resetPIDs();
        }

        ////////////////////////DRIVING//////////////////////////////
        double theta = Math.atan2(oi.driver.getRawAxis(OI.GO), oi.driver.getRawAxis(OI.STRAFE));
        double radius = Math.hypot(oi.driver.getRawAxis(OI.GO), oi.driver.getRawAxis(OI.STRAFE));
        double sqrRadius = deadzone(Math.pow(radius, 1));
        drivey.drive(sqrRadius * Math.cos(theta) * RandomConstants.MAX_TRANS_SPEED, -1 * sqrRadius * Math.sin(theta) * RandomConstants.MAX_TRANS_SPEED, Math.pow(oi.driver.getRawAxis(OI.TURN), 1) * RandomConstants.MAX_ROT_SPEED);
        /////////////////////////////////////////////////////////////

//        //moving -> not moving
//        if(oi.operator.getRawAxis(OI.LIFT) == 0          &&   grabby.getLifterController().getControlMode() == ControlMode.Speed){
//        	grabby.getLifterController().changeControlMode(ControlMode.Position);
//        	grabby.getLifterController().set(grabby.getLifterController().get());
//        //not moving -> moving
//        } else if(oi.operator.getRawAxis(OI.LIFT) != 0   &&   grabby.getLifterController().getControlMode() == ControlMode.Position){
//        	grabby.getLifterController().changeControlMode(ControlMode.Speed);
//        	grabby.setLifterMotor(-1 * oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
//        //moving
//        } else if(oi.operator.getRawAxis(OI.LIFT) != 0){
//        	grabby.setLifterMotor(-1 * oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
//        //not moving
//        } else{}
    	grabby.setLifterMotor(-1 * oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);

        //***/*/*/*/*/*///*/*///HACK
        if (oi.operatorToteUp.get() && stacky.getButtonsActive() && !upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
            upOne.start();
        }
        if (!upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
            stacky.setWinchMotor(oi.operator.getRawAxis(3) - oi.operator.getRawAxis(4));
        }
        //*/*////*/*/*///

        if(RandomConstants.TESTING) {
            stacky.printStuff();
        }

        SmartDashboard.putData(Scheduler.getInstance());

        if (RandomConstants.TESTING) {
            SmartDashboard.putBoolean("Limit Top", stacky.getLimitTop());
            SmartDashboard.putBoolean("Limit Bottom", stacky.getLimitBottom());
            SmartDashboard.putBoolean("Reed Above", stacky.getReedAbove());
            SmartDashboard.putBoolean("Reed Below", stacky.getReedBelow());
            SmartDashboard.putNumber("Gyro heading", SerialPortManager.getHeading());
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    //*/*//*/*//*/*/*/*/*//*/*/Hck
    public double deadzone(double thing) {
        return Math.abs(thing) < RandomConstants.DEADZONE ? 0 : thing;
    }
    //*///*/**/**/*/*/*/*/*/
}

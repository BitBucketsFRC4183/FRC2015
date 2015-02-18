package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.command.*;
import org.bitbuckets.frc2015.subsystems.Drivey;
import org.bitbuckets.frc2015.subsystems.Grabby;
import org.bitbuckets.frc2015.subsystems.Stacky;
import org.bitbuckets.frc2015.subsystems.Tilty;


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

        pdp = new PowerDistributionPanel();
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);
        // instantiate the command used for the autonomous period

        OpenGrabber openGrabber = new OpenGrabber();
        CloseGrabber closeGrabber = new CloseGrabber();
        TiltUp tiltUp = new TiltUp();
        TiltDown tiltDown = new TiltDown();
        ChangeDriveMode driveMode = new ChangeDriveMode();

        upOne = new StackyUp();
        downOne = new StackyDown();
        downAll = new StackyDownAll();

        SmartDashboardInit();

        //FileManager.fetchFiles();

        //ConstantsManager.fetchConstants();

        //generate a list of autonomous programs based on all the .txt files in the local directory
        //TODO make some sort of tag at start of scripts required, so that auto scripts, constant files, etc. don't get confused
        //AutoProgramGenerator.generateAutoPrograms();

        oi.tiltUp.whenActive(tiltUp);
        oi.tiltDown.whenActive(tiltDown);
        oi.changeControl.whenPressed(driveMode);
//        oi.driverTriangBut.whenPressed(upOne);
        oi.driverXBut.whenPressed(downAll);
//        oi.operatorTriangBut.whenPressed(upOne);
//        oi.operatorXBut.whenPressed(downAll);

        oi.operatorToteDown.whenPressed(downOne);
        oi.operatorToteDownAll.whenPressed(downAll);
        oi.operatorTiltUp.whenPressed(tiltUp);
        oi.operatorTiltDown.whenPressed(tiltDown);
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
        autonomousCommand = (Command) autoChooser.getSelected();
        autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        drivey.resetEncoders();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();

        drivey.resetPIDs();
        double theta = Math.atan2(oi.driver.getRawAxis(OI.GO), oi.driver.getRawAxis(OI.STRAFE));
        double radius = Math.hypot(oi.driver.getRawAxis(OI.GO), oi.driver.getRawAxis(OI.STRAFE));
        double sqrRadius = Math.pow(radius, 2);
        drivey.drive(sqrRadius * Math.cos(theta) * RandomConstants.MAX_TRANS_SPEED, -1 * sqrRadius * Math.sin(theta) * RandomConstants.MAX_TRANS_SPEED, Math.pow(oi.driver.getRawAxis(OI.TURN), 2) * RandomConstants.MAX_ROT_SPEED);

//        if (!(downAll.isRunning() || upOne.isRunning())) {
//            if (stacky.getLimitBottom()) {
//                stacky.setWinchMotor(oi.driver.getRawAxis(3));
//            } else if (stacky.getLimitTop()) {
//                stacky.setWinchMotor(0 - oi.driver.getRawAxis(2));
//            } else {
//                stacky.setWinchMotor(oi.driver.getRawAxis(3) - oi.driver.getRawAxis(2));
//            }
//        }

        //***/*//*/*//*/*/*HACK
        if(!upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
            stacky.setWinchMotor(oi.driver.getRawAxis(3) - oi.driver.getRawAxis(4));
        }
        //**/*/**///*/*//*/*/*/*/*/*/*//*/*/*

        ///*/*/*///*/*/*/**/*/*/HACK
        if(oi.grabberUp.get()){
            grabby.setLifterMotor(.5);
        }else if(oi.grabberDown.get()){
            grabby.setLifterMotor(-.5);
        }else{
            grabby.setLifterMotor(0);
        }

        if(oi.grabOpen.get()){
            grabby.setGrabMotor(.5);
        }else if(oi.grabClose.get()){
            grabby.setGrabMotor(-.5);
        }else{
            grabby.setGrabMotor(0);
        }
        //*/*/*/**//*/*/*///*/*//*/*/*/*

        //***/*/*/*/*/*///*/*///HACK
        if (oi.driverTriangBut.get() && stacky.getButtonsActive() && !upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
            upOne.start();
        }
        //*/*////*/*/*///

        SmartDashboard.putData(Scheduler.getInstance());
        SmartDashboard.putBoolean("Limit Top", stacky.getLimitTop());
        SmartDashboard.putBoolean("Limit Bottom", stacky.getLimitBottom());
        SmartDashboard.putBoolean("Reed Above", stacky.getReedAbove());
        SmartDashboard.putBoolean("Reed Below", stacky.getReedBelow());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    public void SmartDashboardInit() {
        SmartDashboard.putString("test", "This is a test!");
        SmartDashboard.putData(Scheduler.getInstance());
        //SmartDashboard.putNumber("Speed", drivey.getSpeed());
        //SmartDashboard.putNumber("Tilty Angle", tilty.getAngle());
        //SmartDashboard.put
    }

    //*/*//*/*//*/*/*/*/*//*/*/Hck
    public double deadband(double thing) {
        return Math.abs(thing) < .1 ? 0 : thing;
    }
    //*///*/**/**/*/*/*/*/*/
}

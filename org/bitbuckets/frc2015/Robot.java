package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.autonomous.AutoDriveTest;
import org.bitbuckets.frc2015.autonomous.AutoProgram;
import org.bitbuckets.frc2015.command.*;
import org.bitbuckets.frc2015.subsystems.*;

import java.util.ArrayList;


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

    private static Compressor compressor;

    private SendableChooser autoChooser;

    private AutoDriveTest driveTest;

    private ArrayList<AutoProgram> autoPrograms;

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


        //generate a list of autonomous programs based on all the .txt files in the local directory
        //TODO make some sort of tag at start of scripts required, so that auto scripts, constant files, etc. don't get confused
//        try {
//			autoPrograms = AutoProgramGenerator.generateAutoPrograms();
//		} catch (IOException e) {
//			e.printStackTrace();
//			SmartDashboard.putString("Auto IO Error", "Error detected: " + e.getMessage());
//		}
//
//        autoChooser = new SendableChooser();
//        for(AutoProgram a: autoPrograms){
//        	autoChooser.addObject(a.title, a);
//        }


        oi.tiltUp.whenActive(tiltUp);
        oi.tiltDown.whenActive(tiltDown);
        oi.changeControl.whenPressed(driveMode);
        oi.driverTriangBut.whenPressed(upOne);
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
        driveTest = new AutoDriveTest();
        driveTest.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (driveTest != null) {
            driveTest.cancel();
        }
        drivey.resetEncoders();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drivey.resetPIDs();
        drivey.drive(oi.driver.getRawAxis(OI.STRAFE) * RandomConstants.MAX_TRANS_SPEED, -oi.driver.getRawAxis(OI.GO) * RandomConstants.MAX_TRANS_SPEED, oi.driver.getRawAxis(OI.TURN) * RandomConstants.MAX_ROT_SPEED);

        if (!(downAll.isRunning() || upOne.isRunning())) {
            if (stacky.getLimitBottom()) {
                stacky.setWinchMotor(oi.driver.getRawAxis(3));
            } else if (stacky.getLimitTop()) {
                stacky.setWinchMotor(0 - oi.driver.getRawAxis(2));
            } else {
                stacky.setWinchMotor(oi.driver.getRawAxis(3) - oi.driver.getRawAxis(2));
            }
        }

        //***/*/*/*/*/*///*/*///HACK
        if(oi.operatorToteUp.get() && stacky.getButtonsActive() && !upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()){
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
}

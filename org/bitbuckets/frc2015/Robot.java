package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.command.*;
import org.bitbuckets.frc2015.subsystems.*;

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
    public static Esteemy esteemy;
    public static Grabby grabby;
    public static Stacky stacky;
    public static Tilty tilty;

    public static Compressor compressor;

    private StackyUp upOne;
    private StackyDown downAll;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();
        drivey = new Drivey();
        esteemy = new Esteemy();
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
        IncreaseStack increaseStack = new IncreaseStack();

        upOne = new StackyUp();
        downAll = new StackyDown();

        SmartDashboardInit();

        oi.tiltUp.whenActive(tiltUp);
        oi.tiltDown.whenActive(tiltDown);
        oi.triangBut.whenPressed(upOne);
        oi.xBut.whenPressed(downAll);
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
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drivey.drive(oi.stick.getRawAxis(OI.GO) * RandomConstants.MAX_TRANS_SPEED, -oi.stick.getRawAxis(OI.STRAFE) * RandomConstants.MAX_TRANS_SPEED, oi.stick.getRawAxis(OI.TURN) * RandomConstants.MAX_ROT_SPEED);

        if (!(downAll.isRunning() || upOne.isRunning())) {
            if (stacky.getLimitBottom()) {
                stacky.setWinchMotor(oi.stick.getRawAxis(3));
            } else if (stacky.getLimitTop()) {
                stacky.setWinchMotor(0 - oi.stick.getRawAxis(2));
            } else {
                stacky.setWinchMotor(oi.stick.getRawAxis(3) - oi.stick.getRawAxis(2));
            }
        }

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

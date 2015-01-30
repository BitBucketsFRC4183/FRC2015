package org.bitbuckets.frc2015;

import java.io.IOException;
import java.util.ArrayList;

import org.bitbuckets.frc2015.autonomous.AutoProgram;
import org.bitbuckets.frc2015.autonomous.AutoProgramGenerator;
import org.bitbuckets.frc2015.command.CloseGrabber;
import org.bitbuckets.frc2015.command.OpenGrabber;
import org.bitbuckets.frc2015.subsystems.Drivey;
import org.bitbuckets.frc2015.subsystems.Esteemy;
import org.bitbuckets.frc2015.subsystems.Grabby;
import org.bitbuckets.frc2015.subsystems.Stacky;
import org.bitbuckets.frc2015.subsystems.Tilty;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
    
    Command autonomousCommand;

    SendableChooser autoChooser;
    
    ArrayList<AutoProgram> autoPrograms;

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

        SmartDashboardInit();
        
        oi.trigger.whenPressed(openGrabber);
        oi.trigger.whenReleased(closeGrabber);
        
        //generate a list of autonomous programs based on all the .txt files in the local directory
        //TODO make some sort of tag at start of scripts required, so that auto scripts, constant files, etc. dont get confused
        try {
			autoPrograms = AutoProgramGenerator.generateAutoPrograms();
		} catch (IOException e) {
			e.printStackTrace();
			SmartDashboard.putString("Auto IO Error", "Error detected: " + e.getMessage());
		}
        
        autoChooser = new SendableChooser();
        for(AutoProgram a: autoPrograms){
        	autoChooser.addObject(a.title, a);
        }
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
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        drivey.drive(oi.stick.getY(), oi.stick.getX());
        grabby.setSpeed(oi.operatorStick.getY());
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    public void SmartDashboardInit(){
        SmartDashboard.putString("test", "This is a test!");
        SmartDashboard.putData(Scheduler.getInstance());
        //SmartDashboard.putNumber("Speed", drivey.getSpeed());
        //SmartDashboard.putNumber("Tilty Angle", tilty.getAngle());
        //SmartDashboard.put
    }
}

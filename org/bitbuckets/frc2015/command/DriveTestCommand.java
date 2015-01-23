package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTestCommand extends Command {
	
	private Timer timer;
	private boolean isDone;

    public DriveTestCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.drivey);
        timer = new Timer();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	isDone=false;
    	timer.reset();
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get()<3.0){
    		Robot.drivey.drive(0.1, 0);
    	} else if(timer.get()<6.0){
    		Robot.drivey.drive(-0.1, 0);
    	} else if(timer.get()<9.0){
    		Robot.drivey.drive(0.0, 0.5);
    	} else if(timer.get()<12.0){
    		Robot.drivey.drive(0.0, -0.5);
    	} else{
    		Robot.drivey.drive(0.0, 0.0);
    		isDone = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isDone;
    }

    // Called once after isFinished returns true
    protected void end() {
    	timer.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

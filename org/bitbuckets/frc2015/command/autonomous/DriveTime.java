package org.bitbuckets.frc2015.command.autonomous;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command {
	private long timeInit;
	
	
    public DriveTime() {
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivey.drive(1, 0, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - timeInit) >= 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

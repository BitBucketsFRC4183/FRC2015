package org.bitbuckets.frc2015.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class DriveTime extends Command {
	/**
	 * In milliseconds
	 */
    private long timeInit;
    /**
     * In seconds
     */
    private long time;

    public DriveTime(long time) {
        requires(Robot.drivey);
        this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timeInit = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivey.drive(0, 1, 0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - timeInit) >= time*1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivey.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivey.drive(0,  0,  0);
    }
}

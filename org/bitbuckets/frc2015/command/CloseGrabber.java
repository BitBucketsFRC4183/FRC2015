package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class CloseGrabber extends Command {

    public CloseGrabber() {
        requires(Robot.grabby);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.grabby.setGrabby(true);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

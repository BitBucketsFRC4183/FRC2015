package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;


public class TiltGrabberUp extends Command {
	


    public TiltGrabberUp(int presetPosition) {
        requires(Robot.grabby);

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.grabby.setSpeed(1.0);
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

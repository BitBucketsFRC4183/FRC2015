package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StackyDownAll extends Command {

    public StackyDownAll() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.stacky);
        requires(Robot.tilty);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// Set the entire stack down
    	Robot.stacky.setWinchMotor(-5.0);
    	// Set tilty down
    	Robot.tilty.setTiltyUp(false);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	 Stop winch
    	Robot.stacky.setWinchMotor(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

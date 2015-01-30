package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StackyUp extends Command {

    public StackyUp() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.stacky);
        requires(Robot.tilty);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.stacky.setWinchMotor(1.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(Robot.stacky.getHallSingActive()){
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.stacky.setWinchMotor(0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

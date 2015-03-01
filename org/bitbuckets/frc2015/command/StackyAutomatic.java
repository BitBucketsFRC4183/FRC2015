package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.subsystems.Stacky;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StackyAutomatic extends Command {
	
	private StackyUp upOne;

    public StackyAutomatic() {
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Stacky.automaticStacky = true;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.stacky.getButtonsActive() && !upOne.isRunning()){
    		upOne.start();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Stacky.automaticStacky;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
    
}
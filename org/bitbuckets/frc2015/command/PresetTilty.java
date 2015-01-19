package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;

/**
 * 
 */
public class PresetTilty extends Command {
	
	/**
	 * 1: Tote set down position
	 * 2: Tote pick up position
	 * 3: Upright can pick up position
	 * 4: Fallen can pick up position
	 */
	int preset;
	
    public PresetTilty(int presetPosition) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.grabby);
    	preset = presetPosition;
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }
    
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

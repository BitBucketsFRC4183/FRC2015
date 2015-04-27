package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.subsystems.Shooty.ShootState;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShooterRetract extends Command {

    public ShooterRetract() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.shooty);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooty.setState(ShootState.RETRACTING);
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

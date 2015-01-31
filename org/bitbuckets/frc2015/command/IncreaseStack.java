package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class IncreaseStack extends Command {
    private long timeInit;

    public IncreaseStack() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.stacky);
        timeInit = System.currentTimeMillis();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.stacky.setWinchMotor(1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis()-timeInit >= 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.stacky.setWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

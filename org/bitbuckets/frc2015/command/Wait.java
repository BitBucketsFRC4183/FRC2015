package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Wait extends Command {
    private long timeInit;
    private double length;

    public Wait(double waitTime) {
        length = waitTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timeInit = System.currentTimeMillis();
        System.out.println("Waiting for: " + length + " seconds.");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (System.currentTimeMillis() - timeInit) >= length * 1000;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

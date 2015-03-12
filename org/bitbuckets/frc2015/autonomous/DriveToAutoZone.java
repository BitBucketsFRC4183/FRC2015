package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by Alia on 3/7/15.
 */
public class DriveToAutoZone extends Command {
    private long timeInit;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public DriveToAutoZone() {
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        timeInit = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        Robot.drivey.drive(0, 2, 0);
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return Math.abs(System.currentTimeMillis() - timeInit) >= 3000;
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
    }
}

package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.OI;
//TODO Fix Javadocs

/**
 * Created by James on 2/12/2015.
 */
public class ChangeDriveMode extends Command {
    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public ChangeDriveMode() {
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        OI.changeControls();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return true;
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

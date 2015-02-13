package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/13/2015.
 */
public class CloseGrabber extends Command {
    private long timeInit;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public CloseGrabber() {
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
        Robot.grabby.setGrabMotor(RandomConstants.GRAB_SPEED);
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return Robot.grabby.getCurrentFinised() || (System.currentTimeMillis() - timeInit)/1000 >= RandomConstants.GRAB_TIMEOUT;
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        Robot.grabby.setGrabMotor(0);
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
    }
}

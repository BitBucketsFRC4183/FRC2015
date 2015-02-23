package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/16/2015.
 */
public class StackyMoveDistance extends Command {
    private double set;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     *
     * TODO Make sure it's the right direction
     */
    public StackyMoveDistance(double distance) {
        set = (Robot.stacky.getDistanceUp() - distance) * RandomConstants.ENC_TICK_PER_REV / RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE;
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        Robot.stacky.setClosedLoop(true);
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        Robot.stacky.setWinchPosition(set);
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

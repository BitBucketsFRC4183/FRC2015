package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/12/2015.
 */
public class TiltUp extends Command {
    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public TiltUp() {
        requires(Robot.tilty);
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        Robot.tilty.setTiltyUp(true);
        SmartDashboard.putString("Tilty", "Up");
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

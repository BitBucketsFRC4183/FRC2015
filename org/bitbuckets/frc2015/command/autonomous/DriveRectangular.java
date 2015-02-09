package org.bitbuckets.frc2015.command.autonomous;

//TODO Fix Javadocs

/**
 * This {@link edu.wpi.first.wpilibj.command.Command} is a wrapper for {@link org.bitbuckets.frc2015.command.autonomous.DrivePolar}
 * to let the user make the robot move in a rectangular coordinate system.
 * <p/>
 * Created by James on 2/9/2015.
 */
public class DriveRectangular extends DrivePolar {
    /**
     * This calls the {@link org.bitbuckets.frc2015.command.autonomous.DrivePolar} constructor with polar coordinates.
     * The calculations happen inline because the <code>super()</code> constructor needs to be called int the first line.
     *
     * @param xTarget The target X distance for the robot to move.
     * @param yTarget The target Y distance for the robot to move.
     */
    public DriveRectangular(double xTarget, double yTarget) {
        super(Math.sqrt(Math.pow(xTarget, 2) + Math.pow(yTarget, 2)), Math.atan2(xTarget, yTarget));
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        super.initialize();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        super.execute();
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return super.isFinished();
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        super.end();
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
        super.interrupted();
    }
}

package org.bitbuckets.frc2015.command.autonomous;

//TODO Fix Javadocs

/**
 * This is a wrapper class for {@link org.bitbuckets.frc2015.command.autonomous.DriveTranslation} so that you can use
 * polar coordinates to control the autonomous translational driving of the robot.
 *
 * Created by James on 2/6/2015.
 */
public class DrivePolar extends DriveTranslation {
    /**
     * Converts polar coordinates to rectangular coordinates and passes it into the superclass constructor.
     */
    public DrivePolar(double radius, double angle) {
        super(radius * Math.cos(angle), radius * Math.sin(angle));
    }

    /**
     * Called just before this Command runs the first time. Calls the superclasses <code>initialize()</code> method.
     */
    protected void initialize() {
        super.initialize();
    }

    /**
     * Called repeatedly when this Command is scheduled to run. Calls the superclasses <code>execute()</code> method.
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

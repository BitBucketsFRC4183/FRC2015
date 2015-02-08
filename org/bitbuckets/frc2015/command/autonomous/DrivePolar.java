package org.bitbuckets.frc2015.command.autonomous;

//TODO Fix Javadocs

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.control.TrapezoidalMotionProfiler;

/**
 * This is a wrapper class for {@link org.bitbuckets.frc2015.command.autonomous.DriveTranslation} so that you can use
 * polar coordinates to control the autonomous translational driving of the robot.
 * <p/>
 * Created by James on 2/6/2015.
 */
public class DrivePolar extends Command {
    private double distance;
    private double theta;
    private TrapezoidalMotionProfiler profiler;
    private long time;

    /**
     * Converts polar coordinates to rectangular coordinates and passes it into the superclass constructor.
     */
    public DrivePolar(double radius, double angle) {
        requires(Robot.drivey);

        profiler = new TrapezoidalMotionProfiler(radius, RandomConstants.MAX_TRANS_SPEED, RandomConstants.MAX_TRANS_ACCEL);
        theta = angle;
        distance = 0;
    }

    /**
     * Called just before this Command runs the first time. Calls the superclasses <code>initialize()</code> method.
     */
    protected void initialize() {
        profiler.start();
        time = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run. Calls the superclasses <code>execute()</code> method.
     */
    protected void execute() {
        double velocity = profiler.update(distance);

        distance += velocity * (System.currentTimeMillis() - time) / 1000;

        SmartDashboard.putNumber("Autonomous velocity", velocity);
        SmartDashboard.putNumber("Autonomous position", distance);

        Robot.drivey.drive(velocity * Math.cos(theta), velocity * Math.sin(theta), 0);

        time = System.currentTimeMillis();
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return profiler.getFinished();
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        Robot.drivey.drive(0, 0, 0);
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {

    }
}

package org.bitbuckets.frc2015.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.control.TrapezoidalMotionProfiler;

/**
 * This {@link edu.wpi.first.wpilibj.command.Command} tells the robot to move a radius at angle theta.
 * <p/>
 * Created by James on 2/6/2015.
 */
public class DrivePolar extends Command {
    private double distance;
    private double theta;
    private TrapezoidalMotionProfiler profiler;
    private long time;

    /**
     * Calls the main constructor with the max velocity of the robot as the max velocity of this command.
     *
     * @param radius The distance to move the robot.
     * @param angle  The angle at which the robot should move.
     */
    public DrivePolar(double radius, double angle) {
        this(radius, angle, RandomConstants.MAX_TRANS_SPEED);
    }

    /**
     * Initializes all the variables except time.
     *
     * @param radius The distance to move the robot.
     * @param angle  The angle at which the robot should move.
     * @param maxVel The maximum velocity for the robot to run.
     */
    public DrivePolar(double radius, double angle, double maxVel) {
        requires(Robot.drivey);

        maxVel = Math.abs(maxVel);

        if(maxVel > RandomConstants.MAX_TRANS_SPEED){
            maxVel = RandomConstants.MAX_TRANS_SPEED;
            SmartDashboard.putString("Maximum tran speed", "is too high");
        }

        profiler = new TrapezoidalMotionProfiler(radius, maxVel, RandomConstants.MAX_TRANS_ACCEL);
        theta = angle;
        distance = 0;
    }

    /**
     * Initializes the time and starts the profiler.
     */
    protected void initialize() {
        profiler.start();
        time = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run. Calculates the velocity from the profiler, updates the
     * position, and calls the robots <code>drive()</code> method.
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
     *
     * @return Whether the profiler is finished.
     */
    protected boolean isFinished() {
        return profiler.getFinished();
    }

    /**
     * Called once after <code>isFinished()</code> returns true. Stops the robot.
     */
    protected void end() {
        Robot.drivey.drive(0, 0, 0);
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run. Stops the robot.
     */
    protected void interrupted() {
        Robot.drivey.drive(0, 0, 0);
    }
}
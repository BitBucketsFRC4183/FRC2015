package org.bitbuckets.frc2015.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.control.TrapezoidalMotionProfiler;
//TODO Fix Javadocs

/**
 * Created by James on 2/11/2015.
 */
public class DriveRotate extends Command {
    private double theta;
    private TrapezoidalMotionProfiler profiler;
    private long time;

    /**
     * Calls the main constructor with the max rotational velocity of the robot as the max velocity of this command.
     *
     * @param angle The angle to turn the robot.
     */
    public DriveRotate(double angle){
        this(angle, RandomConstants.MAX_ROT_SPEED);
    }

    /**
     * Sets up the profiler and initializes stuff that can be initialized before <code>initialize()</code> is called.
     *
     * @param angle The angle to turn the robot.
     * @param maxVel The maximum velocity at which the robot should turn.
     */
    public DriveRotate(double angle, double maxVel) {
        requires(Robot.drivey);

        maxVel = Math.abs(maxVel);

        if(maxVel > RandomConstants.MAX_ROT_SPEED){
            maxVel = RandomConstants.MAX_ROT_SPEED;
            SmartDashboard.putString("Maximum rot speed", "is too high");
        }

        profiler = new TrapezoidalMotionProfiler(angle, maxVel, RandomConstants.MAX_ROT_ACCEL);
        theta = 0;
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        profiler.start();
        time = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        double omega = profiler.update(theta);

        theta += omega * (System.currentTimeMillis() - time) / 1000;

        SmartDashboard.putNumber("Autonomous omega", omega);
        SmartDashboard.putNumber("Autonomous theta", theta);

        Robot.drivey.drive(0, 0, omega);

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
        Robot.drivey.drive(0, 0, 0);
    }
}

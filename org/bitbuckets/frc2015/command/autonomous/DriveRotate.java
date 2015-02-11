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
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public DriveRotate(double angle) {
        requires(Robot.drivey);

        profiler = new TrapezoidalMotionProfiler(angle, RandomConstants.MAX_ROT_SPEED, RandomConstants.MAX_ROT_ACCEL);
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

package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.control.TrapezoidalMotionProfiler;
//TODO Fix Javadocs

/**
 * Created by James on 2/22/2015.
 */
public class GrabbyMoveDistance extends Command {
    private TrapezoidalMotionProfiler profiler;
    private double posInit;
    private long timeInit;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public GrabbyMoveDistance(double dist) {
        profiler = new TrapezoidalMotionProfiler(dist, RandomConstants.MAX_GRABBY_LIFTER_SPEED, RandomConstants.MAX_GRABBY_LIFTER_ACCEL);
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        Robot.grabby.getLifterController().changeControlMode(CANTalon.ControlMode.PercentVbus);
        posInit = Robot.grabby.getVerticalPosition();
        timeInit = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        Robot.grabby.setLifterMotor(profiler.update(posInit - Robot.grabby.getVerticalPosition()));
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
        Robot.grabby.setLifterMotor(0);
        Robot.grabby.getLifterController().changeControlMode(CANTalon.ControlMode.Position);
        Robot.grabby.getLifterController().set(Robot.grabby.getLifterController().get());
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
        Robot.grabby.setLifterMotor(0);
        Robot.grabby.getLifterController().changeControlMode(CANTalon.ControlMode.Position);
        Robot.grabby.getLifterController().set(Robot.grabby.getLifterController().get());
    }
}

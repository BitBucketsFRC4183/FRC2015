package org.bitbuckets.frc2015.command.autonomous;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.control.TrapezoidalMotionProfiler;

/**
 *
 */
public class DriveTranslation extends Command {
    private double xDist;
    private double yDist;
    private TrapezoidalMotionProfiler xProf;
    private TrapezoidalMotionProfiler yProf;
    private long time;

    public DriveTranslation(double xGoal, double yGoal) {
        requires(Robot.drivey);

        xProf = new TrapezoidalMotionProfiler(xGoal, RandomConstants.MAX_TRANS_SPEED, RandomConstants.MAX_TRANS_ACCEL);
        yProf = new TrapezoidalMotionProfiler(yGoal, RandomConstants.MAX_TRANS_SPEED, RandomConstants.MAX_TRANS_ACCEL);

        xDist = 0;
        yDist = 0;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        xProf.start();
        yProf.start();
        time = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        double xVel = xProf.update(xDist);
        double yVel = yProf.update(yDist);

        xDist += xVel * (System.currentTimeMillis() - time) / 1000;
        yDist += yVel * (System.currentTimeMillis() - time) / 1000;

        SmartDashboard.putNumber("X Velocity", xVel);
        SmartDashboard.putNumber("Y Velocity", yVel);
        SmartDashboard.putNumber("X Distance", xDist);
        SmartDashboard.putNumber("Y Distance", yDist);

        Robot.drivey.drive(xVel, yVel, 0);

        time = System.currentTimeMillis();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return xProf.getFinished() && yProf.getFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

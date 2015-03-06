package org.bitbuckets.frc2015.command.autonomous;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.control.PositionMotionProfiler;
import org.bitbuckets.frc2015.util.SerialPortManager;

/**
 * This {@link edu.wpi.first.wpilibj.command.Command} tells the robot to move a radius at angle theta.
 * <p/>
 * Created by James on 2/6/2015.
 */
public class DrivePolar extends Command {
    private double distance;
    private double theta;
    private double initHeading;
    private double correctionHeading;
    private double feetPerEncTick;
    private PositionMotionProfiler profiler;

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

        if (maxVel > RandomConstants.MAX_TRANS_SPEED) {
            maxVel = RandomConstants.MAX_TRANS_SPEED;
            SmartDashboard.putString("Maximum tran speed", "is too high");
        }

        profiler = new PositionMotionProfiler(radius, maxVel, RandomConstants.MAX_TRANS_ACCEL, .25);
        theta = angle;
        distance = 0;
        feetPerEncTick = RandomConstants.WHEEL_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV;
    }

    /**
     * Initializes the time and starts the profiler.
     */
    protected void initialize() {
        Robot.drivey.setEncoderSetting(CANTalon.ControlMode.Position);
        initHeading = SerialPortManager.getHeading();
        Robot.drivey.headingController.setSetpoint(initHeading);
        profiler.start();
    }

    /**
     * Called repeatedly when this Command is scheduled to run. Uses a position motion profile and calculates the desired encoder position on each wheel.
     */
    protected void execute() {
        distance = profiler.getTargetPosition();
        correctionHeading = Robot.drivey.headingOut.getIn();

        double targetX = distance * Math.cos(theta);
        double targetY = distance * Math.sin(theta);

        if (RandomConstants.TESTING) {
            //SmartDashboard.putNumber("Autonomous velocity", velocity);
            SmartDashboard.putNumber("Autonomous position", distance);
            System.out.println(Robot.drivey.getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FL_X, RobotMap.WHEEL_FL_Y, RobotMap.WHEEL_FL_THETA, targetX, targetY, correctionHeading) / feetPerEncTick);
            System.out.println("Current time: " + System.currentTimeMillis() + "\tFinish time:" + profiler.getFinishTime());
        }

        Robot.drivey.setControllers(
                Robot.drivey.getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FL_X, RobotMap.WHEEL_FL_Y, RobotMap.WHEEL_FL_THETA, targetX, targetY, correctionHeading) / feetPerEncTick,
                Robot.drivey.getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FR_X, RobotMap.WHEEL_FR_Y, RobotMap.WHEEL_FR_THETA, targetX, targetY, correctionHeading) / feetPerEncTick,
                Robot.drivey.getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RL_X, RobotMap.WHEEL_RL_Y, RobotMap.WHEEL_RL_THETA, targetX, targetY, correctionHeading) / feetPerEncTick,
                Robot.drivey.getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RR_X, RobotMap.WHEEL_RR_Y, RobotMap.WHEEL_RR_THETA, targetX, targetY, correctionHeading) / feetPerEncTick);

        //Robot.drivey.drive(velocity * Math.cos(theta), velocity * Math.sin(theta), correctionHeading);
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     *
     * @return Whether the profiler is finished.
     */
    protected boolean isFinished() {
        return System.currentTimeMillis() > profiler.getFinishTime();
    }

    /**
     * Called once after <code>isFinished()</code> returns true. Stops the robot.
     */
    protected void end() {
        Robot.drivey.setEncoderSetting(CANTalon.ControlMode.Speed);
        Robot.drivey.drive(0, 0, 0);
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run. Stops the robot.
     */
    protected void interrupted() {
        Robot.drivey.setEncoderSetting(CANTalon.ControlMode.Speed);
        Robot.drivey.drive(0, 0, 0);
    }
}

package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Wheely extends Subsystem {
    private CANTalon talon;
    private double x;
    private double y;
    private double theta;

    public enum WheelPos {
        FRONT_LEFT, FRONT_RIGHT, REAR_LEFT, REAR_RIGHT
    }

    public Wheely(WheelPos pos) {
        switch (pos) {
            case FRONT_LEFT:
                x = RobotMap.WHEEL_FL_X;
                y = RobotMap.WHEEL_FL_Y;
                theta = RobotMap.WHEEL_FL_THETA;
                talon = new CANTalon(RobotMap.WHEEL_RL_MOTOR);
                break;
            case FRONT_RIGHT:
                x = RobotMap.WHEEL_FR_X;
                y = RobotMap.WHEEL_FR_Y;
                theta = RobotMap.WHEEL_FR_THETA;
                talon = new CANTalon(RobotMap.WHEEL_FR_MOTOR);
                break;
            case REAR_LEFT:
                x = RobotMap.WHEEL_RL_X;
                y = RobotMap.WHEEL_RL_Y;
                theta = RobotMap.WHEEL_RL_THETA;
                talon = new CANTalon(RobotMap.WHEEL_RL_MOTOR);
                break;
            case REAR_RIGHT:
                x = RobotMap.WHEEL_RR_X;
                y = RobotMap.WHEEL_RR_Y;
                theta = RobotMap.WHEEL_RR_THETA;
                talon = new CANTalon(RobotMap.WHEEL_RR_MOTOR);
                break;
            default:
                x = 0;
                y = 0;
                theta = 0;
                throw new IndexOutOfBoundsException();
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Moves the wheel at the correct speed to make the robot move in the desired direction.
     *
     * @param vx    The desired X velocity of the robot.
     * @param vy    The desired y velocity of the robot.
     * @param omega The desired rotational velocity of the robot.
     */
    public void moveWheel(double vx, double vy, double omega) {
        double speed = Robot.drivey.getWheelSpeed(0, 0, x, y, theta, vx, vy, omega);
        talon.set(speed);
    }
}


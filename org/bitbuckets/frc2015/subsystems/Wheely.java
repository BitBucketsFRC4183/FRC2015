package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.control.OmniDriveWheelMotionController;

/**
 *
 */
public class Wheely extends Subsystem {
    private CANTalon talon;
    private double x;
    private double y;
    private double theta;
    private OmniDriveWheelMotionController mController;


    public Wheely(int index) {
        talon = new CANTalon(index);
        mController = new OmniDriveWheelMotionController();
        switch (index) {
            case 1:
                x = RobotMap.WHEEL_FL_X;
                y = RobotMap.WHEEL_FL_Y;
                theta = Math.PI / 4;
                break;
            case 2:
                x = RobotMap.WHEEL_FR_X;
                y = RobotMap.WHEEL_FR_Y;
                theta = 7 * Math.PI / 4;
                break;
            case 3:
                x = RobotMap.WHEEL_RL_X;
                y = RobotMap.WHEEL_RL_Y;
                theta = 3 * Math.PI / 4;
                break;
            case 4:
                x = RobotMap.WHEEL_RR_X;
                y = RobotMap.WHEEL_RR_Y;
                theta = 5 * Math.PI / 4;
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
        double speed = mController.getWheelSpeed(0, 0, x, y, theta, vx, vy, omega);
        talon.set(speed);
    }
}


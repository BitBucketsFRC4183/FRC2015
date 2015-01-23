package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Grabby extends Subsystem {
    /**
     * The motor for rotation of the grabber *
     */
    private SpeedController rotator;
    private SpeedController rotatorAlt;
    /**
     * The motor to control the grabber *
     */
    private DoubleSolenoid grabby;
    private DoubleSolenoid flippy;

    /**
     * The potentiometer to measure the angle rotation of grabber *
     */
    AnalogPotentiometer grabberTiltPot;

    public Grabby() {
        rotator = new CANTalon(RobotMap.GRAB_TILT_MOTOR);
        rotatorAlt = new CANTalon(RobotMap.GRAB_TILT_MOTOR_ALT);
        grabby = new DoubleSolenoid(RobotMap.GRABBY_SOLENOID_1, RobotMap.GRABBY_SOLENOID_2);
        flippy = new DoubleSolenoid(RobotMap.FLIPPY_SOLENOID_1, RobotMap.FLIPPY_SOLENOID_2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Sets the speed of the grabber tilt motor
     *
     * @param speed is between -1.0 and 1.0
     */
    public void setSpeed(double speed) {
        rotator.set(speed);
        rotatorAlt.set(-speed);
    }

    /**
     * Sets the grabber to a closed or open position
     *
     * @param closed setting to true closes the grabber at speed kForward
     */
    public void setGrabbed(boolean closed) {
        grabby.set(closed ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    public void setFlipped(boolean flipped) {
        flippy.set(flipped ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }
}


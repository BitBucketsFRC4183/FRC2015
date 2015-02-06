package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Grabby extends Subsystem {
    /**
     * The motor to control the grabber *
     */
    private DoubleSolenoid grabby;
    private DoubleSolenoid flippy;

    /**
     * The potentiometer to measure the angle rotation of grabber *
     */
    AnalogPotentiometer grabberTiltPot;

    /**
     * Sets up all the solenoids and motors.
     */
    public Grabby() {
        grabby = new DoubleSolenoid(RobotMap.GRABBY_SOLENOID_1, RobotMap.GRABBY_SOLENOID_2);
        flippy = new DoubleSolenoid(RobotMap.FLIPPY_SOLENOID_1, RobotMap.FLIPPY_SOLENOID_2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Sets the grabber to a closed or open position
     *
     * @param closed setting to true closes the grabber at speed kForward
     */
    public void setGrabbed(boolean closed) {
        grabby.set(closed ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }

    /**
     * Changes the state of the pneumatic cylinders that flip the can.
     *
     * @param flipped Whether the can is flipped or not.
     */
    public void setFlipped(boolean flipped) {
        flippy.set(flipped ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
    }
}


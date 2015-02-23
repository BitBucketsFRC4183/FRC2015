package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Grabby extends Subsystem {
    /**
     * The motor to control the grabber *
     */
    private Talon grabberController;
    private CANTalon lifterController;

    private DigitalInput open;

    /**
     * Sets up all the solenoids and motors.
     */
    public Grabby() {
        grabberController = new Talon(RobotMap.GRABBY_GRABBER);
        lifterController = new CANTalon(RobotMap.GRABBY_LIFTER);

        lifterController.setPID(RandomConstants.GRABBY_KP, RandomConstants.GRABBY_KI, RandomConstants.GRABBY_KD);
        lifterController.changeControlMode(CANTalon.ControlMode.Speed);

        open = new DigitalInput(RobotMap.GRABBY_OPEN);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Resets the encoder on the pulley for the lifter that lifts the grabber.
     */
    public void resetLifterEncoder(){
        lifterController.setPosition(0);
    }

    /**
     * Sets the grab motor speed.
     *
     * @param speed The speed at which to move the grab motor
     */
    public void setGrabMotor(double speed){
        grabberController.set(speed);
    }

    /**
     * Sets the speed of the motor that lifts the grabber.
     *
     * @param speed The speed at which to make the grabber move in ft/s
     */
    public void setLifterMotor(double speed){
        lifterController.set(speed * RandomConstants.ENC_TICK_PER_REV / RandomConstants.GRABBY_WINCH_DRUM_CIRCUMFERENCE);
    }

    /**
     * Gets whether the grabber is closed according to the current being drawn by the motor
     *
     * @return If the current draw from the grab motor port is greater than the threshold.
     */
    public boolean getCurrentFinised(){
        return Robot.pdp.getCurrent(RobotMap.GRABBER_MOTOR_CHANNEL) >= RandomConstants.GRABBY_CURRRENT_MAX;
    }

    /**
     * Gets the state of the limit switch that closes when the grabber is open.
     *
     * @return If the limit is triggered.
     */
    public boolean getOpen(){
        return open.get();
    }

    /**
     * Gets the position of the grabber in feet according to the encoder
     *
     * @return The vertical position above the bottom.
     */
    public double getVerticalPosition(){
        return lifterController.getEncPosition() * RandomConstants.GRABBY_WINCH_DRUM_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV;
    }

    /**
     * Gets if the top limit switch is closed.
     *
     * @return If the top limit switch is closed.
     */
    public boolean getLimitTop(){
        return lifterController.isRevLimitSwitchClosed();
    }

    /**
     * Gets if the bottom limit switch is closed.
     *
     * @return If the bottom limit switch is closed.
     */
    public boolean getLimitBottom(){
        if(lifterController.isFwdLimitSwitchClosed()){
            resetLifterEncoder();
            return true;
        }
        return false;
    }
}


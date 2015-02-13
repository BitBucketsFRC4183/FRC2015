package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Grabby extends Subsystem {
    /**
     * The motor to control the grabber *
     */
    private CANTalon grabberController;
    private CANTalon lifterController;

    private DigitalInput open;

    /**
     * Sets up all the solenoids and motors.
     */
    public Grabby() {
        grabberController = new CANTalon(RobotMap.GRABBY_GRABBER);
        lifterController = new CANTalon(RobotMap.GRABBY_LIFTER);

        open = new DigitalInput(RobotMap.GRABBY_OPEN);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void setGrabMotor(double speed){
        grabberController.set(speed);
    }

    public boolean getCurrentFinised(){
        return grabberController.getOutputCurrent() >= RandomConstants.GRABBY_CURRRENT_MAX;
    }

    public boolean getOpen(){
        return open.get();
    }

    public void setLifterMotor(double speed){
        lifterController.set(speed);
    }
}


package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
        lifterController.reverseOutput(true);

        lifterController.setPID(RandomConstants.GRABBY_KP, RandomConstants.GRABBY_KI, RandomConstants.GRABBY_KD, 0.0, RandomConstants.GRABBY_IZONE, 0, 0);
        lifterController.changeControlMode(CANTalon.ControlMode.PercentVbus);
        lifterController.enableLimitSwitch(true, true);



        open = new DigitalInput(RobotMap.GRABBY_OPEN);

        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Grabby KP:", RandomConstants.GRABBY_KP);
            SmartDashboard.putNumber("Grabby KI:", RandomConstants.GRABBY_KI);
            SmartDashboard.putNumber("Grabby KD:", RandomConstants.GRABBY_KD);
            SmartDashboard.putNumber("Grabby KF:", RandomConstants.GRABBY_KF);
            SmartDashboard.putNumber("Grabby IZONE:", RandomConstants.GRABBY_IZONE);
        }
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
        lifterController.set(speed /* * RandomConstants.ENC_TICK_PER_REV / RandomConstants.GRABBY_WINCH_DRUM_CIRCUMFERENCE*/);

        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Limit Fault", lifterController.getFaultForLim());
            SmartDashboard.putNumber("Soft Limit Fault", lifterController.getFaultForSoftLim());
            SmartDashboard.putNumber("Hardware Faliure Fault", lifterController.getFaultHardwareFailure());
            SmartDashboard.putNumber("Too Hot Fault", lifterController.getFaultOverTemp());
            SmartDashboard.putNumber("Too Fast Fault", lifterController.getFaultRevLim());
            SmartDashboard.putNumber("Too Fast Soft Fault", lifterController.getFaultRevSoftLim());
            SmartDashboard.putNumber("Under Voltage Fault", lifterController.getFaultUnderVoltage());
        }
    }

    /**
     * Gets whether the grabber is closed according to the current being drawn by the motor
     *
     * @return If the current draw from the grab motor port is greater than the threshold.
     */
    public boolean getCurrentFinised(){
        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Current", Robot.pdp.getCurrent(RobotMap.GRABBER_MOTOR_CHANNEL));
        }
        
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
    
    /**
     * Returns a reference to the lift CANTalon
     * 
     * @return
     */
    public CANTalon getLifterController(){
    	return lifterController;
    }
    
    public void resetGrabbyPID(){
    	try{
    		lifterController.setPID(SmartDashboard.getNumber("Grabby KP"), SmartDashboard.getNumber("Grabby KI"), SmartDashboard.getNumber("Grabby KD"), SmartDashboard.getNumber("Grabby KF"), (int) SmartDashboard.getNumber("Grabby IZONE"), 0, 0);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
        SmartDashboard.putNumber("Grabby KP", lifterController.getP());
        SmartDashboard.putNumber("Grabby KI", lifterController.getI());
        SmartDashboard.putNumber("Grabby KD", lifterController.getD());
        SmartDashboard.putNumber("Grabby KF", lifterController.getF());
        SmartDashboard.putNumber("Grabby IZONE", lifterController.getIZone());
    }
}


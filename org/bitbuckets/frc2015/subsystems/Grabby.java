package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.AnalogInput;
import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabby extends Subsystem {
	/** The motor for rotation of the grabber **/
	SpeedController rotator;
	SpeedController rotatorAlt;
	
	/** The motor to control the grabber **/
	DoubleSolenoid grabby;
	
	/** The potentiometer to measure the angle rotation of grabber **/
	AnalogPotentiometer grabberTiltPot;
    
	public Grabby(){
		rotator = new CANTalon(RobotMap.GRAB_TILT_MOTOR);
		rotatorAlt = new CANTalon(RobotMap.GRAB_TILT_MOTOR_ALT);
		grabby = new DoubleSolenoid(RobotMap.GRABBY_SOLENOID_1, RobotMap.GRABBY_SOLENOID_2);
		if(rotator instanceof CANTalon){
			CANTalon canRotator = (CANTalon) rotator;
		}
		//grabberTiltPot = new AnalogPotentiometer(new AnalogInput(1), 360, 30);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void setAngle(double angle){
		/*
		if(rotator instanceof CANTalon){
			CANTalon canRotator = (CANTalon) rotator;
//			canRotator.setPID();
		}
		*/
		
		
	}
	
	/**
	 * Sets the speed of the grabber tilt motor
	 * 
	 * @param speed is between -1.0 and 1.0
	 */
	public void setSpeed(double speed){
		rotator.set(speed);
		rotatorAlt.set(-speed);
	}

	public boolean getIsAngleReady(){
		return true;
	}

	/**
	 * Sets the grabber to a closed or open position
	 * 
	 * @param closed setting to true closes the grabber at speed kForward
	 */
	public void setGrabby(boolean closed){
		grabby.set(closed ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
}


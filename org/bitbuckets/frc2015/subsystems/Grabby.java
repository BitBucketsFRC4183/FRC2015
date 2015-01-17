package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Grabby extends Subsystem {
	/** The motor for rotation the grabber **/
	SpeedController rotator;
	DoubleSolenoid grabby;
    
	public Grabby(){
		rotator = new CANTalon(RobotMap.GRAB_TILT_MOTOR);
		grabby = new DoubleSolenoid(RobotMap.GRABBY_SOLENOID_1, RobotMap.GRABBY_SOLENOID_2);
		if(rotator instanceof CANTalon){
			CANTalon canRotator = (CANTalon) rotator;
		}
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

	public void setAngle(){
		if(rotator instanceof CANTalon){
			CANTalon canRotator = (CANTalon) rotator;
//			canRotator.setPID();
		}
	}

	public boolean getIsAngleReady(){
		return true;
	}

	/**
	 * 
	 * @param closed setting to true closes the grabber at speed kForward
	 */
	public void setGrabby(boolean closed){
		grabby.set(closed ? DoubleSolenoid.Value.kForward : DoubleSolenoid.Value.kReverse);
	}
}


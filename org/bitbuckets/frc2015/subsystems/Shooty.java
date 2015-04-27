package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooty extends Subsystem {

	public enum ShootState {
		OFF, SHOOTING, RETRACTING, RETRACTSHORT
	}

	private Talon shooter;
	private Talon winder;

	private ShootState state = ShootState.OFF;

	public Shooty() {
		shooter = new Talon(RobotMap.SHOOTY_SHOOTER);
		winder = new Talon(RobotMap.SHOOTY_WINDER);
	}

	/**
	 * Sets the speed of both the motor that shoots and the motor that retracts.
	 * 
	 * @param speed
	 *            The speed to run the motor.
	 */
	public void setShootSpeed(double speed) {
		shooter.set(-1 * speed);
	}
	
	public void setWindSpeed(double speed){
		winder.set(-1 * speed);
	}

	/**
	 * Sets the state of shooty. If it is set to {@link ShootState.SHOOTING} or
	 * {@link ShootState.RETRACTING}, it shoots or retracts, respectively.
	 * 
	 * @param newState The state at which to set the shooter.
	 */
	public void setState(ShootState newState) {
		state = newState;
	}

	public ShootState getState() {
		return state;
	}

	public void initDefaultCommand() {

	}
}

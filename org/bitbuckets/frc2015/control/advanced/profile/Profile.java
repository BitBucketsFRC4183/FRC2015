package org.bitbuckets.frc2015.control.advanced.profile;

import org.bitbuckets.frc2015.control.advanced.MovementVector;


/**
 * An base class for motion profiles.
 * 
 * @author Miles Marchant
 * @version 0.9
 *
 */
public abstract class Profile<T> {	
		
	public Profile(){}
	
	public abstract T getOutput(long time);
	
	protected abstract void generateSplines(MovementVector values);
	
	public abstract void regenerateSplines(double drive);
	
	public abstract boolean finished(long time);

}

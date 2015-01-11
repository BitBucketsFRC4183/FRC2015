package org.bitbuckets.frc2015.lib;

public interface MotionController {
	public void init(double... inputs);
	
	/**
	 * Gets the current speed of the <code>SpeedManager</code>.
	 * 
	 * @return The current speed.
	 */
	public double getSpeed();
	
	/**
	 * Updates the <code>speed</code> of this manager based on sensor inputs
	 * 
	 * @param set The target <code>speed</code> for the <code>SpeedManager</code>.
	 * @param input The inputs from all the sensors this <code>SpeedManager</code> needs inputs from.
	 * @return The updated <code>speed</code> of this <code>SpeedManager</code>.
	 */
	public double update(double set, double... input);
}
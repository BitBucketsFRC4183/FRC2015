package org.bitbuckets.frc2015.util;

public interface MotionController {
	/**
	 * Initializes the Motion Controller.
	 *
	 * @param inputs The inputs that are needed to initialize the class. Different for every type.
	 */
	public void init(double... inputs);
	
	/**
	 * Gets the current speed of the <code>MotionController</code>.
	 * 
	 * @return The current speed.
	 */
	public double getSpeed();
	
	/**
	 * Updates the <code>speed</code> of this manager based on sensor inputs
	 * 
	 * @param set The target <code>speed</code> for the <code>MotionController</code>.
	 * @param input The inputs from all the sensors this <code>MotionController</code> needs inputs from.
	 * @return The updated <code>speed</code> of this <code>MotionController</code>.
	 */
	public double update(double set, double... input);
}
package org.bitbuckets.frc2015.lib;

public interface MotionProfiler {
	/**
	 * Initializes the <code>MotionProfiler</code>.
	 * 
	 * @param pos The target position for the <code>MotionProfiler</code>.
	 * @param maxVel The max velocity at which the motor should run.
	 */
	public void init(int pos, int maxVel);
	
	/**
	 * Gets the current speed at which the motor should be running.
	 * 
	 * @return The current speed of this profiler.
	 */
	public double getSpeed();
	
	/**
	 * Updates the speed at which the motor should be running.
	 * 
	 * @return The current speed to set the motor to.
	 */
	public double update();
}

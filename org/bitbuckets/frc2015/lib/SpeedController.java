package org.bitbuckets.frc2015.lib;

public interface SpeedController {
	public float getSpeed();
	
	public float update(double set, double input);
}

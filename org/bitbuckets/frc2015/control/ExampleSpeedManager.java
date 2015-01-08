package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.lib.SpeedManager;

public class ExampleSpeedManager implements SpeedManager{
	/** The sensor value the last time <code>update()</code> was called. **/
	private float prevSense;
	/** The current speed of the <code>SpeedManager</code>. **/
	private float speed;

	@Override
	public void init(float... inputs) {
		prevSense = inputs[0];
		speed = 0;
	}
	
	@Override
	public float getSpeed() {
		return speed;
	}

	@Override
	public float update(float set, float... input) {
		float sense = input[0];
		float moved = sense-prevSense; 
		float error = set - moved;
		
		speed = set + RandomConstants.SPEED_K_EXSPMA * error;
		
		return speed;
	}

}

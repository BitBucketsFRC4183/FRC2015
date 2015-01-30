package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.util.MotionController;

public class ExampleMotionController implements MotionController{
	/** The sensor value the last time <code>update()</code> was called. **/
	private double prevSense;
	/** The current speed of the <code>SpeedManager</code>. **/
	private double speed;

	@Override
	public void init(double... inputs) {
		prevSense = inputs[0];
		speed = 0;
	}
	
	@Override
	public double getSpeed() {
		return speed;
	}

	@Override
	public double update(double set, double... input) {
		double sense = input[0];
		double moved = sense-prevSense; 
		double error = set - moved;
		
		speed = set + RandomConstants.SPEED_K_EXSPMA * error;
		
		return speed;
	}

}

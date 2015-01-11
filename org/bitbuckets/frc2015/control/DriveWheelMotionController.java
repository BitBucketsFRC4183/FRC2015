package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.lib.MotionController;

public class DriveWheelMotionController implements MotionController{
	private double speed;
	
	@Override
	public void init(double... inputs) {
		
		
	}

	@Override
	public double getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double update(double set, double... input) {
		double sense = input[0];
		return 0;
	}

}

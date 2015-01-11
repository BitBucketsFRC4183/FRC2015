package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.lib.MotionController;

public class DriveWheelMotionController implements MotionController{
	private float speed;
	
	@Override
	public void init(float... inputs) {
		
		
	}

	@Override
	public float getSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float update(float set, float... input) {
		float sense = input[0];
		return 0;
	}

}

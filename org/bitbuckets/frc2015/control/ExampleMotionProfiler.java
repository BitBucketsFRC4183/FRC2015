package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.lib.MotionProfiler;

public class ExampleMotionProfiler implements MotionProfiler {
	private float maxPos;
	private float maxVel;
	private float currPos;
	private float currVel;
	
	@Override
	public void init(int pos, int maxVel) {
		maxPos = pos;
		this.maxVel = maxVel;
	}

	@Override
	public float getSpeed() {
		return currVel;
	}

	@Override
	public float update() {
		
		
		return currVel;
	}

}

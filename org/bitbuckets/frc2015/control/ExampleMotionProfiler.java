package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.util.MotionProfiler;

public class ExampleMotionProfiler implements MotionProfiler {
	private double maxPos;
	private double maxVel;
	private double currPos;
	private double currVel;
	
	@Override
	public void init(double pos, double maxVel) {
		maxPos = pos;
		this.maxVel = maxVel;
	}

	@Override
	public double getSpeed() {
		return currVel;
	}

	@Override
	public double update() {
		
		
		return currVel;
	}

	@Override
	public void setSetpoint(double pos) {

	}

}

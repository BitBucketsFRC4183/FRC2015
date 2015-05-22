package org.bitbuckets.frc2015.control.advanced.profile;

import java.util.function.LongFunction;

import org.bitbuckets.frc2015.control.advanced.MovementVector;

public class Spline{
	
	LongFunction<MovementVector> functions;
	private long startTime;
	private long endTime;
	
	public Spline(LongFunction<MovementVector> functions, long startTime, long endTime){
		this.functions = functions;
		this.endTime = endTime;
	}
	
	public MovementVector calculate(long time){
		return functions.apply(time);
	}
	
	public long getStartTime(){
		return startTime;
	}
	
	public long getEndTime(){
		return endTime;
	}
}
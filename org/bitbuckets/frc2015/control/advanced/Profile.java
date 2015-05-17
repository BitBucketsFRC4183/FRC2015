package org.bitbuckets.frc2015.control.advanced;

import java.util.function.DoubleFunction;

/**
 * An base class for motion profiles.
 * 
 * @author Miles Marchant
 * @version 0.9
 *
 */
public abstract class Profile {
	
	MovementVector translation;
	MovementVector rotational;
	double maxTransVel;
	double maxRotVel;
	
	
	
	public Profile(MovementVector translation, MovementVector rotational, double maxTransVel, double maxRotVel){
		this.translation = translation;
		this.rotational = rotational;
		this.maxTransVel = maxTransVel;
		this.maxRotVel = maxRotVel;
	}
	
	
	public abstract MovementVector getTranslationVector(long time);
	
	public abstract MovementVector getRotationVector(long time);
	
	public abstract double getPosition(long time);
	
	public abstract double getAngle(long time);

	public abstract void generateComponents(double drive);
	
	
	
	
	
	
	class Component{
		
		DoubleFunction<Long> calculator;
		long startTime;
		long endTime;
		
		public Component(DoubleFunction<Long> calculator, long startTime, long endTime){
			this.calculator = calculator;
			this.startTime = startTime;
			this.endTime = endTime;
		}
		
		public double calculate(long time){
			return calculator.apply(time);
		}
		
		public long getStartTime(){
			return startTime;
		}
		
		public long getEndTime(){
			return endTime;
		}
		
	}






	public boolean finished(long time) {
		return false;
	}









}

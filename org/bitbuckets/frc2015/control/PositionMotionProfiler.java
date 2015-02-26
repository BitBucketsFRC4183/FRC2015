package org.bitbuckets.frc2015.control;

public class PositionMotionProfiler {

	   private long initTime;
	   private long prevTime;
	   private long currTime;
	   private double targetDist;
	   private double maxVelocity;
	   private double maxAcceleration;
	   private double position;
	   private double velocity;
	   private double accelDist;
	   private double expectedTime;
	   private boolean triProfile;
	   private double dir;
	   private double dt;
	   private boolean finished;

	   public PositionMotionProfiler(double targetDist, double maxVelocity, double maxAcceleration) {
	       this.targetDist = Math.abs(targetDist);
	       this.maxVelocity = maxVelocity;
	       this.maxAcceleration = maxAcceleration;
	       dir = Math.signum(targetDist);
	       reset();
	   }

	   public void reset() {
	       accelDist = 0.5 * maxVelocity * maxVelocity / maxAcceleration;
	       triProfile = targetDist < accelDist*2;
	       position = 0.0;
	       velocity = 0.0;
	       finished = false;
	       expectedTime = triProfile
	               ? 2 * Math.sqrt(targetDist/maxAcceleration)
	               : accelDist*4 / maxVelocity + (targetDist - accelDist*2) / maxVelocity;
	       System.out.println("Expected time: " + expectedTime);
	   }

	   public void start() {
	       initTime = System.currentTimeMillis();
	       prevTime = initTime;
	       finished = false;
	   }

	   /**
	    * Call this function repeatedly to get updated velocities matching a position profile curve.
	    * Use this function if you do not have the ability to read the current position.
	    * 
	    * @return the current velocity as dictated by the profile.
	    */
	   public double getNextVel() {
	       currTime = System.currentTimeMillis();
	       if(currTime - initTime > expectedTime * 1000){
	    	   finished = true;
	           return targetDist*dir;
	       }

	       dt = (double)(currTime - prevTime) / 1000.0;
	       prevTime = currTime;

	       updateVelocity();

	       position += velocity * dt;

	       return velocity*dir;
	   }
	   
	   /**
	    * Call this function repeatedly to get updated velocities matching a position profile curve.
	    * Use this function if you can directly read the current position.
	    * 
	    * @param pos - The current position as measured with some sort of sensor.
	    * @return the current velocity as dictated by the profile.
	    */
	   public double getNextVel(double pos) {
		   position = pos;
	       currTime = System.currentTimeMillis();
	       if(currTime - initTime > expectedTime * 1000){
	    	   finished = true;
	           return targetDist*dir;
	       }

	       dt = (double)(currTime - prevTime) / 1000.0;
	       prevTime = currTime;

	       updateVelocity();

	       return velocity*dir;
	   }
	   
	   private void updateVelocity(){
	       if(triProfile) {
	           if(position < targetDist/2.0) {
	               velocity += maxAcceleration * dt;
	           } else {
	               velocity -= maxAcceleration * dt;
	           }
	       } else {
	           if(position < accelDist) {
	               velocity += maxAcceleration * dt;
	           } else if(position > accelDist && position < (targetDist - accelDist)) {
	               velocity = maxVelocity;
	           } else {
	               velocity -= maxAcceleration * dt;
	           }
	       }
	   }

	   public double getVelocity() {
	       if(currTime - initTime > expectedTime*1000)
	           return 0;
	       else
	           return velocity*dir;
	   }

	   public long getFinishTime() {
	       return initTime + (long)(expectedTime*1000.0);
	   }
	   
	   public boolean isFinished(){
		   return finished;
	   }

	}
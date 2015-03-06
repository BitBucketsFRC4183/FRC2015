package org.bitbuckets.frc2015.control;

import org.bitbuckets.frc2015.RandomConstants;

public class PositionMotionProfiler {

   private long initTime;
   private long prevTime;
   private long currTime;
   private double settleTime;
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

   public PositionMotionProfiler(double targetDist, double maxVelocity, double maxAcceleration) {
       this(targetDist, maxVelocity, maxAcceleration, RandomConstants.DEFAULT_SETTLE_TIME);
   }
   
   public PositionMotionProfiler(double targetDist, double maxVelocity, double maxAcceleration, double settleTime){
	   this.targetDist = Math.abs(targetDist);
       this.maxVelocity = maxVelocity;
       this.maxAcceleration = maxAcceleration;
       this.settleTime = settleTime;
       dir = Math.signum(targetDist);
       reset();
   }

   public void reset() {
       accelDist = 0.5 * maxVelocity * maxVelocity / maxAcceleration;
       triProfile = targetDist < accelDist*2;
       position = 0.0;
       velocity = 0.0;
       expectedTime = triProfile
               ? 2 * Math.sqrt(targetDist/maxAcceleration)
               : accelDist*4 / maxVelocity + (targetDist - accelDist*2) / maxVelocity;
       expectedTime += settleTime / 1000;
       System.out.println("Expected time: " + expectedTime);
   }

   public void start() {
       initTime = System.currentTimeMillis();
       prevTime = initTime;
   }

   public double getTargetPosition() {
       currTime = System.currentTimeMillis();
       if(currTime - initTime > expectedTime * 1000)
           return targetDist*dir;

       dt = (double)(currTime - prevTime) / 1000.0;
       prevTime = currTime;

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

       position += velocity * dt;

       return position*dir;
   }

   /**
    * Returns the current velocity. if the current time is past the expectedTime, it returns 0.
    * 
    * @return 
    */
   public double getVelocity() {
       if(currTime - initTime > expectedTime*1000)
           return 0;
       else
           return velocity*dir;
   }

   /**
    * Returns the system time at which this profile will finish. Settle time is included. Units are milliseconds.
    * 
    * @return A long representing the system time in milliseconds at which the profile is done.
    */
   public long getFinishTime() {
       return initTime + (long)(expectedTime*1000.0);
   }

}
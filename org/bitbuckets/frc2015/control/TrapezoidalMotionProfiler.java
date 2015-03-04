package org.bitbuckets.frc2015.control;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;

/**
 * A motion profiler that creates a trapezoidal profile and updates the speed accordingly.
 * <p/>
 * Created by James on 1/30/2015.
 */
public class TrapezoidalMotionProfiler {
    private int state;
    private long timeInit;
    private double dist;
    private double maxVel;
    private double speed;
    private double accel;
    private int sign;

    /**
     * Initializes the MotionProfiler.
     *
     * @param pos  The target position for the profiler.
     * @param maxV The max velocity at which the motor should run.
     * @param acc  The acceleration for this profiler.
     */
    public TrapezoidalMotionProfiler(double pos, double maxV, double acc) {
        state = 1;
        dist = Math.abs(pos);
        sign = (int) (dist / pos);
//        dist = pos;
        maxVel = maxV;
        speed = 0;
        accel = acc;
    }

    /**
     *
     */
    public void start() {
        timeInit = System.currentTimeMillis();
    }

    /**
     * Gets the current speed at which the motor should be running.
     *
     * @return The current speed of this profiler.
     */
    public double getSpeed() {
        return speed * sign;
    }

    /**
     * Updates the speed at which the motor should be running.
     *
     * @param pos The current distance the robot has gone.
     * @return The current speed to set the motor to.
     */
    public double update(double pos) {
        pos = Math.abs(pos);
        switch (state) {
            case 1:
                speed = accel * (System.currentTimeMillis() - timeInit) / 1000;
                if (speed >= maxVel) {
                    state = 2;
                }
                if (pos * 2 >= dist) {
                    state = 3;
                    maxVel = speed;
                    timeInit = System.currentTimeMillis();
                }

                if (RandomConstants.TESTING) {
                    SmartDashboard.putNumber("Trapezoidal State", 1);
                }
                break;
            case 2:
                speed = maxVel;
                //the distance under the last trapezoid of the curve
                if (dist - pos <= (maxVel * maxVel) / (accel * 2)) {
                    state = 3;
                    timeInit = System.currentTimeMillis();
                }

                if (RandomConstants.TESTING) {
                    SmartDashboard.putNumber("Trapezoidal State", 2);
                }
                break;
            case 3:
                speed = maxVel - accel * (System.currentTimeMillis() - timeInit) / 1000;
                if (speed <= 0) {
                    state = 4;
                }

                if (RandomConstants.TESTING) {
                    SmartDashboard.putNumber("Trapezoidal State", 3);
                }
                break;
            case 4:
                speed = 0;
                state = 5;
                break;
        }
        return speed * sign;
    }

    public boolean getFinished() {
        return state == 5;
    }

    /**
     * Sets the target position for the MotionProfiler.
     *
     * @param pos The new target position.
     */
    public void setSetpoint(double pos) {
        dist = Math.abs(pos);
        sign = (int) (dist / pos);
//        dist = pos;
    }
}

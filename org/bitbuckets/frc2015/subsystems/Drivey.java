package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivey extends Subsystem {
    private Wheely FL;
    private Wheely FR;
    private Wheely RL;
    private Wheely RR;

    public Drivey() {
        super();
        FL = new Wheely(Wheely.WheelPos.FRONT_LEFT);
        FR = new Wheely(Wheely.WheelPos.FRONT_RIGHT);
        RL = new Wheely(Wheely.WheelPos.REAR_LEFT);
        RR = new Wheely(Wheely.WheelPos.REAR_RIGHT );
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void drive(double throttle, double side, double curve) {
        FL.moveWheel(throttle, side, curve);
        FR.moveWheel(throttle, side, curve);
        RL.moveWheel(throttle, side, curve);
        RR.moveWheel(throttle, side, curve);
    }

    /**
     * Gets the speed of a wheel for omni-directional drive with wheels in any configuration. <code>vwp</code> is the
     * velocity vector for the wheel without accounting for the fact that it can't rotate. <code>thetaR</code> is the
     * angle between the center of rotation and the wheel. All angles are relative to standard coordinate axes if looked
     * at from the top.
     *
     * @param xc    The x coordinate of the intended center of rotation.
     * @param yc    The y coordinate of the intended center of rotation.
     * @param xw    The x coordinate of the center of the wheel.
     * @param yw    The y coordinate of the center of the wheel.
     * @param theta The angle of the center of the wheel.
     * @param vxc   The intended x velocity of the robot.
     * @param vyc   The intended y velocity of the robot.
     * @param rotc  The intended rotational velocity of the robot around the intended center of rotation.
     * @return
     */
    public double getWheelSpeed(double xc, double yc, double xw, double yw, double theta, double vxc, double vyc, double rotc) {
        double vtan = Math.sqrt(Math.pow((xc - xw), 2) + Math.pow((yc - yw), 2)) * rotc;
        double thetaR = Math.atan2((yw - yc), (xw - xc));
        double speed = vtan * Math.abs(Math.cos(theta - (thetaR + Math.PI / 2))) + vxc * Math.cos(theta) + vyc * Math.sin(theta);
        return speed;
    }
}


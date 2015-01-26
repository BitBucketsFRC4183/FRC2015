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
        RR = new Wheely(Wheely.WheelPos.REAR_RIGHT);
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
}


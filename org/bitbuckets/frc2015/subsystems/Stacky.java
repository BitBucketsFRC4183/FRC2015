package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;

/**
 * Description: Stacks totes to one distance to another distance vertically.
 * map it to an event
 * inside the button library when its press and release,
 * when the event happens calls init,
 * continually call main
 * until it finish return true.
 * have interrupt
 */
public class Stacky extends Subsystem {
    private SpeedController winch;

    private DigitalInput hallMulti;
    private DigitalInput hallSing;

    public Stacky() {
        winch = new Talon(RobotMap.WINCH_MOTOR);
        hallMulti = new DigitalInput(RobotMap.HALL_MULTI);
        hallSing = new DigitalInput(RobotMap.HALL_SING);
    }

    public void initDefaultCommand() {

    }

    public void setWinchMotor(double speed) {
        winch.set(speed);
    }

    public boolean getHallMultiActive() {
        return hallMulti.get();
    }

    public boolean getHallSingActive() {
        return hallSing.get();
    }
}


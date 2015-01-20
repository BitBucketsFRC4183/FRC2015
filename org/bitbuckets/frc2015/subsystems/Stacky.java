package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Desc: Stacks totes to one distance to another distance vertically.
 * mapp it to an event 
 * inside the button library when its press and realease,
 * when the event happens calls init,
 * continually call main
 * until it finish return true.
 * have interrupt
 */
public class Stacky extends Subsystem {
    Talon winch;
    	

    public void initDefaultCommand() {
    	winch = new Talon(RobotMap.WINCH_MOTOR);
    }
    
    public void setWinchMotor(double speed){
    	winch.set(speed);
    }
}


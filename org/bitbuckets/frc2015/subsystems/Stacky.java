package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
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
    DigitalInput hallSensors[];

    public void initDefaultCommand() {
    	winch = new Talon(RobotMap.WINCH_MOTOR);
    	hallSensors = new DigitalInput[Robot.hallSensors.length];
    	for(int i 0; i < Robot.RobotMap.hallSensors.length; i++){
    		// Zero is the top one
    		// creates 5 hallSesors starting digital input 0 from 5
    		hallSensors[i] = new DigitalInput(i);
    	}
    }
    // returns the number of hooks
    public int howManyStack(){
    	int numberOfStacks = 0;
    	for(int i=0;i<totalHallSensors;totalHallSensors++){
    		numberOfStacks = numberOfStacks + (hallSensors[i].get()==true?1:0);
    	}
    	return numberOfStacks;
    }
    
    public void setWinchMotor(double speed){
    	winch.set(speed);
    }
    // constructor
    public Stacky(){
    	
    }
}


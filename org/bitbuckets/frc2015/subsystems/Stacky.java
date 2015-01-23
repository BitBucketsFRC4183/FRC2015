package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

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
    Talon winch;
    DigitalInput hallSensors[];
    DigitalInput buttonSensorRight;
    DigitalInput buttonSensorLeft;
    
    public Stacky(){
    	super();
    	winch = new Talon(RobotMap.WINCH_MOTOR);
    	hallSensors = new DigitalInput[RobotMap.HALL_SENSORS.length];
    	for(int i = 0; i < RobotMap.HALL_SENSORS.length; i++){
    		// Zero is the top one
    		// creates 5 hallSesors starting digital input 0 from 5
    		hallSensors[i] = new DigitalInput(i);
    	}
    }

    public void initDefaultCommand() {

    }
    // returns the number of hooks
    public int howManyStack(){
    	int numberOfStacks = 0;
    	for(int i = 0; i<hallSensors.length;i++){
    		numberOfStacks = numberOfStacks + (hallSensors[i].get()==true?1:0);
    	}
    	return numberOfStacks;
    }
    
    public void setWinchMotor(double speed){
    	winch.set(speed);
    }

}


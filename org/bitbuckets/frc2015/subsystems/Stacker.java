package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Stacker extends Subsystem {
    Talon winch;

    public void initDefaultCommand() {
    	winch = new Talon(RobotMap.WINCH_MOTOR);
    }
    
    public void setWinchMotor(double speed){
    	winch.set(speed);
    }
}


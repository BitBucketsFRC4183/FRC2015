package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {
	private RobotDrive drive;
	private Talon RMA;
	private Talon RMB;
	private Talon RMC;
	private Talon LMA;
	private Talon LMB;
	private Talon LMC;
	
	public DriveTrain(){
		super();
		RMA = new Talon(RobotMap.R_MOTOR_A);
		RMB = new Talon(RobotMap.R_MOTOR_B);
		RMC = new Talon(RobotMap.R_MOTOR_C);
		LMA = new Talon(RobotMap.L_MOTOR_A);
		LMB = new Talon(RobotMap.L_MOTOR_B);
	    LMC = new Talon(RobotMap.L_MOTOR_C);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(float throttle, float curve){
    	System.out.println("Blah");
    	SmartDashboard.putNumber("Throttle", throttle);
    	SmartDashboard.putNumber("Curve", curve);
    	float right = throttle - curve;
    	float left = throttle + curve;
    	
    	RMA.set(right);
    	RMB.set(right);
    	RMC.set(right);
    	LMA.set(left);
    	LMB.set(left);
    	LMC.set(left);
    }
}


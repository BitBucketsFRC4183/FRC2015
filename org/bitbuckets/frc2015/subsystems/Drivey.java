package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Drivey extends Subsystem {
	private RobotDrive drive;
	private CANTalon RMA;
	private CANTalon RMB;
//	private CANTalon RMC;
	private CANTalon LMA;
	private CANTalon LMB;
//	private CANTalon LMC;
	
	public Drivey(){
		super();
		RMA = new CANTalon(RobotMap.R_MOTOR_A);
		RMB = new CANTalon(RobotMap.R_MOTOR_B);
//		RMC = new CANTalon(RobotMap.R_MOTOR_C);
		LMA = new CANTalon(RobotMap.L_MOTOR_A);
		LMB = new CANTalon(RobotMap.L_MOTOR_B);
//	    LMC = new CANTalon(RobotMap.L_MOTOR_C);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double throttle, double curve){
    	System.out.println("Blah");
    	SmartDashboard.putNumber("Throttle", throttle);
    	SmartDashboard.putNumber("Curve", curve);
    	double right = throttle - curve;
    	double left = throttle + curve;
    	
    	RMA.set(right);
    	RMB.set(right);
//    	RMC.set(right);
    	LMA.set(left);
    	LMB.set(left);
//    	LMC.set(left);
    }
}


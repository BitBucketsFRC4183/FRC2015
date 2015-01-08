package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
	public RobotDrive drive;
	
	public DriveTrain(){
		super();
		drive = new RobotDrive(RobotMap.R_MOTOR_A, RobotMap.R_MOTOR_B, RobotMap.L_MOTOR_A, RobotMap.L_MOTOR_B);
		drive.setExpiration(.25);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double throttle, double curve){
    	drive.arcadeDrive(throttle, curve);
    }
}


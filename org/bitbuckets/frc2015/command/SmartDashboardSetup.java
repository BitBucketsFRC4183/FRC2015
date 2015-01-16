package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.control.OmniDriveWheelMotionController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardSetup extends Command{

	@Override
	protected void initialize() {
        SmartDashboard.putString("test", "This is a test!");
        SmartDashboard.putData(Scheduler.getInstance());
        //SmartDashboard.putNumber("Speed", Robot.dt.getSpeed());
        //SmartDashboard.putNumber("Tilty Angle", Robot.tilt.getAngle());
        //SmartDashboard.put
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		
	}

}

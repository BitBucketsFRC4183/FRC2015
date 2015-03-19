package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.GrabbyClose;
import org.bitbuckets.frc2015.command.GrabbyOpen;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GrabbyThread extends SubsystemThread{
	
	private double clawSpeed;
	private double position;

	public GrabbyThread(long iterTime, String name) {
		super(iterTime, name);
		clawSpeed = 0;
		position = Robot.grabby.getLifterController().getPosition();
		Robot.grabby.getLifterController().changeControlMode(ControlMode.PercentVbus);
	}
	
	@Override
	protected void execute(){
		
		clawSpeed = 0;
		
		if(Robot.oi.operatorGrabClose.get()){
			clawSpeed += RandomConstants.GRAB_SPEED;
		}
		if(Robot.oi.operatorGrabOpen.get()){
			clawSpeed -= RandomConstants.GRAB_SPEED;
		}
		
		Robot.grabby.setGrabMotor(clawSpeed);

		
//		Robot.grabby.setLifterMotor(position);
		
//		Robot.grabby.getLifterController().set(-Robot.deadzone(Robot.oi.operator.getRawAxis(OI.LIFT)));
		
		
		if(Robot.deadzone(Robot.oi.operator.getRawAxis(OI.LIFT), 0.04) != 0){
			//moving
			if(Robot.grabby.getLifterController().getControlMode() == ControlMode.PercentVbus){
	        	Robot.grabby.setLifterMotor(Robot.deadzone(-1 * Robot.oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED, 0.04));
			//not moving -> moving
			} else if(Robot.grabby.getLifterController().getControlMode() == ControlMode.Position){
	        	Robot.grabby.getLifterController().changeControlMode(ControlMode.PercentVbus);
			}
		} else {
			//moving -> not moving
			if(Robot.grabby.getLifterController().getControlMode() == ControlMode.PercentVbus){
	        	Robot.grabby.setLifterMotor(0);
	        	Robot.grabby.getLifterController().changeControlMode(ControlMode.Position);
	        	Robot.grabby.getLifterController().set(Robot.grabby.getLifterController().get());
			//not moving
			} else if(Robot.grabby.getLifterController().getControlMode() == ControlMode.Position){
			}
		}
		
		SmartDashboard.putBoolean("Grabby is in %Vbus", Robot.grabby.getLifterController().getControlMode() == ControlMode.PercentVbus);
		
	}
}

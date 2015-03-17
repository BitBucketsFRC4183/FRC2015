package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.GrabbyClose;
import org.bitbuckets.frc2015.command.GrabbyOpen;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class GrabbyThread extends SubsystemThread{
	
	GrabbyOpen grabbyOpen;
	GrabbyClose grabbyClose;

	public GrabbyThread(long iterTime, String name) {
		super(iterTime, name);
        grabbyOpen = new GrabbyOpen();
        grabbyClose = new GrabbyClose();
        Robot.oi.operatorGrabOpen.whenPressed(grabbyOpen);
        Robot.oi.operatorGrabClose.whenPressed(grabbyClose);
	}
	
	@Override
	protected void execute(){
        //moving -> not moving
        if(Robot.oi.operator.getRawAxis(OI.LIFT) == 0          &&   Robot.grabby.getLifterController().getControlMode() == ControlMode.Speed){
        	Robot.grabby.getLifterController().changeControlMode(ControlMode.Position);
        	Robot.grabby.getLifterController().set(Robot.grabby.getLifterController().get());
        //not moving -> moving
        } else if(Robot.oi.operator.getRawAxis(OI.LIFT) != 0   &&   Robot.grabby.getLifterController().getControlMode() == ControlMode.Position){
        	Robot.grabby.getLifterController().changeControlMode(ControlMode.Speed);
        	Robot.grabby.setLifterMotor(-1 * Robot.oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
        //moving
        } else if(Robot.oi.operator.getRawAxis(OI.LIFT) != 0){
        	Robot.grabby.setLifterMotor(-1 * Robot.oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
        //not moving
        } else{}
//    	Robot.grabby.setLifterMotor(-1 * Robot.oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
	}

}

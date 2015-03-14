package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.GrabbyClose;
import org.bitbuckets.frc2015.command.GrabbyOpen;

public class GrabbyThread extends SubsystemThread{
	
	GrabbyOpen grabbyOpen;
	GrabbyClose grabbyClose;

	public GrabbyThread(long iterTime) {
		super(iterTime);
        grabbyOpen = new GrabbyOpen();
        grabbyClose = new GrabbyClose();
        Robot.oi.operatorGrabOpen.whenPressed(grabbyOpen);
        Robot.oi.operatorGrabClose.whenPressed(grabbyClose);
	}
	
	@Override
	public void start(){
        super.start();
	}
	
	@Override
	public void run() {
		try {
			while(running){
				Thread.sleep(iterTime);
//		        //moving -> not moving
//		        if(oi.operator.getRawAxis(OI.LIFT) == 0          &&   grabby.getLifterController().getControlMode() == ControlMode.Speed){
//		        	grabby.getLifterController().changeControlMode(ControlMode.Position);
//		        	grabby.getLifterController().set(grabby.getLifterController().get());
//		        //not moving -> moving
//		        } else if(oi.operator.getRawAxis(OI.LIFT) != 0   &&   grabby.getLifterController().getControlMode() == ControlMode.Position){
//		        	grabby.getLifterController().changeControlMode(ControlMode.Speed);
//		        	grabby.setLifterMotor(-1 * oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
//		        //moving
//		        } else if(oi.operator.getRawAxis(OI.LIFT) != 0){
//		        	grabby.setLifterMotor(-1 * oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);
//		        //not moving
//		        } else{}
		    	Robot.grabby.setLifterMotor(-1 * Robot.oi.operator.getRawAxis(OI.LIFT) * RandomConstants.MAX_GRABBY_LIFTER_SPEED);

			}
		} catch (InterruptedException e) {
		}
	}

}

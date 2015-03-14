package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.StackyDown;
import org.bitbuckets.frc2015.command.StackyDownAll;
import org.bitbuckets.frc2015.command.StackyMoveDistance;
import org.bitbuckets.frc2015.command.StackyUp;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class StackyThread extends SubsystemThread{
	
	StackyUp upOne;
	StackyDown downOne;
	StackyDownAll downAll;
	StackyMoveDistance downBit;
	
	public StackyThread(long iterTime) {
		super(iterTime);
        upOne = new StackyUp();
        downOne = new StackyDown();
        downAll = new StackyDownAll();
        downBit = new StackyMoveDistance(-0.5);
        
//      Robot.oi.operatorToteUp.whenPressed(upOne);
        Robot.oi.operatorToteDown.whenPressed(downOne);
        Robot.oi.operatorToteDownAll.whenPressed(downAll);
        Robot.oi.operatorToteDownBit.whenPressed(downBit);
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
		        //***/*/*/*/*/*///*/*///HACK
		        if (Robot.oi.operatorToteUp.get() && Robot.stacky.getButtonsActive() && !upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
		            upOne.start();
		        }

		        if (!upOne.isRunning() && !downAll.isRunning() && !downOne.isRunning()) {
		            double speed = (Math.pow(Robot.oi.operator.getRawAxis(3), 3) - Math.pow(Robot.oi.operator.getRawAxis(4), 3))/2;
		            if(Math.abs(speed) >= RandomConstants.DEADZONE){
		                Robot.stacky.setClosedLoop(false);
		                Robot.stacky.setWinchMotor(speed);
		            }else if(Robot.stacky.getControlMode() == ControlMode.PercentVbus){
		                Robot.stacky.setClosedLoop(true);
		                Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV/ RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
		            }
		        }
		        //*/*////*/*/*///
			}
		} catch (InterruptedException e) {
		}
	}

}

package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.StackyDown;
import org.bitbuckets.frc2015.command.StackyDownAll;
import org.bitbuckets.frc2015.command.StackyMoveDistance;
import org.bitbuckets.frc2015.command.StackyUp;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class StackyThread extends SubsystemThread{
	
	private double position = 0;
	
	public StackyThread(long iterTime, String name) {
		super(iterTime, name);
	}
	
	@Override
	protected void execute(){
		//upOne when button sensors are activated & autopickup mode is engaged
		if(Robot.oi.operatorToteUp.get() && Robot.stacky.getButtonsActive()){
            Robot.upOne.start();
        }

		//maybe move these back into their respective subsystems
        if (!Robot.upOne.isRunning() && !Robot.downAll.isRunning() && !Robot.downOne.isRunning() && !Robot.downBit.isRunning()) {
            double speed = (Math.pow(Robot.oi.operator.getRawAxis(4), 3) - Math.pow(Robot.oi.operator.getRawAxis(3), 3))/2;
            if(Math.abs(speed) >= RandomConstants.DEFAULT_DEADZONE){
                Robot.stacky.setClosedLoop(false);
                Robot.stacky.setWinchMotor(speed);
            }else if(Robot.stacky.getControlMode() == ControlMode.PercentVbus){
            	position = Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV/ RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE;
            	Robot.stacky.setWinchMotor(0);
                Robot.stacky.setClosedLoop(true);
                Robot.stacky.setWinchPosition(position);
            }
        }
                
	}

}

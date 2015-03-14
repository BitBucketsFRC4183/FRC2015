package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;

public class DriveyThread extends SubsystemThread{

	public DriveyThread(long iterTime) {
		super(iterTime);
	}
	
	@Override
	public void start(){
        //Resets driving encoders to 0
        Robot.drivey.resetEncoders();
        //Sets the ControlMode of the drive controllers
        Robot.drivey.setEncoderSetting(ControlMode.Speed);
        super.start();
	}
	
	@Override
	public void run() {
		try {
			while(running){
				Thread.sleep(iterTime);
		        double theta = Math.atan2(Robot.oi.driver.getRawAxis(OI.GO), Robot.oi.driver.getRawAxis(OI.STRAFE));
		        double radius = Math.hypot(Robot.oi.driver.getRawAxis(OI.GO), Robot.oi.driver.getRawAxis(OI.STRAFE));
		        double sqrRadius = Robot.deadzone(Math.pow(radius, 1));
		        if(Robot.oi.driverSlowMode.get()){
		            Robot.drivey.drive(sqrRadius * Math.cos(theta) * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
		                    -1 * sqrRadius * Math.sin(theta) * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
		                    Math.pow(Robot.oi.driver.getRawAxis(OI.TURN), 1) * RandomConstants.MAX_ROT_SPEED * RandomConstants.slowModeRatio);
		        } else{
		            Robot.drivey.drive(sqrRadius * Math.cos(theta) * RandomConstants.MAX_TRANS_SPEED, -1 * sqrRadius * Math.sin(theta) * RandomConstants.MAX_TRANS_SPEED, Math.pow(Robot.oi.driver.getRawAxis(OI.TURN), 1) * RandomConstants.MAX_ROT_SPEED);
		        }
			}
		} catch (InterruptedException e) {
		}
	}

}

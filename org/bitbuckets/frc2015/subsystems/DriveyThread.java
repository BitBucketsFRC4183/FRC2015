package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveyThread extends SubsystemThread{
	
	private boolean first = true;
	/**
	 * If 1, then the robot is stacky-oriented. If -1, it is grabby-oriented.
	 */
	private long directionSwitchTime = 0;
	private long time1 = 0;
	private long time2 = 0;
	private long time3 = 0;
	private long time4 = 0;
	
	private double forwardInput = 0;
	private double lateralInput = 0;
	private double rotInput = 0;

	public DriveyThread(long iterTime, String name) {
		super(iterTime, name);
	}
	
	@Override
	public void execute(){
		time1 = System.currentTimeMillis();
		if(first){
	        //Resets driving encoders to 0
	        Robot.drivey.resetEncoders();
	        //Sets the ControlMode of the drive controllers
	        Robot.drivey.setEncoderSetting(ControlMode.Speed);
	        first = false;
		}
				
		forwardInput = Robot.deadzone(Robot.oi.driver.getRawAxis(OI.GO), 0.02);
		if(forwardInput != 0){
			forwardInput -= Math.signum(forwardInput) * 0.02;
		}
		
		lateralInput = Robot.deadzone(Robot.oi.driver.getRawAxis(OI.STRAFE), 0.02);
		if(lateralInput != 0){
			lateralInput -= Math.signum(forwardInput) * 0.02;
		}
		
		rotInput = Robot.deadzone(Robot.oi.driver.getRawAxis(OI.TURN), 0.02);
		if(rotInput != 0){
			rotInput -= Math.signum(forwardInput) * 0.02;
		}
		
		if(Robot.oi.driverReverse.get()){
			forwardInput *= -1;
			lateralInput *= -1;
		}
		
		SmartDashboard.putNumber("Driver forward input: ", forwardInput);
		SmartDashboard.putNumber("Driver lateral input: ", lateralInput);
		SmartDashboard.putNumber("Driver rotational input: ", rotInput);
//        if(false){
        if(Robot.oi.driverSlowMode.get()){
            Robot.drivey.drive(lateralInput * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
                          -1 * forwardInput * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
                               rotInput     * RandomConstants.MAX_ROT_SPEED   * RandomConstants.slowModeRatio);
        } else{
            time2 = System.currentTimeMillis();
            Robot.drivey.drive(lateralInput * RandomConstants.MAX_TRANS_SPEED,
            		      -1 * forwardInput * RandomConstants.MAX_TRANS_SPEED,
            		           rotInput     * RandomConstants.MAX_ROT_SPEED);
        	time3 = System.currentTimeMillis();
        }
        time4 = System.currentTimeMillis();
        SmartDashboard.putNumber("Time for drive() to get called", time3 - time2);
        SmartDashboard.putNumber("Time for execute() method to run", time4 - time1);
	}

}

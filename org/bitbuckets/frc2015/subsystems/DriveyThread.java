package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveyThread extends SubsystemThread{
	
	private boolean first = true;
	private long time1 = 0;
	private long time2 = 0;
	private long time3 = 0;
	private long time4 = 0;

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
		SmartDashboard.putNumber("Driver forward input: ", Robot.oi.driver.getRawAxis(OI.GO));
		SmartDashboard.putNumber("Driver lateral input: ", Robot.oi.driver.getRawAxis(OI.STRAFE));
//        if(false){
        if(Robot.oi.driverSlowMode.get()){
            Robot.drivey.drive(Robot.oi.driver.getRawAxis(OI.STRAFE) * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
                    -1 * Robot.oi.driver.getRawAxis(OI.GO) * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
                    Robot.oi.driver.getRawAxis(OI.TURN) * RandomConstants.MAX_ROT_SPEED * RandomConstants.slowModeRatio);
        } else{
            time2 = System.currentTimeMillis();
            Robot.drivey.drive(Robot.oi.driver.getRawAxis(OI.STRAFE) * RandomConstants.MAX_TRANS_SPEED, -1 * Robot.oi.driver.getRawAxis(OI.GO) * RandomConstants.MAX_TRANS_SPEED, Math.pow(Robot.oi.driver.getRawAxis(OI.TURN), 1) * RandomConstants.MAX_ROT_SPEED);
        	time3 = System.currentTimeMillis();
        }
        time4 = System.currentTimeMillis();
        SmartDashboard.putNumber("Time for drive() to get called", time3 - time2);
        SmartDashboard.putNumber("Time for execute() method to run", time4 - time1);
	}

}

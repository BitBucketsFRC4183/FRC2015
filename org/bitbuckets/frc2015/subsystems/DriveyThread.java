package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveyThread extends SubsystemThread{
	
	private boolean first = true;
	//the following time variable are for diagnostic purposes.
	private long time1 = 0;
	private long time2 = 0;
	private long time3 = 0;
	private long time4 = 0;
	
	private double forwardInput = 0;
	private double lateralInput = 0;
	private double rotInput = 0;
	
	private double oldForInput = 0;
	private double oldLatInput = 0;
	private double oldRotInput = 0;
	private boolean smoothMode = false;
	
	private int[] encoderValues = new int[4];

	public DriveyThread(long iterTime, String name) {
		super(iterTime, name);
		first = true;
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
		
		//deadzones out small input values to reduce jitter and drift
		forwardInput = Robot.deadzone(Robot.oi.driver.getRawAxis(OI.GO), 0.02);
		if(forwardInput != 0){
			//changes the scale back to still allow small, precise input.
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
		
		if(Robot.oi.driverSlowMode.get() && smoothMode == false){
			smoothMode = true;
			oldForInput = forwardInput;
			oldLatInput = lateralInput;
			oldRotInput = rotInput;
		} else if(smoothMode == true){
			if(Math.abs(forwardInput - oldForInput) > RandomConstants.SMOOTHING_THRESHOLD){
				oldForInput += RandomConstants.SMOOTH_CHANGE * Math.abs(forwardInput-oldForInput) / (forwardInput-oldForInput);
				forwardInput = oldForInput;
			}
			if(Math.abs(lateralInput - oldLatInput) > RandomConstants.SMOOTHING_THRESHOLD){
				oldLatInput += RandomConstants.SMOOTH_CHANGE * Math.abs(lateralInput-oldLatInput) / (lateralInput-oldLatInput);
				lateralInput = oldLatInput;
			}
			if(Math.abs(rotInput - oldRotInput) > RandomConstants.SMOOTHING_THRESHOLD){
				oldRotInput += RandomConstants.SMOOTH_CHANGE * Math.abs(rotInput-oldRotInput) / (rotInput-oldRotInput);
				rotInput = oldRotInput;
			}
		} else {
			smoothMode = false;
		}
		
		
		//allows the driver to reverse the input, effectively making grabby the front of the robot instead of stacky.
		if(Robot.oi.driverReverse.get()){
			forwardInput *= -1;
			lateralInput *= -1;
		}
		

		//the following commented line allows removal of the slowmode code. Use this if the OI call is too slow, but this shouldn't ever be a problem.
//        if(false){
		//allows the driver to scale down his driving input by 1/2, allowing for consistent but gentle movement.
//        if(Robot.oi.driverSlowMode.get()){
//            Robot.drivey.drive(lateralInput * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
//                          -1 * forwardInput * RandomConstants.MAX_TRANS_SPEED * RandomConstants.slowModeRatio,
//                               rotInput     * RandomConstants.MAX_ROT_SPEED   * RandomConstants.slowModeRatio);
//        } else{
            time2 = System.currentTimeMillis();
            Robot.drivey.drive(lateralInput * RandomConstants.MAX_TRANS_SPEED,
            		      -1 * forwardInput * RandomConstants.MAX_TRANS_SPEED,
            		           rotInput     * RandomConstants.MAX_ROT_SPEED);
        	time3 = System.currentTimeMillis();
//        }
        
        //grab encoder values for diagnostics
        encoderValues = Robot.drivey.getEncValues();

        if(RandomConstants.TESTING){
            //prints various diagnostic numbers
            SmartDashboard.putNumber("Driver forward input: ", forwardInput);
            SmartDashboard.putNumber("Driver lateral input: ", lateralInput);
            SmartDashboard.putNumber("Driver rotational input: ", rotInput);
            SmartDashboard.putNumber("Time for drive() to get called", time3 - time2);
            SmartDashboard.putNumber("Front Left Encoder Value:", encoderValues[0]);
            SmartDashboard.putNumber("Front Right Encoder Value:", encoderValues[1]);
            SmartDashboard.putNumber("Rear Left Encoder Value:", encoderValues[2]);
            SmartDashboard.putNumber("Rear Right Encoder Value:", encoderValues[3]);
        }

        time4 = System.currentTimeMillis();

        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Time for execute() method to run", time4 - time1);
        }
	}

}

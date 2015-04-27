package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.OI;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.subsystems.Shooty.ShootState;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootyThread extends SubsystemThread{
	
	private boolean shooting = false;
	private boolean started = false;
	
	//the following time variable are for diagnostic purposes.
	private long time1 = 0;
	private long time2 = 0;
	private long timeInit = 0;
	
	public ShootyThread(long iterTime, String name) {
		super(iterTime, name);
	}
	/**
	 * Executes the loop, shooting the tape measure depending on the state.
	 * <br/>
	 * While the state is in the {@link ShootState.SHOOTING} state, this runs the motor forwards at the speed set in the constants file.  
	 */
	@Override
	public void execute(){
		time1 = System.currentTimeMillis();

//		RandomConstants.SHOOT_TIME = SmartDashboard.getNumber("Shooting time");
//		RandomConstants.WIND_MULTIPLIER = SmartDashboard.getNumber("Wind relative");
//		RandomConstants.UNWIND_TIME = SmartDashboard.getNumber("Unwind time");
		
		if(Robot.shooty.getState() == ShootState.SHOOTING){
			Robot.shooty.setShootSpeed(RandomConstants.SHOOT_SPEED);
			if(System.currentTimeMillis() - timeInit < RandomConstants.UNWIND_TIME){
				Robot.shooty.setWindSpeed(RandomConstants.SHOOT_SPEED * RandomConstants.WIND_MULTIPLIER);
			}else{
				Robot.shooty.setWindSpeed(0);
			}
			
			if(!started){
				timeInit = System.currentTimeMillis();
				started = true;
			}else if(System.currentTimeMillis() - timeInit >= RandomConstants.SHOOT_TIME){
				Robot.shooty.setState(ShootState.OFF);
			}
		}else if(Robot.shooty.getState() == Shooty.ShootState.RETRACTING){
			Robot.shooty.setWindSpeed(-1 * RandomConstants.RETRACT_SPEED * RandomConstants.WIND_MULTIPLIER);
			
			if(!started){
				timeInit = System.currentTimeMillis();
				started = true;
			}else if(System.currentTimeMillis() - timeInit >= RandomConstants.WIND_TIME){
				Robot.shooty.setState(ShootState.OFF);
			}
		}else if(Robot.shooty.getState() == Shooty.ShootState.RETRACTSHORT){
			Robot.shooty.setWindSpeed(-1 * RandomConstants.RETRACT_SPEED * RandomConstants.WIND_MULTIPLIER);
			
			if(!started){
				timeInit = System.currentTimeMillis();
				started = true;
			}else if(System.currentTimeMillis() - timeInit >= RandomConstants.WIND_TIME_SHORT){
				Robot.shooty.setState(ShootState.OFF);
			}
		}else{
			Robot.shooty.setShootSpeed(0);
			Robot.shooty.setWindSpeed(0);
			started = false;
		}
		
        time2 = System.currentTimeMillis();

        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Time for execute() method to run", time2 - time1);
        }
	}

}

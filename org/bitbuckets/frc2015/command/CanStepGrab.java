package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CanStepGrab extends Command {
	
	long timeInit = 0;
	long duration = 0;
	double speed = 0;
	
    public CanStepGrab(double speed, long duration, boolean getDashboardVals) {
        requires(Robot.grabby);
        if(getDashboardVals){
	       	this.duration = (long) SmartDashboard.getNumber("CanStepDuration", duration);
	       	this.speed = SmartDashboard.getNumber("CanStepSpeed", speed);
        } else {
        	this.duration = duration;
        	this.speed = speed;
        }
    }

	/**
	 * NOTE - IN THE CURRENT IMPLEMENTATION, THIS RUNS THE CAN GRABBER MOTOR.
	 * <p>
	 * If possible, values will be retrieved from SmartDashboard, meaning that the parameters are defaults.
	 * 
	 * @param duration - How long this command should run in milliseconds.
	 * @param speed - How fast the motor should run, from -1.0 to 1.0.
	 */
    public CanStepGrab(long duration, double speed) {
        requires(Robot.grabby);
       	this.duration = (long) SmartDashboard.getNumber("CanStepDuration", duration);
       	this.speed = SmartDashboard.getNumber("CanStepSpeed", speed);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timeInit = System.currentTimeMillis();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.grabby.setGrabMotor(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return System.currentTimeMillis() - timeInit > duration;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.grabby.setGrabMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.grabby.setGrabMotor(0);
    }
}

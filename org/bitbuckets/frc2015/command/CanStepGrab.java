package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.TableKeyNotDefinedException;

/**
 *
 */
public class CanStepGrab extends Command {

    Thread motorThread;
    public boolean finished = false;
    double speed;
    long duration;
    boolean getDashboardVals;
    String key;
    
    public CanStepGrab(double speed, long duration, boolean getDashboardVals, String key){
    	requires(Robot.grabby);
        finished = false;
        this.speed = speed;
        this.duration = duration;
        this.getDashboardVals = getDashboardVals;
        this.key = key;
        if(getDashboardVals){
        	SmartDashboard.putBoolean("Getting dashvalues", true);
	        try{
	        	this.duration = (long) SmartDashboard.getNumber(key+": CanStepDuration");
	        } catch(TableKeyNotDefinedException e ) {
	        	SmartDashboard.putNumber(key+": CanStepDuration", duration);
	        }
	        try{
	        	this.speed = SmartDashboard.getNumber(key+": CanStepSpeed");
	        } catch(TableKeyNotDefinedException e ) {
	        	SmartDashboard.putNumber(key+": CanStepSpeed", speed);
	        }
        } else{
        	SmartDashboard.putBoolean("Getting dashvalues", false);
        }
    }

	
    public CanStepGrab(double speed, long duration, boolean getDashboardVals) {
        this(speed, duration, getDashboardVals, "");
    }

	/**
	 * NOTE - IN THE CURRENT IMPLEMENTATION, THIS RUNS THE CAN GRABBER MOTOR.
	 * <p>
	 * If possible, values will be retrieved from SmartDashboard, meaning that the parameters are defaults.
	 * 
	 * @param duration - How long this command should run in milliseconds.
	 * @param speed - How fast the motor should run, from -1.0 to 1.0.
	 */
    public CanStepGrab(double speed, long duration) {
        this(speed, duration, true, "");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        motorThread = new Thread(new MotorThread(speed, duration, getDashboardVals, key));
        motorThread.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute(){
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished;
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

    public class MotorThread implements Runnable{
        long duration = 0;
        double speed = 0;
        long timeInit = 0;

        public MotorThread(double speed, long duration, boolean getDashboardVals, String key){
            timeInit = System.currentTimeMillis();
            if(getDashboardVals){
                this.duration = (long) SmartDashboard.getNumber(key+": CanStepDuration", duration);
                this.speed = SmartDashboard.getNumber(key+"CanStepSpeed", speed);
            } else {
                this.duration = duration;
                this.speed = speed;
            }
        }

        @Override
        public void run(){
            Robot.grabby.setGrabMotor(speed);
            while(System.currentTimeMillis()-timeInit < duration){
                try{
                    Thread.sleep(1);
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            finished = true;
            Robot.grabby.setGrabMotor(0);
        }
    }
}

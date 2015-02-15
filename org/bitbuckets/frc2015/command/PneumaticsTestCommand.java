package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PneumaticsTestCommand extends Command {
	
	DoubleSolenoid ds;
	Long timeInit;
	int state;

    public PneumaticsTestCommand() {
    	requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        ds = new DoubleSolenoid(0, 1);
    	timeInit = System.currentTimeMillis();
    	state = 0;
    	System.out.println("Running PneumaticsTestCommand");
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	switch(state){
    		case 0:
    			state = 1;
    			System.out.println("Run the solenoid forward");
    			break;
    		case 1:
    	    	ds.set(DoubleSolenoid.Value.kForward);
    	    	if(System.currentTimeMillis()-timeInit>2000){
    	    		state = 2;
    	    		System.out.println("Run the solenoid backwards");
    	    	}
    	    	break;
    		case 2:
    			ds.set(DoubleSolenoid.Value.kReverse);
    			if(System.currentTimeMillis()-timeInit>4000){
    				state = 3;
    				System.out.println("Stop");
        			ds.set(DoubleSolenoid.Value.kOff);
    			}
    			break;
    		case 3:
    			break;
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(state == 3){
    		return true;
    	}
    	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class StackyUp extends Command {
    private boolean finished;
    private long timeInit;
    private boolean timeout;

    public StackyUp() {
        requires(Robot.stacky);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	finished = false;
    	timeout = false;
        Robot.stacky.setClosedLoop(false);
        Robot.stacky.startElevatorLatch(true);
        Robot.stacky.startReedBelow(true);
        if (Robot.stacky.getLimitTop()) {
            finished = true;
        }
        timeInit = System.currentTimeMillis();
    }

    /**
     * 
     */
    protected void execute() {
    	if(Robot.stacky.getElevatorLatch()){
    		finished = true;
    	}
    	//requires either that the command is blind, or that the button sensors are pressed
        if(RandomConstants.TESTING){
            SmartDashboard.putBoolean("StackyUp finshed (top switch) reached?", finished);
        }
    	Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
    }

    /**
     * Stops the command if the command is finished, the top carriage hits the top limit, or the timeout is reached.
     *
     * @return Whether the command has finished executing.
     */
    protected boolean isFinished() {
    	timeout = (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_UP_TIMEOUT;

        return finished || Robot.stacky.getLimitTop() || timeout;
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void end() {
    	finished = false;
    	Robot.stacky.setWinchMotor(0);
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV/ RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        Robot.stacky.stopReedBelow();
        Robot.stacky.stopElevatorLatch();
        
        if(timeout) {
        	SmartDashboard.putString("StackUpTimeoutStatus", "It's bad, you guys.");
        }
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void interrupted() {
    	finished = false;
        Robot.stacky.setWinchMotor(0);
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV / RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        Robot.stacky.stopReedBelow();
        Robot.stacky.stopElevatorLatch();
    }
}
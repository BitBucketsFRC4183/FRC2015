package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class StackyUp extends Command {
    private int state = 0;
    private long timeInit;
    private boolean timeout;

    public StackyUp() {
        requires(Robot.stacky);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.stacky.setClosedLoop(false);
        Robot.stacky.startReedAbove(false);
        if (!Robot.stacky.getLimitTop()) {
            state = 1;
        }
        timeInit = System.currentTimeMillis();
        timeout = false;
    }

    /**
     * This runs a state machine which runs the carriages at different speeds to get the carriages to go up one position.
     * <ul>
     * <li>State 1: Sets the winch to fast and waits until it's above the range of the upper switch</li>
     * <li>State 2: Sets the winch to fast and waits until the next carriage hits the lower switch</li>
     * <li>State 3: Sets the winch to slow and waits until the next carriage hits the upper switch</li>
     * </ul>
     */
    protected void execute() {
        switch (state) {
            case 1:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if (Robot.stacky.getReedAbove()) {
                    state = 2;
                    Robot.stacky.startElevatorLatch(true);
                    Robot.stacky.startReedBelow(true);
                }
                break;
            case 2:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if (Robot.stacky.getReedBelow()) {
                    state = 3;
                }
                if (Robot.stacky.getElevatorLatch()) {
                	state = 4;
                }
                break;
            case 3:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_SLOW_SPEED);
                if (Robot.stacky.getElevatorLatch()) {
                    state = 4;
                    Robot.stacky.upOne();
                }
                break;
            case 4:
            default:
                break;
        }
        Robot.stacky.logStuffs((int)(System.currentTimeMillis() - timeInit));
    }

    /**
     * Stops the command if the command is finished, the top carriage hits the top limit, or the timeout is reached.
     *
     * @return Whether the command has finished executing.
     */
    protected boolean isFinished() {
    	timeout = (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_UP_TIMEOUT;
        return state == 4 || Robot.stacky.getLimitTop() || (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_UP_TIMEOUT;
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void end() {
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV/ RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        state = 0;
        Robot.stacky.stopReedAbove();
        Robot.stacky.stopReedBelow();
        Robot.stacky.stopElevatorLatch();
        
        if(timeout) {
        	System.out.println("StackUp has TIMED OUT YOU FOOLS!");
        	SmartDashboard.putString("StackUpTimeoutStatus", "It's bad, you guys.");
        }
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void interrupted() {
        Robot.stacky.setWinchMotor(0);
        state = 0;
        Robot.stacky.stopReedAbove();
        Robot.stacky.stopReedBelow();
        Robot.stacky.stopElevatorLatch();
    }
}

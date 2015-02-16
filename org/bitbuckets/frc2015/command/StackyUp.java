package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class StackyUp extends Command {
    private int state = 0;
    private long timeInit;
    private long time;

    public StackyUp() {
        requires(Robot.stacky);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        Robot.stacky.setClosedLoop(false);
        if(!Robot.stacky.getLimitTop()) {
            state = 1;
        }
        timeInit = System.currentTimeMillis();
    }

    /**
     * This runs a state machine which runs the carriages at different speeds to get the carriages to go up one position.
     * <ul>
     *     <li>State 1: Sets the winch to fast and waits until it's above the range of the upper switch</li>
     *     <li>State 2: Sets the winch to fast and waits until the next carriage hits the lower switch</li>
     *     <li>State 3: Sets the winch to slow and waits until the next carriage hits the upper switch</li>
     * </ul>
     */
    protected void execute() {
        switch (state) {
            case 1:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if (!Robot.stacky.getReedAbove()) {
                    state = 2;
                }
                break;
            case 2:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if (Robot.stacky.getReedBelow()) {
                    state = 3;
                }
                break;
            case 3:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_SLOW_SPEED);
                if (Robot.stacky.getReedAbove()) {
                    state = 4;
                    Robot.stacky.upOne();
                }
                break;
            default:
                break;
        }
    }

    /**
     * Stops the command if the command is finished, the top carriage hits the top limit, or the timeout is reached.
     *
     * @return Whether the command has finished executing.
     */
    protected boolean isFinished() {
        return state == 4 || Robot.stacky.getLimitTop() || (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_TIMEOUT;
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void end() {
        Robot.stacky.setWinchMotor(0);
        state = 0;
    }

    /**
     * Stops the winch and resets the state machine.
     */
    protected void interrupted() {
        Robot.stacky.setWinchMotor(0);
        state = 0;
    }
}

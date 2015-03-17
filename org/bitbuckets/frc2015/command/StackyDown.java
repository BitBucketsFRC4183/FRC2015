package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/13/2015.
 */
public class StackyDown extends Command {
    private boolean finished;
    private long timeInit;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public StackyDown() {
        finished = false;
    }

    /**
     * Called just before this Command runs the first time. If the bottom limit switch is already active, it will end.
     */
    protected void initialize() {
        Robot.stacky.setClosedLoop(false);
        Robot.stacky.startReedAbove(true);
        if (Robot.stacky.getLimitBottom()) {
            finished = true;
        }
        timeInit = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        Robot.stacky.setWinchMotor(-1 * RandomConstants.CARRIAGE_SLOW_SPEED);
        if(Robot.stacky.getReedAbove()){
        	finished = true;
        }
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return finished || Robot.stacky.getLimitBottom() || (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_DOWN_ONE_TIMEOUT;
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        finished = false;
    	Robot.stacky.setWinchMotor(0);
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV / RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        Robot.stacky.stopReedAbove();
        Robot.stacky.stopReedBelow();
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
        finished = false;
        Robot.stacky.setWinchMotor(0);
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV / RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        Robot.stacky.stopReedAbove();
        Robot.stacky.stopReedBelow();
    }
}

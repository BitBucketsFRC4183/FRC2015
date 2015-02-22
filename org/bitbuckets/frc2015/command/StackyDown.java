package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/13/2015.
 */
public class StackyDown extends Command {
    private int state;
    private long timeInit;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public StackyDown() {
        state = 0;
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        Robot.stacky.setClosedLoop(false);
        if (!Robot.stacky.getLimitBottom()) {
            state = 1;
        }
        timeInit = System.currentTimeMillis();
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        System.out.println("Current down state: " + state);
        System.out.println("\tNum up: " + Robot.stacky.getNumUp());
        System.out.println("\t\tReed Above: " + Robot.stacky.getReedAbove());
        switch (state) {
            case 1:
                Robot.stacky.setWinchMotor(-1 * RandomConstants.CARRIAGE_FAST_SPEED);
                if (Robot.stacky.getReedAbove()) {
                    state = 2;
                }
                SmartDashboard.putNumber("Down State", 1);
                break;
            case 2:
                Robot.stacky.setWinchMotor(-1 * RandomConstants.CARRIAGE_FAST_SPEED);
                if (Robot.stacky.getReedBelow()) {
                    state = 3;
                }
                SmartDashboard.putNumber("Down State", 2);
                break;
            case 3:
                Robot.stacky.setWinchMotor(-1 * RandomConstants.CARRIAGE_FAST_SPEED);
                if (!Robot.stacky.getReedAbove()) {
                    state = 4;
                    Robot.stacky.downOne();
                    timeInit = System.currentTimeMillis();
                }
                SmartDashboard.putNumber("Down State", 3);
                break;
            default:
                break;
        }
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return state == 4 || Robot.stacky.getLimitBottom() || (System.currentTimeMillis() - timeInit) / 1000 >= RandomConstants.STACK_UP_TIMEOUT;
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV / RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
        state = 0;
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
        Robot.stacky.setWinchMotor(0);
        state = 0;
    }
}

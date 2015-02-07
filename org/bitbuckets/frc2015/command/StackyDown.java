package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
//TODO Fix Javadocs

/**
 * Created by James on 2/7/2015.
 */
public class StackyDown extends Command {
    int state = 0;

    /**
     * The constructor for this {@link edu.wpi.first.wpilibj.command.Command}. It should use <code>requires()</code> to tell the compiler which subsystem it uses.
     */
    public StackyDown() {
    }

    /**
     * Called just before this Command runs the first time.
     */
    protected void initialize() {
        state = 1;
    }

    /**
     * Called repeatedly when this Command is scheduled to run.
     */
    protected void execute() {
        switch (state){
            case 1:
                Robot.stacky.setWinchMotor(-1* RandomConstants.CARRIAGE_FAST_SPEED);
                if(Robot.stacky.getReedAbove()){
                    if(Robot.stacky.getNumUp() > 1) {
                        state = 2;
                    }
                    else {
                        state = 3;
                    }
                }
                break;
            case 2:
                Robot.stacky.setWinchMotor(-1* RandomConstants.CARRIAGE_FAST_SPEED);
                if(Robot.stacky.getReedBelow()){
                    state = 1;
                    Robot.stacky.downOne();
                }
                break;
            case 3:
                Robot.stacky.setWinchMotor(-1*RandomConstants.CARRIAGE_SLOW_SPEED);
                break;
            default:
                break;
        }
    }

    /**
     * Make this return true when this Command no longer needs to run <code>execute()</code>.
     */
    protected boolean isFinished() {
        return Robot.stacky.getLimitBottom() && Robot.stacky.getNumUp() == 0;
    }

    /**
     * Called once after <code>isFinished()</code> returns true.
     */
    protected void end() {
        Robot.stacky.setWinchMotor(0);
    }

    /**
     * Called when another command which requires one or more of the same subsystems is scheduled to run.
     */
    protected void interrupted() {
        Robot.stacky.setWinchMotor(0);
    }
}

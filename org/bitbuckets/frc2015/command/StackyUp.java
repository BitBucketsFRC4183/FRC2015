package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StackyUp extends Command {
    int state = 0;

    public StackyUp() {
        requires(Robot.stacky);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        state = 1;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        switch (state){
            case 1:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if(!Robot.stacky.getReedAbove()){
                    state = 2;
                }
                break;
            case 2:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_FAST_SPEED);
                if(Robot.stacky.getReedBelow()){
                    state = 3;
                }
                break;
            case 3:
                Robot.stacky.setWinchMotor(RandomConstants.CARRIAGE_SLOW_SPEED);
                if(Robot.stacky.getReedAbove()){
                    state = 4;
                    Robot.stacky.upOne();
                }
                break;
            default:
                break;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return state == 4 || Robot.stacky.getLimitTop();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.stacky.setWinchMotor(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        Robot.stacky.setWinchMotor(0);
    }
}

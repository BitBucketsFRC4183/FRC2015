package org.bitbuckets.frc2015.command;

import org.bitbuckets.frc2015.Robot;

import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Use this for the Can Burglar driving
 */
public class LowLatencyDrive extends Command {

    public LowLatencyDrive() {
        requires(Robot.drivey);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.drivey.setEncoderSetting(ControlMode.PercentVbus);
    	Robot.drivey.setControllers(-1, -1, 1, 1);
    	try {
			Thread.sleep(200);
		} catch (InterruptedException e) {}
    	SmartDashboard.putNumber("FLEncoder at end of 200ms of driving", Robot.drivey.getEncValues()[1]);
    	Robot.drivey.setEncoderSetting(ControlMode.Speed);
    	Robot.drivey.setControllers(0, 0, 0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

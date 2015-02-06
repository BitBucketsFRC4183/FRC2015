package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class DrivePower extends Command {
    double power = 0;

    public DrivePower(double pow) {
        requires(Robot.drivey);
        power = pow;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.drivey.driveRaw(power);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.oi.stick.getRawButton(7);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.drivey.putEncStuff();
        Robot.drivey.driveRaw(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

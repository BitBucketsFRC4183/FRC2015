package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class TiltUp extends Command {

    public TiltUp() {
        requires(Robot.tilty);
    }

    protected void initialize() {
        Robot.tilty.setTiltyUp(true);
        SmartDashboard.putString("Tilty", "Up");
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}

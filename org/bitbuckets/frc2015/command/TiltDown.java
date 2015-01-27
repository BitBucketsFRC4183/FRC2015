package org.bitbuckets.frc2015.command;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.Robot;

/**
 *
 */
public class TiltDown extends Command {

    public TiltDown() {
        requires(Robot.tilty);
    }

    protected void initialize() {
        Robot.tilty.setTiltyUp(false);
        SmartDashboard.putString("Tilter", "Down");
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

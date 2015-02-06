package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DriveTranslation;

/**
 *
 */
public class AutoDriveTest extends CommandGroup {

    public AutoDriveTest() {
        addSequential(new DriveTranslation(0., 5.));
        addSequential(new Wait(10));
        addSequential(new DriveTranslation(5., 0.));
        addSequential(new Wait(10));
        addSequential(new DriveTranslation(0., -5.));
        addSequential(new Wait(10));
        addSequential(new DriveTranslation(-5., 0.));
    }
}

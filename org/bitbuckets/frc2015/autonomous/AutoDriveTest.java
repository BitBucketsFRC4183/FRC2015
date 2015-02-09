package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DriveRectangular;

/**
 *
 */
public class AutoDriveTest extends CommandGroup {

    public AutoDriveTest() {
        addSequential(new DriveRectangular(0., 5.));
        addSequential(new Wait(10));
        addSequential(new DriveRectangular(5., 0.));
        addSequential(new Wait(10));
        addSequential(new DriveRectangular(0., -5.));
        addSequential(new Wait(10));
        addSequential(new DriveRectangular(-5., 0.));
    }
}

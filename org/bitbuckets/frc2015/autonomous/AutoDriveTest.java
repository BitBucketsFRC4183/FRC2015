package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DrivePolar;
import org.bitbuckets.frc2015.command.autonomous.DriveRectangular;
import org.bitbuckets.frc2015.command.autonomous.DriveRotate;

/**
 *
 */
public class AutoDriveTest extends CommandGroup {

    public AutoDriveTest() {
        addSequential(new DriveRectangular(0., 5.));
        addSequential(new Wait(2));
        addSequential(new DriveRectangular(5., 0.));
        addSequential(new Wait(2));
        addSequential(new DriveRectangular(0., -5.));
        addSequential(new Wait(2));
        addSequential(new DriveRectangular(-5., 0.));
//        addSequential(new DrivePolar(5, 3));
        addSequential(new Wait(3));
//        addSequential(new DriveRotate(5));
    }
}

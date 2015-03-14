package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DrivePolar;
import org.bitbuckets.frc2015.command.autonomous.DriveRectangular;
import org.bitbuckets.frc2015.command.autonomous.DriveRotate;
import org.bitbuckets.frc2015.command.autonomous.DriveTime;

/**
 *
 */
public class AutoDriveTest extends CommandGroup {

    public AutoDriveTest() {
        addSequential(new DriveTime(1L));
    }
}

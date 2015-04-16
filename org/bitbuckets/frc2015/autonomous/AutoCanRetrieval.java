package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.command.CanStepGrab;
import org.bitbuckets.frc2015.command.LowLatencyDrive;
import org.bitbuckets.frc2015.command.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCanRetrieval extends CommandGroup {
    
    public  AutoCanRetrieval() {
        addSequential(new CanStepGrab(1.0, 100, true, "Accel"));
        addSequential(new CanStepGrab(-1.0, 100, true, "Decel"));
        addSequential(new LowLatencyDrive(new int[]{-1, 1, -1, 1}, 200));
    }
}

package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.command.CanStepGrab;
import org.bitbuckets.frc2015.command.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCanRetrieval extends CommandGroup {
    
    public  AutoCanRetrieval() {
        addSequential(new CanStepGrab(1.0, 250, true));
        addSequential(new Wait(1.0));
        addSequential(new CanStepGrab(-0.5, 500, false));
    }
}

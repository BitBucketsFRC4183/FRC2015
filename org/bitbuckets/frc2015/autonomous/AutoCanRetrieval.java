package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.command.CanStepGrab;
import org.bitbuckets.frc2015.command.Wait;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCanRetrieval extends CommandGroup {
    
    public  AutoCanRetrieval() {
        addSequential(new CanStepGrab(0.75, 330, false));
        addSequential(new Wait(0.5));
        addSequential(new CanStepGrab(-0.15, 50, false));
        addSequential(new Wait(0.1));
        addSequential(new CanStepGrab(-0.5, 2000, false));
    }
}

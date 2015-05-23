package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.command.ShooterRetract;
import org.bitbuckets.frc2015.command.ShooterShoot;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DrivePolar;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoCanRetrieval extends CommandGroup {
    
    public AutoCanRetrieval() {
        addSequential(new ShooterShoot());
        addSequential(new Wait(0.250));
//        addSequential(new DrivePolar(6, Math.PI, 6));
        addSequential(new Wait(3));
        addSequential(new ShooterRetract());
    }
}

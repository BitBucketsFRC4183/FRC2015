package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.command.autonomous.DrivePolar;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoMove extends CommandGroup {
    
    public  AutoMove() {
        addSequential(new DrivePolar(7, Math.PI/4, 6));
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
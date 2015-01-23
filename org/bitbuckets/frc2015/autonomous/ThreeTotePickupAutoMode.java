package org.bitbuckets.frc2015.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotePickupAutoMode extends CommandGroup {
    
    public  ThreeTotePickupAutoMode() {
    	
    	/*
    	 * Things that need to happen during this autonomous:
    	 * 1: Movement between the three tote set ups. The totes are placed X meters apart, and the we need X meters
    	 *    of clearance to get around the bins without knocking them over
    	 * 2: Pickup totes reliably and quickly; as much as possible of this action should happen while driving to/away
    	 * 3: Drive into the auto-zone;
    	 */
    	
    	
    	
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

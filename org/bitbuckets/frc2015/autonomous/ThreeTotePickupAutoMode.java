package org.bitbuckets.frc2015.autonomous;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.command.StackyAutomatic;
import org.bitbuckets.frc2015.command.StackyDownAll;
import org.bitbuckets.frc2015.command.StackyMoveDistance;
import org.bitbuckets.frc2015.command.TiltDown;
import org.bitbuckets.frc2015.command.TiltUp;
import org.bitbuckets.frc2015.command.autonomous.DrivePolar;
import org.bitbuckets.frc2015.subsystems.Stacky;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class ThreeTotePickupAutoMode extends CommandGroup {
	
	StackyAutomatic stackyAutomatic = new StackyAutomatic();

    public ThreeTotePickupAutoMode() {
    	
    	//turn on button sensor pickup
    	addParallel(stackyAutomatic);
    	addSequential(new DrivePolar(0.2, 0, RandomConstants.MAX_TRANS_SPEED));
    	addSequential(new DrivePolar(0.7, 180, RandomConstants.MAX_TRANS_SPEED));
    	//move to second tote
    	addSequential(new DrivePolar(6.846, 270, RandomConstants.MAX_TRANS_SPEED));
    	addSequential(new DrivePolar(0.5, 0, RandomConstants.MAX_TRANS_SPEED));
    	//pick up second tote
    	addSequential(new DrivePolar(0.5, 180, RandomConstants.MAX_TRANS_SPEED));
    	//move to third tote
    	addSequential(new DrivePolar(6.846, 270, RandomConstants.MAX_TRANS_SPEED));
    	//turn off button sensor pickup
    	Stacky.automaticStacky = false;
    	//push third tote into autozone
    	addSequential(new DrivePolar(9, 0, RandomConstants.MAX_TRANS_SPEED));
    	//set down all totes
    	addSequential(new TiltDown());
    	addSequential(new StackyMoveDistance(-0.5));
    	//drive backwards a little bit to not be touching the totes
    	addSequential(new DrivePolar(0.5, 180, RandomConstants.MAX_TRANS_SPEED));
    	addSequential(new TiltUp());
    	addSequential(new StackyDownAll());

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

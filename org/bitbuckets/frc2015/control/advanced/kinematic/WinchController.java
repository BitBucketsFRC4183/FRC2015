package org.bitbuckets.frc2015.control.advanced.kinematic;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.control.advanced.MovementVector;

public class WinchController extends KinematicController{

	@Override
	public Double[] getOutputs(MovementVector input) {
		return new Double[]{input.get("velocity") * RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV};
	}

}

package org.bitbuckets.frc2015.control.advanced.kinematic;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.control.advanced.MovementVector;

public class LifterController extends KinematicController{

	@Override
	public Double[] getOutputs(MovementVector input) {
		return new Double[]{input.get("velocity") * RandomConstants.GRABBY_WINCH_DRUM_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV};
	}

}

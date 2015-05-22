package org.bitbuckets.frc2015.control.advanced.valueControl;

import org.bitbuckets.frc2015.control.advanced.MovementVector;

public class SimpleController extends ValueController<MovementVector, MovementVector>{
	
	private double Kp;
	private double Ka;
	
	public SimpleController(double Kp, double Ka){
		this.Kp = Kp;
		this.Ka = Ka;
	}

	@Override
	public MovementVector compute(MovementVector input, Object[] state) {
		return input.merge(new MovementVector.VectorBuilder().velocity(
				(input.getValue("position")) + input.get("velocity") + Ka*input.get("acceleration")).createVector());
	}

}

package org.bitbuckets.frc2015.control.advanced.valueControl;

import java.util.Arrays;

import org.bitbuckets.frc2015.control.advanced.MovementVector;

public class CascadingController extends ValueController<MovementVector, MovementVector>{
	
	private double Kp;
	private double Ka;
	
	public CascadingController(double Kp, double Ka){
		this.Kp = Kp;
		this.Ka = Ka;
	}

	@Override
	public MovementVector compute(MovementVector input, Object[] state) {
		if(state.length < 2){
			Object[] newArr = new Object[2];
			Arrays.fill(newArr, 0);
			for(int i = 0; i < state.length; i++){
				newArr[i] = state[i];
			}
			state = newArr;
		}
		MovementVector transOutput = null;
		if(input.getValue("position")>0 && input.getValue("velocity")>0 && input.getValue("acceleration")>0){
			transOutput = input.merge(new MovementVector.VectorBuilder().velocity(
					(input.getValue("position")-(double)state[0]) + input.get("velocity") + Ka*input.get("acceleration")).createVector());
		}
		MovementVector rotOutput = null;
		if(input.getValue("angle")>0 && input.getValue("angularVel")>0 && input.getValue("angularAccel")>0){
			rotOutput = input.merge(new MovementVector.VectorBuilder().angularVel(
					(input.getValue("angle")-(double)state[1]) + input.get("angularVel") + Ka*input.get("angularAccel")).createVector());
		}
		
		if(transOutput != null && rotOutput != null){
			return transOutput.merge(rotOutput);
		} else if(transOutput == null && rotOutput == null){
			return null;
		} else{
			return (transOutput == null) ? rotOutput : transOutput;
		}
	}

}

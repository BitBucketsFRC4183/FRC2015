package org.bitbuckets.frc2015.control.advanced;

/**
 * 
 * @author Miles Marchant
 * @version 0.9
 *
 */
public abstract class ValueController {
	
	public ValueController(){
	}
	
	public abstract MovementVector[] compute(Profile p, Double[] state, long time);

}

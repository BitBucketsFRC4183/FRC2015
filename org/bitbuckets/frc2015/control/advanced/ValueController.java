package org.bitbuckets.frc2015.control.advanced;

/**
 * 
 * @author Miles Marchant
 * @version 0.9
 *
 */
public abstract class ValueController<T, R> {
	
	public ValueController(){
	}
	
	public abstract R compute(T input, Object[] state);

}

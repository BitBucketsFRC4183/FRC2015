package org.bitbuckets.frc2015.control.advanced;

/**
 * 
 * @author Miles Marchant
 * @version 0.9
 *
 */
public abstract class AutonomousExecutable extends Thread{
	
	@Override
	public abstract void run();
	
	public abstract boolean isFinished();
	
	public abstract boolean verify();
	
	public abstract void cancel();

}

package org.bitbuckets.frc2015.control;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;

public class DigitalInputLatch implements Runnable {
	
	protected long sleepTime = 10L;
	protected DigitalInput input;
	protected Thread thread;
    protected boolean triggered = true;
    protected boolean goal;
		
	/**
	 * 
	 * @param input - The DigitalInput to be monitored.
	 * @param sleepTime - A long representing the delay between each check of the DigitalInput, in milliseconds.
	 */
	public DigitalInputLatch(DigitalInput input, Long sleepTime){
		this.input = input;
		this.sleepTime = sleepTime;
		thread = null;
	}
	
	/**
	 * Creates a DigitalInputLatch with a default sleepTime of 10 milliseconds.
	 * 
	 * @param input - The DigitalInput to be monitored.
	 */
	public DigitalInputLatch(DigitalInput input){
		this(input, 10L);
	}
	
	/**
	 * Begins a thread monitoring for the desired change in state of the DigitalInput. This requires a rising or falling edge to end.
	 * 
	 * @param goal the final target state of the DigitalInput.
	 * @throws NullPointerException
	 */
	public void start(boolean goal) throws NullPointerException{
		if(input == null){
			throw new NullPointerException("The referenced DigitalInput is null");
		}
		if(thread != null){
			System.out.println("Warning: attempted to start duplicate DigitalInputLatch thread.");
			return;
		}

        if(RandomConstants.TESTING){
		    SmartDashboard.putString("DigitalInputLatchThread "+input.getChannel(), "Thread started");
        }

		this.goal = goal;
		triggered = false;
		thread = new Thread(this);
		thread.start();
	}
	
	/**
	 * Detects falling or rising edges on the DigitalInput
	 */
	@Override
	public void run() {
		while(!input.get() == goal){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
		}
		while(!input.get() != goal){
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {}
		}
		triggered = true;
		stopThread();
		System.out.println("Edge detected on digitalinput " + input.getChannel());
        if(RandomConstants.TESTING){
            SmartDashboard.putString("DigitalInputLatchStatus "+input.getChannel(), "Edge detected");
        }
	}
	
	/**
	 * Stops this DigitalInputLatch's thread and allows for a new thread to be created.
	 */
	public void stopThread(){
		if(thread ==null){
			return;
		}
		thread.interrupt();
		thread = null;

        if(RandomConstants.TESTING){
            SmartDashboard.putString("DigitalInputLatchThread "+input.getChannel(), "Thread stopped");
        }
	}
	
	/**
	 * Returns whether or not the goal value has been reached.
	 * 
	 * @return
	 */
	public boolean getValue(){
		return triggered;
		
	}

}

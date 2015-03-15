package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SubsystemThread implements Runnable{
	
	protected long iterTime;
	protected boolean running = false;
	protected long prevTime = 0;
	protected long executeTime = 0;
	protected String name;

	public SubsystemThread(long iterTime, String name){
		this.iterTime = iterTime;
		Thread.currentThread().setName(name);
		this.name = name;
	}
	
	public SubsystemThread(long iterTime){
		this(iterTime, "Unnamed thread");
	}
	
	@Override
	public void run() {
		System.out.println("starting the superclass run loop for " + name);
		running = true;
		prevTime = System.currentTimeMillis();
		try {
			while(running){
				execute();
				executeTime = System.currentTimeMillis() - prevTime;
				if(iterTime > executeTime){
					Thread.sleep(iterTime - executeTime);
				}
				SmartDashboard.putNumber(name + " sleep time", iterTime - executeTime);
				SmartDashboard.putNumber(name + " cycle time", System.currentTimeMillis()-prevTime);
				prevTime = System.currentTimeMillis();
			}
		} catch (InterruptedException e) {
		}
	}
	
	protected void execute(){
		System.out.println("executing superclass thread");
	}

}

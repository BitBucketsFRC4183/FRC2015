package org.bitbuckets.frc2015.subsystems;

public class SubsystemThread implements Runnable{
	
	protected long iterTime;
	protected boolean running = false;

	public SubsystemThread(long iterTime){
		this.iterTime = iterTime;
	}
	
	public void start(){
		running = true;
		run();
	}
	
	@Override
	public void run() {
		try {
			while(running){
				Thread.sleep(iterTime);
			}
		} catch (InterruptedException e) {
		}
	}

}

package org.bitbuckets.frc2015.subsystems;

import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.command.TiltDown;
import org.bitbuckets.frc2015.command.TiltUp;



public class TiltyThread extends SubsystemThread{
	
	TiltUp tiltUp;
	TiltDown tiltDown;
	
	public TiltyThread(long iterTime) {
		super(iterTime);
        tiltUp = new TiltUp();
        tiltDown = new TiltDown();
		Robot.oi.operatorTiltUp.whenActive(tiltUp);
		Robot.oi.operatorTiltDown.whenActive(tiltDown);
	}
	
	@Override
	public void start(){
        super.start();
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

package org.bitbuckets.frc2015.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Parity;
import edu.wpi.first.wpilibj.SerialPort.StopBits;

/**
 * 
 * 
 * @author Miles
 *
 */
public final class SerialPortManager {
	
	public static SerialPort sp;
	
	private SerialPortManager(){
	}

	public static void init(){
		sp = new SerialPort(115200, SerialPort.Port.kUSB, 8, Parity.kNone, StopBits.kOne);
	}
	
	//TODO units
	public static double getAngularCorrectionVel(double intendedVel){
		return intendedVel - getAngularVel();
	}
	
	//TODO units
	public static double getAngularVel(){
		int numBytes = sp.getBytesReceived();
		byte[] out = sp.read(numBytes);
		//TODO check if right
		byte[] last = Arrays.copyOfRange(out, numBytes-5, numBytes-1);
		return ByteBuffer.wrap(last).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}
	
	/**
	 * NYI
	 * 
	 * @return
	 */
	public static float getHeading(){
		float heading = 0;
		byte[] out = sp.read(sp.getBytesReceived());
		return heading;
	}
	
	
}

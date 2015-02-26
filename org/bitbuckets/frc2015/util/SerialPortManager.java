package org.bitbuckets.frc2015.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Gyro;
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
	public static Gyro analogGyro;
	
	private SerialPortManager(){
	}

	public static void init(){
		//sp = new SerialPort(115200, SerialPort.Port.kUSB, 8, Parity.kNone, StopBits.kOne);
		analogGyro = new Gyro(RobotMap.ANALOG_GYRO);
		analogGyro.initGyro();
	}
	
	//TODO units
	public static double getAngularCorrectionVel(double intendedVel){
		return intendedVel - getAngularVel();
	}
	
	//TODO units
	public static double getAngularVel(){
		return analogGyro.getRate();
//		int numBytes = sp.getBytesReceived();
//		byte[] out = sp.read(numBytes);
//		//TODO check if right
//		byte[] last = Arrays.copyOfRange(out, numBytes-5, numBytes-1);
//		return ByteBuffer.wrap(last).order(ByteOrder.LITTLE_ENDIAN).getDouble();
	}
	
	/**
	 * Returns a relative heading in degrees that is the difference between the desired direction and the current direction
	 * 
	 * @param intendedHeading
	 * @return
	 */
	public static double getCorrectionHeading(double intendedHeading){
		return intendedHeading - getHeading();
	}
	
	/**
	 * Currently implemented for an analogGyro
	 * 
	 * @return
	 */
	public static double getHeading(){
		double heading = analogGyro.getAngle() % 360;
		//byte[] out = sp.read(sp.getBytesReceived());
		return heading;
	}
	
	/**
	 * Converts an angle in degrees to an angle in radians.
	 * 
	 * @param degrees
	 * @return
	 */
	public static double degreesToRadians(double degrees){
		if(Math.abs(degrees) >= 360){
			degrees %= 360;
		}
		return degrees * Math.PI / 180;
	}
	
	
}

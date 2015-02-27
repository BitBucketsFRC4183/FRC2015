package org.bitbuckets.frc2015.util;

import java.nio.ByteBuffer;
import javax.xml.bind.DatatypeConverter;

import org.bitbuckets.frc2015.RobotMap;

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
	 * Currently implemented for an serialPort
	 * 
	 * Check units, currently returns degrees
	 * 
	 * @return
	 */
	public static double getHeading(){
		
//		sp.enableTermination('\n');
//		String inputString = null;
//		double heading = 0;
//		double dt;
//		double mo;
//		
//		while(sp.getBytesReceived() >= 62){
//			inputString = sp.readString();
//		}
//		
//	    // 34B048C0,6D8104BB,3735CDBE,F86EF7BB,9C02533D,85002141,4E20,0
//		if (inputString != null && inputString.length() > 0) {
//		   	String [] inputStringArr = inputString.split(",");
//		   	// print(inputStringArr.length);
//		   	if(inputStringArr.length == 9) {           // 8 elements min
//		   		heading  = decodeFloat(inputStringArr[2]);
//		   		dt       = ByteBuffer.wrap(DatatypeConverter.parseHexBinary(inputStringArr[6])).getDouble();
//		   		mo       = Integer.parseInt(inputStringArr[7]);
//		   	}
//		}
		
		double heading = analogGyro.getAngle() % 360;
		    
		return heading;
	}

	private static double decodeFloat(String stringHex) {
		byte [] inData = new byte[4];
		inData = DatatypeConverter.parseHexBinary(stringHex);
		      
		return ByteBuffer.wrap(inData).getDouble();
	}

}

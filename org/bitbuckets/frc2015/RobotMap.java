package org.bitbuckets.frc2015;
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	////OI////
	public static int joyPort = 1;
	
	public static int opJoyPort = 2;
	
	//not sure where sensors go
	/**
	 * 
	 */
	public static int[] hallSensors = {0, 1, 2, 3, 4};
	/**
	 * The right side sensor to detect collision with a tote
	 */
	public static int bumperSensorRight = 5;
	/**
	 * The left side sensor to detect collision with a tote
	 */
	public static int bumperSensorLeft = 6;
	
	/////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////----CAN----/////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////
	
	/**
     * A right motor on the drivetrain.
     */
    public static final int R_MOTOR_A = 4;
    /**
     * A right motor on the drivetrain.
     */
    public static final int R_MOTOR_B = 5;
    /**
     * A right motor on the drivetrain.
     */
    public static final int R_MOTOR_C = 6;
    /**
     * A left motor on the drivetrain.
     */
    public static final int L_MOTOR_A = 1;
    /**
     * A left motor on the drivetrain.
     */
    public static final int L_MOTOR_B = 2;
    /**
     * A left motor on the drivetrain.
     */
    public static final int L_MOTOR_C = 3;
    /**
     * Motors driving the winch for the hooks.
     */
    public static final int WINCH_MOTOR = 8;
    /**
     * 
     */
    public static final int GRAB_TILT_MOTOR = 6;
    public static final int GRAB_TILT_MOTOR_ALT = 7;
    ///////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----SOLENOIDS----/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////////
    
    /**
     * The first port for the double solenoid controlling the grabber pistons.
     */
    public static final int GRABBY_SOLENOID_1 = 1;
    /**
     * The second port for the double solenoid controlling the grabber pistons.
     */
    public static final int GRABBY_SOLENOID_2 = 2;
    /**
     * The first port for the double solenoid controlling the winch shifter.
     */
    public static final int WINCH_SHIFTER_1 = 3;
    /**
     * The second port for the double solenoid controlling the winch shifter.
     */
    public static final int WINCH_SHIFTER_2 = 4;
    /**
     * The latch solenoid for firing
     */
    public static final int CATAPULT_LATCH = 5;
    
    ///////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----RELAY----/////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////////
    
    /**
     * The relay that's connected to the lights.
     */
    public static final int LIGHT_RELAY = 2;
    /**
     * The relay the compressor is plugged in to
     */
    public static final int COMPRESSOR_RELAY = 4;
    
    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----DIO----/////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////
    
    /**
     * The limit switch that says when the catapult is all the way back.
     */
    public static final int CATAPAULT_LIMIT_SWITCH = 1;
    /**
     * The right encoder for the drivetrain.
     */
    public static final int R_ENCODER_A = 2;
    /**
     * The right encoder for the drivetrain.
     */
    public static final int R_ENCODER_B = 2;
    /**
     * The left encoder for the drivetrain.
     */
    public static final int L_ENCODER_A = 4;
    /**
     * The left encoder for the drivetrain.
     */
    public static final int L_ENCODER_B = 4;
    /**
     * The switch that tells the compressor the max pressure
     */
    public static final int PRESSURE_SWITCH = 14;
}

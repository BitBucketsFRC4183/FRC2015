package org.bitbuckets.frc2015;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----CAN----/////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * A right motor on the drivetrain.
     */
    public static final int R_MOTOR_F = 4;
    /**
     * A right motor on the drivetrain.
     */
    public static final int R_MOTOR_R = 5;
    /**
     * A left motor on the drivetrain.
     */
    public static final int L_MOTOR_F = 1;
    /**
     * A left motor on the drivetrain.
     */
    public static final int L_MOTOR_R = 2;
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
    public static final int GRABBY_SOLENOID_1 = 0;
    /**
     * The second port for the double solenoid controlling the grabber pistons.
     */
    public static final int GRABBY_SOLENOID_2 = 1;
    /**
     * The first port for the double solenoid controlling the tilty pistons.
     */
    public static final int TILTY_SOLENOID_1 = 3;
    /**
     * The second port for the double solenoid controlling the tilty pistons.
     */
    public static final int TILTY_SOLENOID_2 = 4;
    /**
     * The first port for the double solenoid controlling the flippy pistons.
     */
    public static final int FLIPPY_SOLENOID_1 = 5;
    /**
     * The second port for the double solenoid controlling the flippy pistons.
     */
    public static final int FLIPPY_SOLENOID_2 = 6;

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
     * The port that the hall effect sensor with the singular magnet is connected to.
     */
    public static final int HALL_SING = 1;
    /**
     * The port that the hall effect sensor with the multiple magnets is connected to.
     */
    public static final int HALL_MULTI = 2;
    /**
     * The right side sensor to detect collision with a tote
     */
    public static final int BUMP_SENSE_RIGHT = 5;
    /**
     * The left side sensor to detect collision with a tote
     */
    public static final int BUMP_SENSE_LEFT = 6;
    /**
     * The switch that tells the compressor the max pressure
     */
    public static final int PRESSURE_SWITCH = 14;

    /////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////----WHEELS----//////////////////////////////
    /////////////////////////////////////////////////////////////////////////////

    /**
     * The X coordinate of the front left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FL_X = -11 / 12.0;
    /**
     * The Y coordinate of the front left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FL_Y = 14 / 12.0;
    /**
     * The X coordinate of the front right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FR_X = 11 / 12.0;
    /**
     * The Y coordinate of the front right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_FR_Y = 14 / 12.0;
    /**
     * The X coordinate of the rear left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RL_X = -11 / 12.0;
    /**
     * The Y coordinate of the rear left wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RL_Y = -14 / 12.0;
    /**
     * The X coordinate of the rear right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RR_X = 11 / 12.0;
    /**
     * The Y coordinate of the rear right wheel from the center of the robot in inches.
     */
    public static final double WHEEL_RR_Y = -14 / 12.0;
}

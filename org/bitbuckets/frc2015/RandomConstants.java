package org.bitbuckets.frc2015;


public class RandomConstants {
    /**
     * The gear ratio of the gearbox between the encoder and the wheel in a wheel module.
     */
    public static final double WHEEL_GEAR_RATIO = 70. / 24.;
    
    /**
     * Default settle time for autonomous driving, in milliseconds.
     */
    public static final int DEFAULT_SETTLE_TIME = 150;

    /**
     * 
     */
    public static final double FUDGE_FACTOR = 1;
    /**
     * Encoder ticks per revolution of a motor shaft
     */
    public static final double ENC_TICK_PER_REV = 256.0 * WHEEL_GEAR_RATIO;

    public static final double WHEEL_CIRCUMFERENCE = Math.PI / 3;

    public static final double STACKY_WINCH_DRUM_CIRCUMFERENCE = Math.PI / 6;

    public static final double GRABBY_WINCH_DRUM_CIRCUMFERENCE = 3 * Math.PI / 24;

    public static final double DEADZONE = 0.01;

    /**
     * Maximum translational speed in ft/s.(needs to be converted to ft/s)
     */
    public static final double MAX_TRANS_SPEED = 10;
    /**
     * Maximum translational acceleration in ft/s^2.(needs to be converted to ft/s^2)
     */
    public static final double MAX_TRANS_ACCEL = 5;
    /**
     * Maximum rotational speed in rad/s.
     */
    public static final double MAX_ROT_SPEED = 3;
    /**
     * Maximum rotational acceleration in rad/s^2
     */
    public static final double MAX_ROT_ACCEL = 3;

    public static final double MAX_GRABBY_LIFTER_SPEED = 2;

    public static final double MAX_GRABBY_LIFTER_ACCEL = 1;

    /**
     * The speed for the carriage while it is going fast.
     */
    public static final double CARRIAGE_FAST_SPEED = .7;
    /**
     * The speed for the carriage while it is going slowly.
     */
    public static final double CARRIAGE_SLOW_SPEED = .5;
    /**
     *
     */
    public static final double STACK_UP_TIMEOUT = 2;
    public static final double STACK_DOWN_TIMEOUT = 4;

    public static final boolean TESTING = true;

    //////////////////////////GRABBY/////////////////////////////
    /**
     *
     */
    public static final double GRABBY_CURRRENT_MAX = 8;
    /**
     *
     */
    public static final double GRAB_TIMEOUT = 2;
    public static final double GRAB_SPEED = 1;

    /**
     * The deadband after which the stick is seen as "pressed";
     */
    public static final double STICK_TO_BUTTON_DEADBAND = .5;

    /**
     *
     */
    public static final double DRIVE_KP = 0.2;
    public static final double DRIVE_KI = 0.01;
    public static final double DRIVE_KD = 0.01;
    public static final double DRIVE_KF = 0.01;
    public static final int DRIVE_IZONE = 50;

    public static final double STACKY_KP = .3;
    public static final double STACKY_KI = 0;
    public static final double STACKY_KD = 0;

    public static final double GRABBY_KP = .3;
    public static final double GRABBY_KI = 0;
    public static final double GRABBY_KD = 0;
}

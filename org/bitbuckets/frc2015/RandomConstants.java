package org.bitbuckets.frc2015;

public class RandomConstants {
    public static final double ENC_TICK_PER_REV = 1000. * 70. / 24.;

    public static final double WHEEL_CIRCUMFERENCE = Math.PI / 3;

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
    public static final double MAX_ROT_SPEED = 6;
    /**
     * Maximum rotational acceleration in rad/s^2
     */
    public static final double MAX_ROT_ACCEL = 3;

    /**
     * The speed for the carriage while it is going fast.
     */
    public static final double CARRIAGE_FAST_SPEED = 1;
    /**
     * The speed for the carriage while it is going slowly.
     */
    public static final double CARRIAGE_SLOW_SPEED = .5;
    /**
     *
     */
    public static final double STACK_TIMEOUT = 2;

    /**
     *
     */
    public static final double DRIVE_KP = .1;
    public static final double DRIVE_KI = 0;
    public static final double DRIVE_KD = 0;
    public static final double DRIVE_KF = 5.976;
}

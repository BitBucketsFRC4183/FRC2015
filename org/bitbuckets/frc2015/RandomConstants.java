package org.bitbuckets.frc2015;

public class RandomConstants {
    public static final double SPEED_K_EXSPMA = .01;

    public static final double ENC_TICK_TO_IN = 8;

    /**
     * Maximum translational speed in ft/s.(needs to be converted to ft/s)
     */
    public static final double MAX_TRANS_SPEED = 1.414;
    /**
     * Maximum rotational speed in rad/s.
     */
    public static final double MAX_ROT_SPEED = .67;

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
    public static final double DRIVE_KP = 1;
    public static final double DRIVE_KI = 0;
    public static final double DRIVE_KD = 0;
}

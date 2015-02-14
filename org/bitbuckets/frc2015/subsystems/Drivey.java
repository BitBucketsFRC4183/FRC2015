package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.RobotMap;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class Drivey extends Subsystem {
    private double FL;
    private double FR;
    private double RL;
    private double RR;

    private CANTalon flContr;
    private CANTalon frContr;
    private CANTalon rlContr;
    private CANTalon rrContr;

    private FileWriter csvWriterfrEnc;
    private FileWriter csvWriterflEnc;

//    private enum Wheels {
//        FRONT_LEFT(RobotMap.WHEEL_FL_X, RobotMap.WHEEL_FL_Y, RobotMap.WHEEL_FL_THETA, RobotMap.WHEEL_FL_MOTOR),
//        FRONT_RIGHT(RobotMap.WHEEL_FR_X, RobotMap.WHEEL_FR_Y, RobotMap.WHEEL_FR_THETA, RobotMap.WHEEL_FR_MOTOR),
//        REAR_LEFT(RobotMap.WHEEL_RL_X, RobotMap.WHEEL_RL_Y, RobotMap.WHEEL_RL_THETA, RobotMap.WHEEL_RL_MOTOR),
//        REAR_RIGHT(RobotMap.WHEEL_RR_X, RobotMap.WHEEL_RR_Y, RobotMap.WHEEL_RR_THETA, RobotMap.WHEEL_RR_MOTOR);
//
//        double x;
//        double y;
//        double theta;
//        int canIndex;
//
//        Wheels(double xi, double yi, double thetai, int canIndexi) {
//            x = xi;
//            y = yi;
//            theta = thetai;
//            canIndex = canIndexi;
//        }
//    }

    /**
     * The constructor. Sets up the speeds and talons with k values for the internal PID controller.
     */
    public Drivey() {
        super();

        //Write to a csv file
        try {
            csvWriterfrEnc = new FileWriter("///TextFiles//XVelData.csv");
            csvWriterfrEnc.append("frEnc");
            csvWriterfrEnc.append(",");
            csvWriterfrEnc.append("time");
            csvWriterflEnc = new FileWriter("///TextFiles//YVelData.csv");
            csvWriterflEnc.append("flEnc");
            csvWriterflEnc.append(",");
            csvWriterflEnc.append("time");
        } catch (IOException e){
        }

        FL = 0;
        FR = 0;
        RL = 0;
        RR = 0;

        flContr = new CANTalon(RobotMap.WHEEL_FL_MOTOR);
        frContr = new CANTalon(RobotMap.WHEEL_FR_MOTOR);
        rlContr = new CANTalon(RobotMap.WHEEL_RL_MOTOR);
        rrContr = new CANTalon(RobotMap.WHEEL_RR_MOTOR);

        resetEncoders();

        flContr.changeControlMode(CANTalon.ControlMode.Speed);
        frContr.changeControlMode(CANTalon.ControlMode.Speed);
        rlContr.changeControlMode(CANTalon.ControlMode.Speed);
        rrContr.changeControlMode(CANTalon.ControlMode.Speed);

        flContr.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD);
        frContr.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD);
        rlContr.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD);
        rrContr.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD);

        flContr.reverseSensor(true);
        frContr.reverseSensor(true);
        rlContr.reverseSensor(true);
        rrContr.reverseSensor(true);

        SmartDashboard.putNumber("KP", RandomConstants.DRIVE_KP);
        SmartDashboard.putNumber("KI", RandomConstants.DRIVE_KI);
        SmartDashboard.putNumber("KD", RandomConstants.DRIVE_KD);
        SmartDashboard.putNumber("KF", RandomConstants.DRIVE_KF);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    public void resetEncoders() {
        flContr.setPosition(0);
        frContr.setPosition(0);
        rlContr.setPosition(0);
        rrContr.setPosition(0);
    }

    public void resetPIDs(){
        flContr.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"));
        frContr.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"));
        rlContr.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"));
        rrContr.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"));
    }

    /**
     * Drives the robot with x, y, and rotational velocity. Gets the intended speed of the wheels, limits it so that no speed is over 1 and but the vector directions are the same, does gyro stuff(not yet), and sets the velocity PID setpoint on the motors.
     * NOT IN FEET YET
     *
     * @param vx    The intended X velocity of the robot.
     * @param vy    The intended Y velocity of the robot.
     * @param omega The intended rotation of the robot around the center of rotation.
     */
    public void drive(double vx, double vy, double omega){



        //Gets the wheel speed
        FL = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FL_X, RobotMap.WHEEL_FL_Y, RobotMap.WHEEL_FL_THETA, vx, vy, omega);
        FR = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FR_X, RobotMap.WHEEL_FR_Y, RobotMap.WHEEL_FR_THETA, vx, vy, omega);
        RL = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RL_X, RobotMap.WHEEL_RL_Y, RobotMap.WHEEL_RL_THETA, vx, vy, omega);
        RR = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RR_X, RobotMap.WHEEL_RR_Y, RobotMap.WHEEL_RR_THETA, vx, vy, omega);

        //Speed limiiter
//        double kLimit = FL;
//        if (FR > kLimit) {
//            kLimit = FR;
//        }
//        if (RL > kLimit) {
//            kLimit = RL;
//        }
//        if (RR > kLimit) {
//            kLimit = RR;
//        }
//
//        if (kLimit > 1) {
//            kLimit = Math.abs(1 / kLimit);
//            FL = FL * kLimit;
//            FR = FR * kLimit;
//            RL = RL * kLimit;
//            RR = RR * kLimit;
//        }

        flContr.set(FL * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        frContr.set(FR * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        rlContr.set(RL * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        rrContr.set(RR * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);

        try {
            csvWriterfrEnc.append("" + frContr.getEncVelocity());
            csvWriterfrEnc.append(",");
            csvWriterfrEnc.append("" + System.currentTimeMillis());
            csvWriterflEnc.append("" + flContr.getEncVelocity());
            csvWriterflEnc.append(",");
            csvWriterflEnc.append("" + System.currentTimeMillis());
        } catch(IOException e){

        }

        SmartDashboard.putNumber("FLEnc", flContr.getEncPosition());
        SmartDashboard.putNumber("FREnc", frContr.getEncPosition());
        SmartDashboard.putNumber("RLEnc", rlContr.getEncPosition());
        SmartDashboard.putNumber("RREnc", rrContr.getEncPosition());

        SmartDashboard.putNumber("FL", FL * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        SmartDashboard.putNumber("FR", FR * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        SmartDashboard.putNumber("RL", RL * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
        SmartDashboard.putNumber("RR", RR * RandomConstants.ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE);
    }

    /**
     * Gets the speed of a wheel for omni-directional drive with wheels in any configuration. <code>vwp</code> is the
     * velocity vector for the wheel without accounting for the fact that it can't rotate. <code>thetaR</code> is the
     * angle between the center of rotation and the wheel. All angles are relative to standard coordinate axes if looked
     * at from the top.
     *
     * @param xc    The x coordinate of the intended center of rotation.
     * @param yc    The y coordinate of the intended center of rotation.
     * @param xw    The x coordinate of the center of the wheel.
     * @param yw    The y coordinate of the center of the wheel.
     * @param theta The angle of the center of the wheel.
     * @param vxc   The intended x velocity of the robot.
     * @param vyc   The intended y velocity of the robot.
     * @param rotc  The intended rotational velocity of the robot around the intended center of rotation.
     * @return
     */
    public double getWheelSpeed(double xc, double yc, double xw, double yw, double theta, double vxc, double vyc, double rotc) {
        double vtan = Math.sqrt(Math.pow((xc - xw), 2) + Math.pow((yc - yw), 2)) * rotc;
        double thetaR = Math.atan2((yw - yc), (xw - xc));
        double speed = vtan * Math.abs(Math.cos(theta - (thetaR + Math.PI / 2))) + vxc * Math.cos(theta) + vyc * Math.sin(theta);
        return speed;
    }
}


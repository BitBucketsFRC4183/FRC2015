package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.util.EmptyPIDOutput;
import org.bitbuckets.frc2015.util.EmptyPIDSource;
import org.bitbuckets.frc2015.util.SerialPortManager;

import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class Drivey extends Subsystem {
	//wheel speed variables
    private double FL;
    private double FR;
    private double RL;
    private double RR;
    
    private CANTalon flController;
    private CANTalon frController;
    private CANTalon rlController;
    private CANTalon rrController;

    public PIDController headingController;
    public EmptyPIDSource serialGyroSource;
    public EmptyPIDOutput headingOut;

    private FileWriter csvWriterfrEnc;
    private FileWriter csvWriterflEnc;

    /**
     * The constructor. Sets up the speeds and CANTalons with k values for the internal PID controller.
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
        } catch (IOException e) {
        }

        //If changed to serial port gyro/accel/magnetometer, you need to setup an EmptyPIDSource for it
        serialGyroSource = new EmptyPIDSource();
        headingOut = new EmptyPIDOutput();
        headingController = new PIDController(0.3, 0.001, 0, 0, SerialPortManager.analogGyro, headingOut, 20);
        
        FL = 0;
        FR = 0;
        RL = 0;
        RR = 0;

        //initialize CANTalons with the proper indexes
        flController = new CANTalon(RobotMap.WHEEL_FL_MOTOR);
        frController = new CANTalon(RobotMap.WHEEL_FR_MOTOR);
        rlController = new CANTalon(RobotMap.WHEEL_RL_MOTOR);
        rrController = new CANTalon(RobotMap.WHEEL_RR_MOTOR);

        //sets the wheel encoders to 0
        resetEncoders();

        //puts CANTalons on Speed PID control
        flController.changeControlMode(CANTalon.ControlMode.Speed);
        frController.changeControlMode(CANTalon.ControlMode.Speed);
        rlController.changeControlMode(CANTalon.ControlMode.Speed);
        rrController.changeControlMode(CANTalon.ControlMode.Speed);

        //Inputs the PID values outlined in RandomConstants
        flController.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD, RandomConstants.DRIVE_KF, RandomConstants.DRIVE_IZONE, 0, 0);
        frController.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD, RandomConstants.DRIVE_KF, RandomConstants.DRIVE_IZONE, 0, 0);
        rlController.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD, RandomConstants.DRIVE_KF, RandomConstants.DRIVE_IZONE, 0, 0);
        rrController.setPID(RandomConstants.DRIVE_KP, RandomConstants.DRIVE_KI, RandomConstants.DRIVE_KD, RandomConstants.DRIVE_KF, RandomConstants.DRIVE_IZONE, 0, 0);

        //reverses the sensors on each CANTalon
        flController.reverseSensor(true);
        frController.reverseSensor(true);
        rlController.reverseSensor(true);
        rrController.reverseSensor(true);

        if (RandomConstants.TESTING) {
            SmartDashboard.putNumber("KP", RandomConstants.DRIVE_KP);
            SmartDashboard.putNumber("KI", RandomConstants.DRIVE_KI);
            SmartDashboard.putNumber("KD", RandomConstants.DRIVE_KD);
            SmartDashboard.putNumber("KF", RandomConstants.DRIVE_KF);
            SmartDashboard.putNumber("IZONE", RandomConstants.DRIVE_IZONE);
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Sets the values of the four drive encoders to 0.
     */
    public void resetEncoders() {
        flController.setPosition(0);
        frController.setPosition(0);
        rlController.setPosition(0);
        rrController.setPosition(0);
    }

    /**
     * Sets the PID values on the four CANTalons to the values input through SmartDashboard.
     */
    public void resetPIDs() {
    	try{
	        flController.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"), SmartDashboard.getNumber("KF"), (int) SmartDashboard.getNumber("IZONE"), 0, 0);
	        frController.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"), SmartDashboard.getNumber("KF"), (int) SmartDashboard.getNumber("IZONE"), 0, 0);
	        rlController.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"), SmartDashboard.getNumber("KF"), (int) SmartDashboard.getNumber("IZONE"), 0, 0);
	        rrController.setPID(SmartDashboard.getNumber("KP"), SmartDashboard.getNumber("KI"), SmartDashboard.getNumber("KD"), SmartDashboard.getNumber("KF"), (int) SmartDashboard.getNumber("IZONE"), 0, 0);
    	} catch(Exception e){
    		e.printStackTrace();
    	}

        SmartDashboard.putNumber("KP", flController.getP());
        SmartDashboard.putNumber("KI", flController.getI());
        SmartDashboard.putNumber("KD", flController.getD());
        SmartDashboard.putNumber("KF", flController.getF());
        SmartDashboard.putNumber("IZONE", flController.getIZone());
    }

    /**
     * Drives the robot with x, y, and rotational velocity. Gets the intended speed of the wheels, limits it so that no speed is over 1 and but the vector directions are the same, does gyro stuff(not yet), and sets the velocity PID setpoint on the motors.
     * NOT IN FEET YET
     *
     * @param vx    The intended X velocity of the robot.
     * @param vy    The intended Y velocity of the robot.
     * @param omega The intended rotation of the robot around the center of rotation.
     */
    public void drive(double vx, double vy, double omega) {

        //Gets the wheel speed
        FL = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FL_X, RobotMap.WHEEL_FL_Y, RobotMap.WHEEL_FL_THETA, vx, vy, omega);
        FR = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_FR_X, RobotMap.WHEEL_FR_Y, RobotMap.WHEEL_FR_THETA, vx, vy, omega);
        RL = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RL_X, RobotMap.WHEEL_RL_Y, RobotMap.WHEEL_RL_THETA, vx, vy, omega);
        RR = getWheelSpeed(RobotMap.CENTER_X, RobotMap.CENTER_Y, RobotMap.WHEEL_RR_X, RobotMap.WHEEL_RR_Y, RobotMap.WHEEL_RR_THETA, vx, vy, omega);

        //Speed limiter
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

        flController.set(FL * RandomConstants.DRIVEY_ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE * RandomConstants.FUDGE_FACTOR);
        frController.set(FR * RandomConstants.DRIVEY_ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE * RandomConstants.FUDGE_FACTOR);
        rlController.set(RL * RandomConstants.DRIVEY_ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE * RandomConstants.FUDGE_FACTOR);
        rrController.set(RR * RandomConstants.DRIVEY_ENC_TICK_PER_REV / RandomConstants.WHEEL_CIRCUMFERENCE * RandomConstants.FUDGE_FACTOR);

//        try {
//            csvWriterfrEnc.append("" + frController.getEncVelocity());
//            csvWriterfrEnc.append(",");
//            csvWriterfrEnc.append("" + System.currentTimeMillis());
//            csvWriterflEnc.append("" + flController.getEncVelocity());
//            csvWriterflEnc.append(",");
//            csvWriterflEnc.append("" + System.currentTimeMillis());
//        } catch (IOException e) {
//
//        }

        if (RandomConstants.TESTING) {
            SmartDashboard.putNumber("FLEnc", flController.getEncPosition());
            SmartDashboard.putNumber("FREnc", frController.getEncPosition());
            SmartDashboard.putNumber("RLEnc", rlController.getEncPosition());
            SmartDashboard.putNumber("RREnc", rrController.getEncPosition());

//            SmartDashboard.putNumber("FLEncVel", flController.getEncVelocity());
//            SmartDashboard.putNumber("FREncVel", frController.getEncVelocity());
//            SmartDashboard.putNumber("RLEncVel", rlController.getEncVelocity());
//            SmartDashboard.putNumber("RREncVel", rrController.getEncVelocity());
//
//            SmartDashboard.putNumber("FL Speed", FL);
//            SmartDashboard.putNumber("FR Speed", FR);
//            SmartDashboard.putNumber("RL Speed", RL);
//            SmartDashboard.putNumber("RR Speed", RR);
        }
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
     * @return The speed that the wheel should be set to based on the inputs.
     */
    public double getWheelSpeed(double xc, double yc, double xw, double yw, double theta, double vxc, double vyc, double rotc) {
        double vTan = Math.sqrt(Math.pow((xc - xw), 2) + Math.pow((yc - yw), 2)) * rotc;
        double thetaR = Math.atan2((yw - yc), (xw - xc));
        return vTan * Math.abs(Math.cos(theta - (thetaR + Math.PI / 2))) + vxc * Math.cos(theta) + vyc * Math.sin(theta);
    }

    /**
     * Changes all four drive encoders to a new PID control mode.
     * 
     * @param mode
     * @see CANTalon.ControlMode
     */
    public void setEncoderSetting(CANTalon.ControlMode mode) {
        flController.changeControlMode(mode);
        frController.changeControlMode(mode);
        rlController.changeControlMode(mode);
        rrController.changeControlMode(mode);
    }

    /**
     * Sets each of the four CANTalons to new setpoints.
     * 
     * @param flSet - Setpoint for the front left CANTalon.
     * @param frSet - Setpoint for the front right CANTalon.
     * @param rlSet - Setpoint for the back left CANTalon.
     * @param rrSet - Setpoint for the back right CANTalon.
     */
    public void setControllers(double flSet, double frSet, double rlSet, double rrSet) {
        flController.set(flSet);
        frController.set(frSet);
        rlController.set(rlSet);
        rrController.set(rrSet);
    }
    
    /**
     * 
     * @return
     */
    public int[] getEncValues(){
    	return new int[]{flController.getEncPosition(), frController.getEncPosition(), rlController.getEncPosition(), rrController.getEncPosition()};
    }

    /**
     *
     * @param FL
     * @param FR
     * @param RL
     * @param RR
     */
    public void setFeedForward(double FL, double FR, double RL, double RR){
        flController.setF(FL);
        frController.setF(FR);
        rlController.setF(RL);
        rrController.setF(RR);
    }

}


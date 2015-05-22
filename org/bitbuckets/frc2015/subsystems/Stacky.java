package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;
import org.bitbuckets.frc2015.RobotMap;
import org.bitbuckets.frc2015.control.DigitalInputLatch;
import org.bitbuckets.frc2015.control.ElevatorStopLatch;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 */
public class Stacky extends Subsystem {

    /**
     * This hack is for the StackyAutomatic command
     */
    public static boolean automaticStacky = false;
    private BufferedWriter logthing;
    /**
     * The CANTalon that controls the elevator winch
     */
    private CANTalon winch;

    /**
     * The upper control reed switch
     */
    private DigitalInput reedAbove;
    /**
     * The Latch wrapper for the upper reed switch
     */
    private DigitalInputLatch reedAboveLatch;
    /**
     * The upper control reed switch
     */
    private DigitalInput reedBelow;
    /**
     * The Latch wrapper for the lower reed switch
     */
    private DigitalInputLatch reedBelowLatch;
    /**
     * Allows the top reed sensor to stop the elevator on a short timescale.
     */
    private ElevatorStopLatch elevatorStopLatch;

    //deprecated since the limit switches are plugged directly into the Talon SRX.
//    private DigitalInput limitTop;
//    private DigitalInput limitBottom;

    /**
     * The left bumper for running into the totes
     */
    private DigitalInput bumperLeft;
    /**
     * The right bumper for running into the totes
     */
    private DigitalInput bumperRight;

    /**
     * The number of positions the elevator has gone up
     */
    private int numUp;
	private boolean isReset = false;

    /**
     * The constructor. Initializes everything.
     */
    public Stacky() {
    	
		SmartDashboard.putBoolean("Winch enc too low", false);
    	
		//initializes CANTalon for the winch
        winch = new CANTalon(RobotMap.WINCH_MOTOR);

        //enables both limit switches for the winch
        winch.enableLimitSwitch(true, true);

        //sets the PID values for the winch
        winch.setPID(RandomConstants.STACKY_KP, RandomConstants.STACKY_KI, RandomConstants.STACKY_KD, 0.0, RandomConstants.STACKY_IZONE, 0, 0);

        //initializes all of the reed switches and their latches
        reedAbove = new DigitalInput(RobotMap.HALL_ABOVE);
        reedBelow = new DigitalInput(RobotMap.HALL_BELOW);
        reedAboveLatch = new DigitalInputLatch(reedAbove, 2L);
        reedBelowLatch = new DigitalInputLatch(reedBelow, 2L);
        elevatorStopLatch = new ElevatorStopLatch(reedAbove, 5L);

        //again, deprecated since the limit switches go directly into the Talon SRX
//        limitTop = new DigitalInput(RobotMap.SWITCH_TOP);
//        limitBottom = new DigitalInput(RobotMap.SWITCH_BOTTOM);

        bumperLeft = new DigitalInput(RobotMap.BUMP_SENSE_LEFT);
        bumperRight = new DigitalInput(RobotMap.BUMP_SENSE_RIGHT);

        numUp = 0;

        try {
            logthing = new BufferedWriter(new FileWriter("///TextFiles//LogStack.csv"));
            logthing.write("Time;Reed Above;Reed Below;Encoder");
            logthing.newLine();
            logthing.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Stacky KP:", RandomConstants.STACKY_KP);
            SmartDashboard.putNumber("Stacky KI:", RandomConstants.STACKY_KI);
            SmartDashboard.putNumber("Stacky KD:", RandomConstants.STACKY_KD);
            SmartDashboard.putNumber("Stacky KF:", RandomConstants.STACKY_KF);
            SmartDashboard.putNumber("Stacky IZONE:", RandomConstants.STACKY_IZONE);

            SmartDashboard.putNumber("Input Stacky KP:", RandomConstants.STACKY_KP);
            SmartDashboard.putNumber("Input Stacky KI:", RandomConstants.STACKY_KI);
            SmartDashboard.putNumber("Input Stacky KD:", RandomConstants.STACKY_KD);
            SmartDashboard.putNumber("Input Stacky KF:", RandomConstants.STACKY_KF);
            SmartDashboard.putNumber("Input Stacky IZONE:", RandomConstants.STACKY_IZONE);
        }
    }

    /**
     * Starts the command that is supposed to start when the subsystem is initialized.
     */
    public void initDefaultCommand() {

    }

    /**
     * Sets the speed of the winch if the {@link edu.wpi.first.wpilibj.CANTalon.ControlMode} is <code>Disabled</code> or open loop.
     *
     * @param speed The speed to set the winch.
     */
    public void setWinchMotor(double speed) {
//    	if(winch.getEncPosition() <= -400 && isReset){
//    		speed = 0;
//    		SmartDashboard.putBoolean("Winch enc too low", true);
//    	}
        if(RandomConstants.TESTING){
            SmartDashboard.putNumber("Running winch at: ", speed);
            SmartDashboard.putString("Stacky winch mode:", winch.getControlMode().toString());
        }
    	if (winch.getControlMode() == CANTalon.ControlMode.PercentVbus) {
            winch.set(speed);
            if (RandomConstants.TESTING) {
                SmartDashboard.putNumber("winch encoder", winch.getEncPosition());
            }
        } else {
            SmartDashboard.putString("Uhh", "You tried to set open loop speed with closed loop control");
        }
    }

    /**
     * Sets the position of the winch if the {@link edu.wpi.first.wpilibj.CANJaguar.ControlMode} is <code>Position</code> or closed loop.
     *
     * @param distance The distance to set the winch setpoint to.
     */
    public void setWinchPosition(double distance) {
        if (winch.getControlMode() == CANTalon.ControlMode.Position) {
            winch.set(distance);
            if (RandomConstants.TESTING) {
                SmartDashboard.putNumber("winch encoder", winch.getEncPosition());
            }
        } else {
            SmartDashboard.putString("Uhh", "You tried to set closed loop speed with open loop control");
        }
    }

    /**
     * Changes the control mode to open or closed loop. Use this in the <code>initialize()</code> method of every command that uses the winch motor.
     *
     * @param closed True for position control, false for open loop VBus control.
     */
    public void setClosedLoop(boolean closed) {
        winch.changeControlMode(closed ? CANTalon.ControlMode.Position : CANTalon.ControlMode.PercentVbus);
    }
    
    /**
     * Wrapper method to start the top reed latch.
     * 
     * @param goal - Boolean indicating whether the reed is waiting for a rising or falling edge. True = rising, false = falling.
     */
    public void startReedAbove(boolean goal){
    	try{
    		reedAboveLatch.start(goal);
    	} catch(NullPointerException e){
    		System.out.println(e.getMessage());
    	}
    }
    
    /**
     * Wrapper method to start the bottom reed latch.
     * 
     * @param goal - Boolean indicating whether the reed is waiting for a rising or falling edge. True = rising, false = falling.
     */
    public void startReedBelow(boolean goal){
    	try{
    		reedBelowLatch.start(goal);
    	} catch(NullPointerException e){
    		System.out.println(e.getMessage());
    	}
    }

    /**
     * Wrapper method to start the top reed latch, in a way that will immediatly stop the elevator.
     * 
     * @param goal - Boolean indicating whether the reed is waiting for a rising or falling edge. True = rising, false = falling.
     */
    public void startElevatorLatch(boolean goal){
        try{
            elevatorStopLatch.start(goal);
        } catch(NullPointerException e){
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Stops the top latch.
     */
    public void stopReedAbove(){
    	reedAboveLatch.stopThread();
    }

    /**
     * Stops the bottom latch.
     */
    public void stopReedBelow(){
    	reedBelowLatch.stopThread();
    }

    /**
     * Stops the auto-stop latch.
     */
    public void stopElevatorLatch(){
        elevatorStopLatch.stopThread();
    }

    /**
     * Gets the current value of the top latch.
     * 
     * @return
     */
    public boolean getReedAbove() {
        return reedAboveLatch.getValue();
    }

    /**
     * Gets the current value of the bottom latch.
     * 
     * @return
     */
    public boolean getReedBelow() {
        return reedBelowLatch.getValue();
    }

    /**
     * Gets the current value of the autostop latch.
     * 
     * @return
     */
    public boolean getElevatorLatch(){
        return elevatorStopLatch.getValue();
    }

    /**
     * Logs various values from Stacky.
     */
    ///*/*/***/*/*////***////HACK
    public void logStuffs(int millis){
        try {
            logthing.append(millis + ";" + reedAbove.get() + ";" + reedBelow.get() + ";" + winch.getEncPosition());
            logthing.newLine();
            logthing.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Resets the file writer.
     */
    public void resetFileStuff(){
        try {
            logthing.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //*/**/*//*/*/*/***//*

    /**
     * Returns the control mode of the winch.
     * 
     * @return
     */
    public CANTalon.ControlMode getControlMode(){
        return winch.getControlMode();
    }
    
    public void setControlMode(CANTalon.ControlMode mode){
    	winch.changeControlMode(mode);
    }

    /**
     * Gets whether the top limit switch is active.
     *
     * @return Whether the top limit switch is active
     */
    public boolean getLimitTop() {
//        return !limitTop.get();
        return winch.isFwdLimitSwitchClosed();
    }

    /**
     * Gets whether the bottom limit switch is active.
     *
     * @return Whether the bottom limit switch is active
     */
    public boolean getLimitBottom() {
//        return !limitBottom.get();
        if(!winch.isRevLimitSwitchClosed()){
        	return false;
        }
        winch.setPosition(0);
        isReset = true;
        return true;
    }

    /**
     * Increments the carriage index by one.
     */
    public void upOne() {
        numUp++;
    }

    /**
     * Decrements the carriage index  by one.
     */
    public void downOne() {
        numUp--;
    }

    /**
     * Gets the number of carriages that have gone up
     *
     * @return The number of totes picked up
     */
    public int getNumUp() {
        return numUp;
    }

    /**
     * Sets the carriage index.
     * @param up
     */
    public void setNumUp(int up) {
        numUp = up;
        if (numUp < 0) {
            numUp = 0;
        }
    }

    /**
     * Gets whether there is a tote in the pickup position
     *
     * @return Whether both bumper switches are active
     */
    public boolean getButtonsActive() {
        return !bumperLeft.get() && !bumperRight.get();
    }

    /**
     * Returns the winch encoder position in feet(?).
     * 
     * @return
     */
    public double getDistanceUp() {
        return winch.getEncPosition() * RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV;
    }
    
    public double getWinchCurrent(){
    	return Robot.pdp.getCurrent(2);
    }

    /**
     * Prints various values to the SmartDashboard for diagnostics.
     */
    //*/*/*/*/*/*/*/*/*///*/****/HACK
    public void printStuff() {
        if (RandomConstants.TESTING) {
            SmartDashboard.putNumber("Setpointwinch", winch.getSetpoint());
            SmartDashboard.putBoolean("Buttons", getButtonsActive());
            SmartDashboard.putNumber("PutNumUp", getNumUp());
            SmartDashboard.putBoolean("Reed Above", getReedAbove());
            SmartDashboard.putBoolean("Reed Below", getReedBelow());
        }
    }
    
    /**
     * 
     */
    public void resetStackyPID(){
    	try{
    		winch.setPID(SmartDashboard.getNumber("Input Stacky KP"), SmartDashboard.getNumber("Input Stacky KI"), SmartDashboard.getNumber("Input Stacky KD"), SmartDashboard.getNumber("Input Stacky KF"), (int) SmartDashboard.getNumber("Input Stacky IZONE"), 0, 0);
    	} catch(Exception e){
    		e.printStackTrace();
    	}
    	
        SmartDashboard.putNumber("Stacky KP", winch.getP());
        SmartDashboard.putNumber("Stacky KI", winch.getI());
        SmartDashboard.putNumber("Stacky KD", winch.getD());
        SmartDashboard.putNumber("Stacky KF", winch.getF());
        SmartDashboard.putNumber("Stacky IZONE", winch.getIZone());
    }
}


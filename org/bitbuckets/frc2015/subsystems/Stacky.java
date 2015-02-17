package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Stacky extends Subsystem {
    /**
     * The CANTalon that controls the elevator winch
     */
    private CANTalon winch;

    /**
     * The upper control reed switch
     */
    private DigitalInput reedAbove;
    /**
     * The upper control reed switch
     */
    private DigitalInput reedBelow;

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

    /**
     * The constructor. Initializes everything.
     */
    public Stacky() {
        winch = new CANTalon(RobotMap.WINCH_MOTOR);

        winch.enableLimitSwitch(true, true);

        reedAbove = new DigitalInput(RobotMap.HALL_ABOVE);
        reedBelow = new DigitalInput(RobotMap.HALL_BELOW);

//        limitTop = new DigitalInput(RobotMap.SWITCH_TOP);
//        limitBottom = new DigitalInput(RobotMap.SWITCH_BOTTOM);

        bumperLeft = new DigitalInput(RobotMap.BUMP_SENSE_LEFT);
        bumperRight = new DigitalInput(RobotMap.BUMP_SENSE_RIGHT);

        numUp = 0;
    }

    /**
     * Starts the command that is supposed to start when the subsystem is initialized.
     */
    public void initDefaultCommand() {

    }

    /**
     * Sets the speed of the winch if the {@link edu.wpi.first.wpilibj.CANJaguar.ControlMode} is <code>Disabled</code> or open loop.
     *
     * @param speed The speed to set the winch.
     */
    public void setWinchMotor(double speed) {
        if (winch.getControlMode() == CANTalon.ControlMode.PercentVbus) {
            winch.set(speed);
            SmartDashboard.putNumber("winch encoder", winch.getEncPosition());
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
        } else {
            SmartDashboard.putString("Uhh", "You tried to set closed loop speed with open loop control");
        }
    }

    /**
     * Changes the control mode to open or closed loop. Use this in the <code>initialize()</code> method of every command that uses the winch motor.
     *
     * @param closed True for position control, false for open loop.
     */
    public void setClosedLoop(boolean closed) {
        winch.changeControlMode(closed ? CANTalon.ControlMode.Position : CANTalon.ControlMode.PercentVbus);
    }

    public boolean getReedAbove() {
        return !reedAbove.get();
    }

    public boolean getReedBelow() {
        return !reedBelow.get();
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
        return winch.isRevLimitSwitchClosed();
    }

    public void upOne() {
        numUp++;
    }

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
     * Gets whether there is a tote in the pickup position
     *
     * @return Whether both bumper switches are active
     */
    public boolean getButtonsActive() {
        return !bumperLeft.get() && !bumperRight.get();
    }

    public double getDistanceUp() {
        return winch.getEncPosition() * RandomConstants.WINCH_DRUM_CIRCUMFERENCE / RandomConstants.ENC_TICK_PER_REV;
    }
}


package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Stacky extends Subsystem {
    private CANTalon winch;

    private DigitalInput reedAbove;
    private DigitalInput reedBelow;

//    private DigitalInput limitTop;
//    private DigitalInput limitBottom;

    private DigitalInput bumperLeft;
    private DigitalInput bumperRight;

    private int numUp;

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

    public void initDefaultCommand() {

    }

    public void setWinchMotor(double speed) {
        winch.set(speed);
        SmartDashboard.putNumber("winch encoder", winch.getEncPosition());
    }

    public boolean getReedAbove() {
        return !reedAbove.get();
    }

    public boolean getReedBelow() {
        return !reedBelow.get();
    }

    public boolean getLimitTop() {
//        return !limitTop.get();
        return winch.isFwdLimitSwitchClosed();
    }

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

    public int getNumUp() {
        return numUp;
    }

    public boolean getButtonsActive() {
        return !bumperLeft.get() && !bumperRight.get();
    }

    public boolean getButtonLeft(){
        return bumperLeft.get();
    }

    public boolean getButtonRight(){
        return bumperRight.get();
    }
}


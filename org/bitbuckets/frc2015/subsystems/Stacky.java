package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import org.bitbuckets.frc2015.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Desc: Stacks totes to one distance to another distance vertically.
 * mapp it to an event 
 * inside the button library when its press and realease,
 * when the event happens calls init,
 * continually call main
 * until it finish return true.
 * have interrupt
 */
public class Stacky extends Subsystem {
    private SpeedController winch;
    
    private DigitalInput reedAbove;
    private DigitalInput reedBelow;

    private DigitalInput limitTop;
    private DigitalInput limitBottom;

    private DigitalInput bumperLeft;
    private DigitalInput bumperRight;

    private int numUp;

    public Stacky(){
        winch = new Talon(RobotMap.WINCH_MOTOR);

        reedAbove = new DigitalInput(RobotMap.HALL_ABOVE);
        reedBelow = new DigitalInput(RobotMap.HALL_BELOW);

        limitTop = new DigitalInput(RobotMap.SWITCH_TOP);
        limitBottom = new DigitalInput(RobotMap.SWITCH_BOTTOM);

        bumperLeft = new DigitalInput(RobotMap.BUMP_SENSE_LEFT);
        bumperRight = new DigitalInput(RobotMap.BUMP_SENSE_RIGHT);

        numUp = 0;
    }

    public void initDefaultCommand() {

    }
    
    public void setWinchMotor(double speed){
    	winch.set(speed);
    }

    public boolean getReedAbove() {
        return !reedAbove.get();
    }

    public boolean getReedBelow() {
        return !reedBelow.get();
    }

    public boolean getLimitTop() {
        return !limitTop.get();
    }

    public boolean getLimitBottom() {
        return !limitBottom.get();
    }

    public void upOne(){
        numUp++;
    }

    public void downOne(){
        numUp--;
    }

    public int getNumUp() {
        return numUp;
    }

    public boolean getButtonsActive(){
        return bumperLeft.get() && bumperRight.get();
    }
}


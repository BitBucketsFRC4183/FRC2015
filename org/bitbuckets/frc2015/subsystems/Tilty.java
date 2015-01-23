package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Tilty extends Subsystem {
    private DoubleSolenoid tilty;

    /**
     * Sets up the solenoid.
     */
    public Tilty(){
        tilty = new DoubleSolenoid(RobotMap.TILTY_SOLENOID_1, RobotMap.TILTY_SOLENOID_2);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    /**
     * Sets whether the tilting mechanism elavator thing is up or not.
     *
     * @param up Whether to set the tilter as up or not.
     */
    public void setTiltyUp(boolean up){
        tilty.set(up? DoubleSolenoid.Value.kForward: DoubleSolenoid.Value.kReverse);
    }
}


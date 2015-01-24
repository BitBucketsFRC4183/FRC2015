package org.bitbuckets.frc2015.subsystems;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.bitbuckets.frc2015.RobotMap;

/**
 *
 */
public class Wheely extends Subsystem {
    private CANTalon talon;
    private int x;
    private int y;
    private int theta;


    public Wheely(int index){
        talon = new CANTalon(index);
        switch (index){
            case 0:
                x = RobotMap.WHEEL_FL_X;
                y = RobotMap.WHEEL_FL_Y;
                break;
            case 1:
                x = RobotMap.WHEEL_FR_X;
                y = RobotMap.WHEEL_FR_Y;
                break;
            case 2:
                x = RobotMap.WHEEL_RL_X;
                y = RobotMap.WHEEL_RL_Y;
                break;
            case 3:
                x = RobotMap.WHEEL_RR_X;
                y = RobotMap.WHEEL_RR_Y;
                break;
            default:
                x = 0;
                y = 0;
                throw new IndexOutOfBoundsException();
        }
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


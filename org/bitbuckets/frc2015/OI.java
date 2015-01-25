package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    Joystick stick = new Joystick(1);
    Button trigger = new JoystickButton(stick, 1);

    public static final int GO = 1;
    public static final int STRAFE = 0;
    public static final int TURN = 4;
}


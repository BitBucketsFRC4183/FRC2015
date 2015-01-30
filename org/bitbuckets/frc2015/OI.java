package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.bitbuckets.frc2015.util.POVHat;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    public Joystick stick = new Joystick(1);
    public Button trigger = new JoystickButton(stick, 1);
//    public Button trigger = new JoystickButton(stick, 1);
    public POVHat tiltUp = new POVHat(stick, POVHat.HatDir.UP);
    public POVHat tiltDown = new POVHat(stick, POVHat.HatDir.DOWN);

    public static final int GO = 0;
    public static final int STRAFE = 1;
    public static final int TURN = 4;
}


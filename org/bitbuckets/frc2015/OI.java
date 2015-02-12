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
    public Joystick driver = new Joystick(1);
    public Joystick operator = new Joystick(2);

    public POVHat tiltUp = new POVHat(driver, POVHat.HatDir.UP);
    public POVHat tiltDown = new POVHat(driver, POVHat.HatDir.DOWN);
    public Button changeControl = new JoystickButton(driver, 10);

    public static int GO = 0;
    public static int STRAFE = 1;
    public static int TURN = 4;

    public static void changeControls() {
        if (GO == 0) {
            GO = 5;
            STRAFE = 4;
            TURN = 1;
        } else {
            GO = 0;
            STRAFE = 1;
            TURN = 4;
        }
    }

    public Button driverXBut = new JoystickButton(driver, 1);
    public Button driverCircleBut = new JoystickButton(driver, 2);
    public Button driverSquareBut = new JoystickButton(driver, 3);
    public Button driverTriangBut = new JoystickButton(driver, 4);

    public Button operatorXBut = new JoystickButton(driver, 1);
    public Button operatorCircleBut = new JoystickButton(driver, 2);
    public Button operatorSquareBut = new JoystickButton(driver, 3);
    public Button operatorTriangBut = new JoystickButton(driver, 4);
}


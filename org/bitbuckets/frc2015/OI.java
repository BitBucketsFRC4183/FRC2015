package org.bitbuckets.frc2015;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import org.bitbuckets.frc2015.util.Direction;
import org.bitbuckets.frc2015.util.JoystickDirButton;
import org.bitbuckets.frc2015.util.POVHatDirButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    ////////////////////JOYSTICKS///////////////////////
    public Joystick driver = new Joystick(1);
    public Joystick operator = new Joystick(2);

    ///////////////////BUTTONS//////////////////////////
    //Tilty
    public POVHatDirButton operatorTiltUp = new POVHatDirButton(operator, Direction.UP);
    public POVHatDirButton operatorTiltDown = new POVHatDirButton(operator, Direction.DOWN);

    //Grabby
    public Button operatorGrabOpen = new JoystickButton(operator, 5);
    public Button operatorGrabClose = new JoystickButton(operator, 6);

    //Stacky
    public JoystickDirButton operatorToteUp = new JoystickDirButton(operator, Direction.UP, 0, 1);
    public JoystickDirButton operatorToteDown = new JoystickDirButton(operator, Direction.DOWN, 0, 1);
    public Button operatorToteDownAll = new JoystickButton(operator, 11);
    public Button operatorToteDownBit = new JoystickButton(operator, 3);

    ////////////////////Console////////////////////
//    public Button operatorToteUp = new JoystickButton(operator, 1);
//    public Button operatorToteDown = new JoystickButton(operator, 2);
//    public Button operatorToteDownAll = new JoystickButton(operator, 7);
//    public Button operatorTiltUp = new JoystickButton(operator, 3);
//    public Button operatorTiltDown = new JoystickButton(operator, 4);
    ///////////////////////////////////////////////

    public Button changeControl = new JoystickButton(driver, 10);

    //////////////////AXIS STUFF///////////////////////////
    //Driving (driver)
    public static int GO = 5;
    public static int STRAFE = 2;
    public static int TURN = 0;
    //Grabber (operator)
    //Do you even
    public static final int LIFT = 5;
    //bro?

    public static void changeControls() {
        if (GO == 1) {
            GO = 5;
            STRAFE = 2;
            TURN = 0;
        } else {
            GO = 1;
            STRAFE = 0;
            TURN = 2;
        }
    }

    /////////////////////////PICTURE BUTTONS////////////////////////////
//    public Button driverSquareBut = new JoystickButton(driver, 1);
//    public Button driverXBut = new JoystickButton(driver, 2);
//    public Button driverCircleBut = new JoystickButton(driver, 3);
//    public Button driverTriangBut = new JoystickButton(driver, 4);
//
//    public Button operatorSquareBut = new JoystickButton(operator, 1);
//    public Button operatorXBut = new JoystickButton(operator, 2);
//    public Button operatorCircleBut = new JoystickButton(operator, 3);
//    public Button operatorTriangBut = new JoystickButton(operator, 4);
}


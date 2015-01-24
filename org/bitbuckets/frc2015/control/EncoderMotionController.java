/* FRC 4183 - The Bit Buckets
 * Tucson, AZ
 *
 * FRC 2015 Codebase
 */
package org.bitbuckets.frc2015.control;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SpeedController;

/**
 * This class is a {@link org.bitbuckets.frc2015.util.MotionController} that is designed for Encoder control of a motor.
 */
public class EncoderMotionController {
    private PIDController pidController;

    /**
     * Sets up the controller with the required inputs.
     *
     * @param encoder The encoder to be read from.
     * @param motor   The motor to output to.
     * @param inputs  The kp, ki, and kd for the PID controller. Needs 3 inputs.
     */
    public EncoderMotionController(Encoder encoder, SpeedController motor, double... inputs) {
        try {
            pidController = new PIDController(inputs[0], inputs[1], inputs[2], encoder, motor);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            pidController = new PIDController(.5, .1, .2, encoder, motor);
        }
    }

    /**
     * Changes the setpoint of the PID controller.
     *
     * @param newSet The new setpoint.
     */
    public void changeSetpoint(double newSet) {
        pidController.setSetpoint(newSet);
    }

    /**
     * Set whether the PID controller should be enabled.
     *
     * @param enabled If the PID controller should be enabled.
     */
    public void setEnabled(boolean enabled) {
        if (enabled) {
            pidController.enable();
        } else {
            pidController.disable();
        }
    }
}

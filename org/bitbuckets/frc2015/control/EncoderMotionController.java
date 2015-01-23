/* FRC 4183 - The Bit Buckets
 * Tucson, AZ
 *
 * FRC 2015 Codebase
 */
package org.bitbuckets.frc2015.control;

import edu.wpi.first.wpilibj.PIDController;
import org.bitbuckets.frc2015.util.EmptyPIDOutput;
import org.bitbuckets.frc2015.util.EmptyPIDSource;
import org.bitbuckets.frc2015.util.MotionController;

/**
 * This class is a {@link org.bitbuckets.frc2015.util.MotionController} that is designed for Encoder control of a motor.
 */
public class EncoderMotionController implements MotionController {
    private double speed;
    private double enc;
    private EmptyPIDSource pidSource;
    private EmptyPIDOutput pidOutput;
    private PIDController pidController;

    /**
     * Initializes the <code>EncoderMotionController</code>.
     *
     * @param inputs This {@link org.bitbuckets.frc2015.util.MotionController} needs to be initialized with 3 doubles. In order, they are kp, ki, and kd for the {@link edu.wpi.first.wpilibj.PIDController}
     */
    @Override
    public void init(double... inputs) {
        speed = 0;
        enc = 0;
        pidSource = new EmptyPIDSource();
        pidOutput = new EmptyPIDOutput();

        try {
            pidController = new PIDController(inputs[0], inputs[1], inputs[2], pidSource, pidOutput);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
            pidController = new PIDController(1, 0, 0, pidSource, pidOutput);
        }
    }

    @Override
    public double getSpeed() {
        return speed;
    }

    @Override
    public double update(double set, double... input) {
        pidController.setSetpoint(set);
        return 0;
    }
}

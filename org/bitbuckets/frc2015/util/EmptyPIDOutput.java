package org.bitbuckets.frc2015.util;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Provides an empty {@link edu.wpi.first.wpilibj.PIDOutput} so you can use the built in {@link edu.wpi.first.wpilibj.PIDController} with values.
 * <p/>
 * Created by James on 1/22/2015.
 */
public class EmptyPIDOutput implements PIDOutput {
    private double in = 0;

    @Override
    public void pidWrite(double output) {
        in = output;
    }

    public double getIn() {
        return in;
    }
}

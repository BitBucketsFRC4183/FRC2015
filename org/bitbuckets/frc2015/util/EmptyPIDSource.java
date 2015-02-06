package org.bitbuckets.frc2015.util;

import edu.wpi.first.wpilibj.PIDSource;

/**
 * Provides an empty {@link edu.wpi.first.wpilibj.PIDOutput} so you can use the built in {@link edu.wpi.first.wpilibj.PIDController} with values.
 * <p/>
 * Created by James on 1/22/2015.
 */
public class EmptyPIDSource implements PIDSource {
    private double out = 0;

    public void setOut(double nOut) {
        out = nOut;
    }

    @Override
    public double pidGet() {
        return out;
    }
}

package org.bitbuckets.frc2015.util;

import edu.wpi.first.wpilibj.PIDOutput;

/**
 * Created by James on 1/22/2015.
 */
public class EmptyPIDOutput implements PIDOutput{
    private double in = 0;

    @Override
    public void pidWrite(double output) {
        in = output;
    }

    public double getIn(){
        return in;
    }
}

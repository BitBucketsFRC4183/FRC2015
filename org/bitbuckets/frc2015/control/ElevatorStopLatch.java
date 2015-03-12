package org.bitbuckets.frc2015.control;//TODO Fix Javadocs

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.bitbuckets.frc2015.RandomConstants;
import org.bitbuckets.frc2015.Robot;

/**
 * Created by Alia on 3/8/15.
 */
public class ElevatorStopLatch extends DigitalInputLatch {
    public ElevatorStopLatch(DigitalInput input, Long sleepTime) {
        super(input, sleepTime);
    }

    public ElevatorStopLatch(DigitalInput input) {
        super(input);
    }

    @Override
    public void stopThread(){
        super.stopThread();
        Robot.stacky.setWinchMotor(0);
        Robot.stacky.setClosedLoop(true);
        Robot.stacky.setWinchPosition(Robot.stacky.getDistanceUp() * RandomConstants.ENC_TICK_PER_REV/ RandomConstants.STACKY_WINCH_DRUM_CIRCUMFERENCE);
    }
}

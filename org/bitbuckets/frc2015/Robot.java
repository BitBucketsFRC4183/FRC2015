package org.bitbuckets.frc2015;

import org.bitbuckets.frc2015.command.GrabbyClose;
import org.bitbuckets.frc2015.command.GrabbyMoveDistance;
import org.bitbuckets.frc2015.command.GrabbyOpen;
import org.bitbuckets.frc2015.command.ShooterRetractShort;
import org.bitbuckets.frc2015.command.ShooterShoot;
import org.bitbuckets.frc2015.command.StackyDown;
import org.bitbuckets.frc2015.command.StackyDownAll;
import org.bitbuckets.frc2015.command.StackyMoveDistance;
import org.bitbuckets.frc2015.command.StackyUp;
import org.bitbuckets.frc2015.command.TiltDown;
import org.bitbuckets.frc2015.command.TiltUp;
import org.bitbuckets.frc2015.command.Wait;
import org.bitbuckets.frc2015.command.autonomous.DrivePolar;
import org.bitbuckets.frc2015.control.advanced.AutonomousController;
import org.bitbuckets.frc2015.control.advanced.CommandExecutor;
import org.bitbuckets.frc2015.subsystems.Drivey;
import org.bitbuckets.frc2015.subsystems.DriveyThread;
import org.bitbuckets.frc2015.subsystems.Grabby;
import org.bitbuckets.frc2015.subsystems.GrabbyThread;
import org.bitbuckets.frc2015.subsystems.Shooty;
import org.bitbuckets.frc2015.subsystems.ShootyThread;
import org.bitbuckets.frc2015.subsystems.Stacky;
import org.bitbuckets.frc2015.subsystems.StackyThread;
import org.bitbuckets.frc2015.subsystems.Tilty;
import org.bitbuckets.frc2015.subsystems.TiltyThread;
import org.bitbuckets.frc2015.util.SerialPortManager;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static OI oi;
    public static Drivey drivey;
    public static Grabby grabby;
    public static Stacky stacky;
    public static Tilty tilty;
    public static Shooty shooty;

    public static PowerDistributionPanel pdp;
    private static Compressor compressor;

    private Command autonomousCommand;

    public static SendableChooser autoChooser = new SendableChooser();
    
    public Thread driveyThread;
    public Thread grabbyThread;
    public Thread stackyThread;
    public Thread tiltyThread;
    public Thread shootyThread;
    

//    public static GrabbyOpen grabbyOpen;
//	public static GrabbyClose grabbyClose;
	public static TiltUp tiltUp;
	public static TiltDown tiltDown;
    public static StackyUp upOne;
    public static StackyDown downOne;
    public static StackyDownAll downAll;
    public static StackyMoveDistance downBit;
    public static ShooterShoot shoot;
    public static ShooterRetractShort retract;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
        oi = new OI();
        drivey = new Drivey();
        grabby = new Grabby();
        stacky = new Stacky();
        tilty = new Tilty();
        shooty = new Shooty();

        SerialPortManager.init();

        pdp = new PowerDistributionPanel();
        compressor = new Compressor(0);
        compressor.setClosedLoopControl(true);
        
//    	grabbyOpen = new GrabbyOpen();
//    	grabbyClose = new GrabbyClose();
    	tiltUp = new TiltUp();
    	tiltDown = new TiltDown();
        upOne = new StackyUp();
        downOne = new StackyDown();
        downAll = new StackyDownAll();
        downBit = new StackyMoveDistance(-0.5);
        shoot = new ShooterShoot();
        retract = new ShooterRetractShort();
        
//        oi.operatorGrabOpen.whenPressed(grabbyOpen);
//        oi.operatorGrabClose.whenPressed(grabbyClose);
		oi.operatorTiltUp.whenActive(tiltUp);
		oi.operatorTiltDown.whenActive(tiltDown);
	    oi.operatorToteUpBlind.whenPressed(upOne);
	    oi.operatorToteDown.whenPressed(downOne);
	    oi.operatorToteDownAll.whenPressed(downAll);
	    oi.operatorToteDownBit.whenPressed(downBit);
//	    oi.operatorTapeShoot.whenPressed(shoot);
	    oi.operatorTapeRetract.whenPressed(retract);


        ///////////////////COMMANDS////////////////
//        ChangeDriveMode driveMode = new ChangeDriveMode();

        //FileManager.fetchFiles();

        //ConstantsManager.fetchConstants();

        //generate a list of autonomous programs based on all the .txt files in the local directory
        //TODO make some sort of tag at start of scripts required, so that auto scripts, constant files, etc. don't get confused
        //AutoProgramGenerator.generateAutoPrograms();


        //autonomousCommand = new AutoDriveTest();
//        autoChooser.addObject("Drive to AutoZone", new DriveToAutoZone());
//        autoChooser.addObject("Drive Test", new AutoDriveTest());
//        autoChooser.addDefault("Take Can", new AutoCanMove());
//        autoChooser.addDefault("Single Test", new CanStepGrab(1.0, 100, true, "Single Test"));
//        autoChooser.addObject("Stop Test", new AutoCanRetrieval());
//        SmartDashboard.putData("Auto Chooser", autoChooser);
        SmartDashboard.putNumber("Shooting time", 100);
        SmartDashboard.putNumber("Wind relative", 0.75);
        SmartDashboard.putNumber("Unwind time", 50);
        

    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit() {
    	if(driveyThread != null ){
	    	driveyThread.interrupt();
    	}
    	if(grabbyThread != null){
	    	grabbyThread.interrupt();
    	}
    	if(stackyThread != null){
	    	stackyThread.interrupt();
    	}
    	if(tiltyThread != null){
	    	tiltyThread.interrupt();
    	}
    	if(shootyThread != null){
	    	shootyThread.interrupt();
    	}      
    	AutonomousController.cleanUp();
    }

    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    public void autonomousInit() {
        drivey.resetEncoders();
        //uncomment the following line for position based autoscripts, but ultimately each script should control it on its own.
//        drivey.setEncoderSetting(ControlMode.Position);
        SerialPortManager.analogGyro.reset();
        
//        AutonomousController.addParallel(new CommandExecutor(new StackyUp()));
//        AutonomousController.addParallel(new CommandExecutor(new GrabbyClose()));
//        AutonomousController.addSequential(new CommandExecutor(new Wait(5.0)));
//        AutonomousController.addParallel(new CommandExecutor(new StackyUp()));
//        AutonomousController.addParallel(new CommandExecutor(new GrabbyMoveDistance(2)));
//        AutonomousController.addSequential(new CommandExecutor(new Wait(5.0)));
//        AutonomousController.addParallel(new CommandExecutor(new StackyUp()));
//        AutonomousController.addParallel(new CommandExecutor(new GrabbyOpen()));
//        AutonomousController.addSequential(new CommandExecutor(new Wait(1.0)));

        AutonomousController.start();
        
        if(autonomousCommand != null){
        	autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if(autonomousCommand != null) {
            autonomousCommand.cancel();
        }
        SerialPortManager.analogGyro.reset();
        
        
        driveyThread = new Thread(new DriveyThread(50L, "Drivey Thread"));
        grabbyThread = new Thread(new GrabbyThread(100L, "Grabby Thread"));
        stackyThread = new Thread(new StackyThread(20L, "Stacky Thread"));
        tiltyThread  = new Thread(new TiltyThread(100L, "Tilty Thread"));
        shootyThread = new Thread(new ShootyThread(10L, "Shooty Thread"));
        
        driveyThread.start();
        grabbyThread.start();
        stackyThread.start();
        tiltyThread.start();
        shootyThread.start();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        if(RandomConstants.TESTING){
//            SmartDashboard.putBoolean("Grabby Open:", grabbyOpen.isRunning());
//            SmartDashboard.putBoolean("Grabby Close:", grabbyClose.isRunning());
            SmartDashboard.putBoolean("Tilt Up:", tiltUp.isRunning());
            SmartDashboard.putBoolean("Tilt Down:", tiltDown.isRunning());
            SmartDashboard.putBoolean("Up One Blind:", upOne.isRunning());
            SmartDashboard.putBoolean("Down One:", downOne.isRunning());
            SmartDashboard.putBoolean("Down All:", downAll.isRunning());
            SmartDashboard.putBoolean("Down Bit:", downBit.isRunning());

            SmartDashboard.putNumber("Stacky Motor Current", Robot.stacky.getWinchCurrent());
        }

//        if(RandomConstants.TESTING) {
//            drivey.resetPIDs();
//            stacky.resetStackyPID();
//            grabby.resetGrabbyPID();
//        }
        
        SmartDashboard.putData(Scheduler.getInstance());

        if (RandomConstants.TESTING) {
            stacky.printStuff();
            SmartDashboard.putBoolean("Limit Top", stacky.getLimitTop());
            SmartDashboard.putBoolean("Limit Bottom", stacky.getLimitBottom());
            SmartDashboard.putBoolean("Reed Above", stacky.getReedAbove());
            SmartDashboard.putBoolean("Reed Below", stacky.getReedBelow());
            SmartDashboard.putNumber("Gyro heading", SerialPortManager.getHeading());
        }
    }

    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }

    //*/*//*/*//*/*/*/*/*//*/*/Hck
    public static double deadzone(double input) {
        return Math.abs(input) < RandomConstants.DEFAULT_DEADZONE ? 0 : input;
    }
    
    public static double deadzone(double input, double deadzoneSize){
    	return Math.abs(input) < deadzoneSize ? 0 : input;
    }
    //*///*/**/**/*/*/*/*/*/
}

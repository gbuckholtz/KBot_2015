
package KBot;

import KBot.subsystems.Vision;
import KBot.commands.AutoBinAndTote;
import KBot.commands.AutoBinThief;
import KBot.commands.AutoJustDrive;
import KBot.commands.AutoOneBin;
import KBot.commands.AutoStack2;
import KBot.commands.AutoStack2NoVision;
import KBot.commands.AutoStack3;
import KBot.commands.AutoStack3NoVision;
import KBot.commands.AutoToteNoLift;
import KBot.commands.Collapse;
import KBot.commands.CollapseCommand;
import KBot.commands.DriveController;
import KBot.commands.DriveRelative;
import KBot.commands.MoveLifter;
import KBot.commands.MoveWrist;
import KBot.commands.OpenClaw;
import KBot.commands.TrackYellowTote;
import KBot.subsystems.Claw;
import KBot.subsystems.DriveTrain;
import KBot.subsystems.Lift;
import KBot.subsystems.VisionPIDDrive;
import KBot.subsystems.Wrist;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	private final int collapse_time = 115;
	private final boolean collapse_on = false;
	
	public static OI oi;
	public static DriveTrain drivetrain;
	public static Lift lift;
	public static Claw claw;
	public static Wrist wrist;
	public static Vision visionSubsystem;
	public static VisionPIDDrive visionPIDSubsystem;
	private static Timer countdown;

    Command autonomousCommand, teleopCommand;
    
	static private boolean autonomousEnabled = false;
	private static boolean overrideSet = false;
	private static boolean collapsed = false;
	static public boolean isTeleop(){
		return !autonomousEnabled;
	}

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
    	visionSubsystem = new Vision();
    	visionPIDSubsystem = new VisionPIDDrive();
		drivetrain = new DriveTrain();
		lift = new Lift();
		lift.resetEncoders();
		claw = new Claw();
		wrist = new Wrist();		
		oi = new OI();
		
        // instantiate the command used for the teleop period
		teleopCommand = new DriveController();
		countdown = new Timer();
		
        // instantiate the command used for the autonomous period
		//setAutonomousMode();
    }
    
    private void setAutonomousMode() {
		// autoModeInputs 0, 1, 8 and 9 are not hooked up yet
		/*autonomousCommand = new AutoJustDrive();	// Default if no switches set
		
		boolean useVision = !RobotMap.autoModeInput[7].get();
		
		//Change this to a loop with a switch instead of all these if statements
		
		if (!RobotMap.autoModeInput[6].get()) {
			// Get a bin from the center wall
			autonomousCommand = new AutoBinThief();
		}
		
		if (!RobotMap.autoModeInput[2].get()) {
			// Grab bin and get out of the way
			autonomousCommand = new AutoOneBin();
		}
		if (!RobotMap.autoModeInput[3].get()) {
			// Take one bin and one tote
			autonomousCommand = new AutoBinAndTote();
		}
		if (!RobotMap.autoModeInput[4].get()) {
			// Take bin and two totes
			if (useVision) {
				autonomousCommand = new AutoStack2();
			} else {
				autonomousCommand = new AutoStack2NoVision();
			}
		}
		if (!RobotMap.autoModeInput[5].get()) {
			// Take bin and three totes
			if (useVision) {
				autonomousCommand = new AutoStack3();
			} else {
				autonomousCommand = new AutoStack3NoVision();
			}
		}*/
		
		// Testing overrides:
		
		//autonomousCommand = new TrackYellowTote();
		//autonomousCommand = new DriveRelative(0.75, -1.0, 0.75); // turn left
		//autonomousCommand = new DriveRelative(0.2, 0.0, 0.1); // forward 1 inch
		//autonomousCommand = new SetLiftHeight(SetLiftHeight.level.LVL2, SetLiftHeight.offset.LOWER);
		//autonomousCommand = new MoveWrist(-36.5);
		//autonomousCommand = new AutoJustDrive();
    	autonomousCommand = new AutoOneBin();
    }
    
    public void autonomousInit() {
        autonomousEnabled = isAutonomous();
		setAutonomousMode();
		lift.resetEncoders();
        if (teleopCommand != null) teleopCommand.cancel();		//TODO: should not be needed
        if (autonomousCommand != null)
        {
        	System.out.println("starting autonomous command");
        	autonomousCommand.start();
        }
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        autonomousEnabled = isAutonomous();
        Scheduler.getInstance().run();
        //oi.operator.tron();
    }

    public void teleopInit() {
        autonomousEnabled = isAutonomous();
		lift.resetEncoders();	//TODO: remove this
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.visionPIDSubsystem.disable();		//TODO: should not be needed (in the end command of TrackYellowTote)
        //teleopCommand.start();
        oi.operator.disableLights();
        countdown.reset();
        countdown.start();
        collapsed = false;
        
        
        // For some reason, if we initialize the override buttons more than once they won't work anymore, so leave this in.
        if (!overrideSet)
        {
        	oi.initializeOverrideButtons();
        	overrideSet = true;
        }
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        autonomousEnabled = isAutonomous();
        Robot.claw.checkMotors();
        Robot.wrist.checkMotors();
        //Robot.lift.checkMotors();
        Scheduler.getInstance().run();
        oi.operator.tron();
        SmartDashboard.putNumber("Claw", claw.getClawPosition());
        SmartDashboard.putNumber("Wrsit", (wrist.getAngle()/105));
        SmartDashboard.putNumber("Lift", RobotMap.liftTalon1.getPosition());
        SmartDashboard.putNumber("Lift1 i", RobotMap.liftTalon1.getOutputCurrent());
        SmartDashboard.putNumber("Lift1 v", RobotMap.liftTalon1.getAnalogInVelocity());
        SmartDashboard.putNumber("Lift2 i", RobotMap.liftTalon1.getOutputCurrent());
        SmartDashboard.putNumber("Lift2 v", RobotMap.liftTalon1.getAnalogInVelocity());
        SmartDashboard.putNumber("Lift3 i", RobotMap.liftTalon1.getOutputCurrent());
        SmartDashboard.putNumber("Lift3 v", RobotMap.liftTalon1.getAnalogInVelocity());
        	
        if (countdown.get() > collapse_time && !collapsed && collapse_on)        
        {
        	System.out.println(" Collapsing at Match time: " + DriverStation.getInstance().getMatchTime());
        	Command collapse = new Collapse();
        	collapse.start();
        	System.out.println("Ending Match");
        	collapsed = true;
        }
        //System.out.println("Wrist Pot: " + (RobotMap.wristPot.get()/3.674-384) + " | Operator Pot: " + oi.operator.getPotAngle());
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        autonomousEnabled = isAutonomous();
    	if (teleopCommand != null) teleopCommand.cancel();
    	if (autonomousCommand != null) autonomousCommand.cancel();
    	//countdown.reset();
    	collapsed = false;
    }
    
	int count=0;
	public void disabledPeriodic() {
        autonomousEnabled = isAutonomous();
		oi.operator.pacman();
		//System.out.println("Auto Selector Pot: " + RobotMap.autoTimerInput.getValue());
		//System.out.println("Wrist Pot: " + RobotMap.wristPot.get() + " | Operator Pot: " + oi.operator.getPotAngle());
		//System.out.println("3: " + oi.operator.getPotAngle());
		
		/*if (++count%20==0)
		{
			String msg="";
			for (int i=14; i>=0; i--) {
				msg+=(RobotMap.autoModeInput[i].get()?"1 ":"0 ");
			}
			msg+=(RobotMap.autoTimerInput.getValue());
			msg+=autonomousCommand.toString();
			System.out.println(msg);
		}*/
	}

    /**
     * This function is called once before test mode
     */
    public void testInit() {
        autonomousEnabled = isAutonomous();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        autonomousEnabled = isAutonomous();
        LiveWindow.run();
        oi.operator.tron();
    }
}

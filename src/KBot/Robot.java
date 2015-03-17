
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
import KBot.commands.DriveController;
import KBot.commands.DriveRelative;
import KBot.commands.MoveLifter;
import KBot.commands.TrackYellowTote;
import KBot.subsystems.Claw;
import KBot.subsystems.DriveTrain;
import KBot.subsystems.Lift;
import KBot.subsystems.VisionPIDDrive;
import KBot.subsystems.Wrist;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static OI oi;
	public static DriveTrain drivetrain;
	public static Lift lift;
	public static Claw claw;
	public static Wrist wrist;
	public static Vision visionSubsystem;
	public static VisionPIDDrive visionPIDSubsystem;

    Command autonomousCommand, teleopCommand;
    
	static private boolean autonomousEnabled = false;
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
		claw = new Claw();
		wrist = new Wrist();		
		oi = new OI();
		
        // instantiate the command used for the teleop period
		teleopCommand = new DriveController();
		
        // instantiate the command used for the autonomous period
		setAutonomousMode();
    }
    
    private void setAutonomousMode() {
		// autoModeInputs 0, 1, 8 and 9 are not hooked up yet
		autonomousCommand = new AutoJustDrive();	// Default if no switches set
		
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
		}
		
		// Testing overrides:
		
		//autonomousCommand = new TrackYellowTote();
		//autonomousCommand = new DriveRelative(0.75, -1.0, 0.75); // turn left
		//autonomousCommand = new DriveRelative(0.2, 0.0, 0.1); // forward 1 inch
		//autonomousCommand = new SetLiftHeight(SetLiftHeight.level.LVL2, SetLiftHeight.offset.LOWER);
    }
    public void autonomousInit() {
        autonomousEnabled = isAutonomous();
		setAutonomousMode();
        if (teleopCommand != null) teleopCommand.cancel();		//TODO: should not be needed
        if (autonomousCommand != null) autonomousCommand.start();
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
        if (autonomousCommand != null) autonomousCommand.cancel();
        Robot.visionPIDSubsystem.disable();		//TODO: should not be needed (in the end command of TrackYellowTote)
        //teleopCommand.start();
        oi.operator.disableLights();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        autonomousEnabled = isAutonomous();
        Scheduler.getInstance().run();
        oi.operator.tron();
    }
    
    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
        autonomousEnabled = isAutonomous();
    	if (teleopCommand != null) teleopCommand.cancel();
    	if (autonomousCommand != null) autonomousCommand.cancel();
    }
    
	int count=0;
	public void disabledPeriodic() {
        autonomousEnabled = isAutonomous();
		oi.operator.pacman();
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

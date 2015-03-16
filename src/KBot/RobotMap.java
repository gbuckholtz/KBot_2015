package KBot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.RobotDrive;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Constants
		//Underglow
	public static final int relayRed = 0;
	private static final int relayGreen = 1;
	private static final int relayBlue = 2;
		//Drive Motors
	private static final int rightDriveSplice = 0;
	private static final int rightDriveSingle = 1;
	private static final int leftDriveSplice = 2;
	private static final int leftDriveSingle = 3;
		//Drive Encoders
	private static final int driveEncLeftA = 0;
	private static final int driveEncLeftB = 1;
	private static final int driveEncRightA = 2;
	private static final int driveEncRightB = 3;
		//Lift
	private static final int lift1 = 1;
	private static final int lift2 = 2;
	private static final int lift3 = 3;
		//Wrist
	private static final int wristInput = 1;
	private static final int wristOutput = 4;
		//Claw
	private static final int clawInput = 2;
	private static final int clawOutput = 5;
	
	//Object Declarations
	public static CANTalon liftTalon1, liftTalon2, liftTalon3;
	public static CANTalon wristTalon, clawTalon;
	public static AnalogPotentiometer wristPot, clawPot;
	public static Encoder driveEncoderLeft, driveEncoderRight;
	public static RobotDrive drive;
	public static Relay red, green, blue;
	public static DigitalInput autoModeInput[];
	public static AnalogInput autoTimerInput;

	//Network table for vision system
	public static NetworkTable visionTable = NetworkTable.getTable("SmartDashboard");
    
	public static void init() 
    {

		//Drive System
		drive = new RobotDrive(leftDriveSplice, leftDriveSingle, rightDriveSplice, rightDriveSingle);
    	driveEncoderLeft = new Encoder(driveEncLeftA,driveEncLeftB);
    	driveEncoderRight = new Encoder(driveEncRightA,driveEncRightB);
    	
    	drive.setSafetyEnabled(false);
    	driveEncoderLeft.setReverseDirection(false);
    	
    	//Lifter System
    	liftTalon1 = new CANTalon(lift1);
    	liftTalon2 = new CANTalon(lift2);
    	liftTalon3 = new CANTalon(lift3);
    	
    		//Lifter 1
		liftTalon1.setProfile(0);
    	liftTalon1.changeControlMode(CANTalon.ControlMode.Position);
		liftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		liftTalon1.reverseSensor(true);	// if necessary
    	liftTalon1.setPID(10.0, 0.0, 0.0); // This should be done in the subsystem that uses this, not in robotMap

    		//Lifter 2
    	liftTalon2.changeControlMode(CANTalon.ControlMode.Follower);
    	liftTalon2.set(liftTalon1.getDeviceID());
    	liftTalon2.setPID(1.0, 0.0, 0.0);// This should be done in the subsystem that uses this, not in robotMap
    	liftTalon2.enableControl();		// Not sure if this is needed
    	
    		//Lifter 3
    	liftTalon3.changeControlMode(CANTalon.ControlMode.Follower);
    	liftTalon3.set(liftTalon1.getDeviceID());
    	liftTalon3.setPID(1.0, 0.0, 0.0);// This should be done in the subsystem that uses this, not in robotMap
    	liftTalon3.enableControl();		// Not sure if this is needed
    	
    	//Claw System
    	clawTalon = new CANTalon(clawOutput);
    	clawPot = new AnalogPotentiometer(clawInput);
    	
    	//Wrist
    	wristTalon = new CANTalon(wristOutput);
    	wristPot = new AnalogPotentiometer(wristInput);
    	
    	//Underglow
    		// kReverse is ON
    		// kForward is OFF
    	red = new Relay(relayRed);
    	green = new Relay(relayGreen);
    	blue = new Relay(relayBlue);
    	
    	autoModeInput = new DigitalInput[15];
    	for (int i=0; i<15; i++)
    	{
    		autoModeInput[i] = new DigitalInput(10+i);		//  10 - 25 are on the MXP
    	}

    	autoTimerInput = new AnalogInput(4);			// 4 - 7 are on the MXP
    }
}

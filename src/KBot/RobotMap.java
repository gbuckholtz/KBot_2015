package KBot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
//import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.RobotDrive;
//import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//Constants
	public static final int relayRed = 0, relayGreen = 1, relayBlue = 2;
	private static final int leftDriveSplice = 2, leftDriveNot = 3, rightDriveSplice = 0, rightDriveNot = 1;
	
	//Object Declarations
	public static CANTalon liftTalon1, liftTalon2, liftTalon3;
	public static CANTalon wristTalon, clawTalon;
	public static AnalogPotentiometer wristPot, clawPot;
	public static Encoder driveEncoderLeft, driveEncoderRight, liftEncoder;
	public static RobotDrive drive;
	public static Relay red, green, blue;
	public static DigitalInput autoModeInput[];
	public static AnalogInput autoTimerInput;

	//Network table for vision system
	public static NetworkTable visionTable= NetworkTable.getTable("SmartDashboard");
    
	public static void init() 
    {
		//visionTable = NetworkTable.getTable("SmartDashboard"); //TODO: Trying it here to try to get rid of error
		//Drive System
		drive = new RobotDrive(leftDriveSplice, leftDriveNot, rightDriveSplice, rightDriveNot);
    	driveEncoderLeft = new Encoder(0,1);
    	driveEncoderLeft.setReverseDirection(false);
    	driveEncoderRight = new Encoder(2,3);
    	drive.setSafetyEnabled(false);
    	
    	//Lifter System
    	liftTalon1 = new CANTalon(1);
    	liftTalon2 = new CANTalon(2);
    	liftTalon3 = new CANTalon(3);
		liftTalon1.setProfile(0);
    	liftTalon1.changeControlMode(CANTalon.ControlMode.Position);
		liftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		liftTalon1.reverseSensor(true);	// if necessary
    	liftTalon1.setPID(10.0, 0.0, 0.0);

    	// Set lift talons 2 and 3 to slave themselves to liftTalon1:
    	liftTalon2.changeControlMode(CANTalon.ControlMode.Follower);
    	liftTalon2.set(liftTalon1.getDeviceID());
    	liftTalon2.setPID(1.0, 0.0, 0.0);
    	liftTalon2.enableControl();		// Not sure if this is needed
    	liftTalon3.changeControlMode(CANTalon.ControlMode.Follower);
    	liftTalon3.set(liftTalon1.getDeviceID());
    	liftTalon3.setPID(1.0, 0.0, 0.0);
    	liftTalon3.enableControl();		// Not sure if this is needed
    	
    	//liftEncoder = new Encoder(5,6); // THIS ENCODER IS ON THE TALONS (1,2 and 3)
    	
    	//Claw System
    	wristTalon = new CANTalon(4);
    	clawTalon = new CANTalon(5);
    	wristPot = new AnalogPotentiometer(1);
    	clawPot = new AnalogPotentiometer(2);
    	
    	//Underglow
    		// kReverse is ON
    		// kForward is OFF
    	red = new Relay(relayRed);
    	green = new Relay(relayGreen);
    	blue = new Relay(relayBlue);
    	//red.setDirection(Direction.kReverse);
    	//green.setDirection(Direction.kReverse);
    	//blue.setDirection(Direction.kReverse);
    	
    	autoModeInput = new DigitalInput[15];
    	for (int i=0; i<15; i++)
    	{
    		autoModeInput[i] = new DigitalInput(10+i);		//  10 - 25 are on the MXP
    	}

    	autoTimerInput = new AnalogInput(4);			// 4 - 7 are on the MXP
    }
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

package KBot;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
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

	public static Encoder driveEncoderLeft, driveEncoderRight;
	public static RobotDrive drive;
	public static Relay red, green, blue;
	public static DigitalInput autoModeInput[];
	public static AnalogInput autoTimerInput;
    
	public static void init() 
    {

		//Drive System
		drive = new RobotDrive(leftDriveSplice, leftDriveSingle, rightDriveSplice, rightDriveSingle);
    	driveEncoderLeft = new Encoder(driveEncLeftA,driveEncLeftB);
    	driveEncoderRight = new Encoder(driveEncRightA,driveEncRightB);
    	
    	drive.setSafetyEnabled(false);
    	driveEncoderLeft.setReverseDirection(false);
    	    	
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

package KBot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Object Declarations
	public static CANTalon liftTalon1, liftTalon2, liftTalon3;
	public static CANTalon wristTalon, clawTalon;
	public static AnalogPotentiometer wristPot, clawPot;
	public static Encoder driveEncoderLeft, driveEncoderRight, liftEncoder;
	public static RobotDrive drive;
	
	//PWM Locations
	private static final int leftDriveSplice = 2, leftDriveNot = 3, rightDriveSplice = 0, rightDriveNot = 1;
    
	public static void init() 
    {
		//Drive System
		drive = new RobotDrive(leftDriveSplice, leftDriveNot, rightDriveSplice, rightDriveNot);
    	driveEncoderLeft = new Encoder(1,2);
    	driveEncoderRight = new Encoder(3,4);
    	drive.setSafetyEnabled(false);
    	
    	//Lifter System
    	liftTalon1 = new CANTalon(1);
    	liftTalon2 = new CANTalon(2);
    	liftTalon3 = new CANTalon(3);
    	liftEncoder = new Encoder(5,6);
    	
    	//Claw System
    	wristTalon = new CANTalon(4);
    	clawTalon = new CANTalon(5);
    	wristPot = new AnalogPotentiometer(1);
    	clawPot = new AnalogPotentiometer(2);
    }
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

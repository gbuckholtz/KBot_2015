package KBot;

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	public static Talon leftDrive1, leftDrive2, leftDrive3, rightDrive1, rightDrive2, rightDrive3;
	public static CANTalon liftTalon1, liftTalon2, liftTalon3;
	public static CANTalon wristTalon, clawTalon;
	public static AnalogPotentiometer wristPot, clawPot;
	public static Encoder driveEncoderLeft, driveEncoderRight, liftEncoder;
    
	public static void init() 
    {
    	leftDrive1 = new Talon(1);
    	leftDrive2 = new Talon(2);
    	leftDrive3 = new Talon(3);
    	leftDrive1 = new Talon(4);
    	leftDrive2 = new Talon(5);
    	leftDrive3 = new Talon(6);
    	liftTalon1 = new CANTalon(1);
    	liftTalon2 = new CANTalon(2);
    	liftTalon3 = new CANTalon(3);
    	wristTalon = new CANTalon(4);
    	clawTalon = new CANTalon(5);
    	wristPot = new AnalogPotentiometer(1);
    	clawPot = new AnalogPotentiometer(2);
    	driveEncoderLeft = new Encoder(1,2);
    	driveEncoderRight = new Encoder(3,4);
    	liftEncoder = new Encoder(5,6);
    }
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

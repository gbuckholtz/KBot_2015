package kbot.pid;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static CANTalon pidTalon;
	public static Talon leftTalon1,leftTalon2,leftTalon3;
	public static Talon rightTalon1, rightTalon2, rightTalon3;
	
	public static Encoder leftEncoder, rightEncoder;
	//public static AnalogPotentiometer pot;
	//public static Encoder encoder;
	
	public static void init()
	{
		pidTalon = new CANTalon(0);
		pidTalon.changeControlMode(ControlMode.Position);
		
		leftTalon1 = new Talon(1);
		leftTalon2 = new Talon(2);
		leftTalon3 = new Talon(3);
		rightTalon1 = new Talon(5);
		rightTalon2 = new Talon(6);
		rightTalon3 = new Talon(7);
		
		leftEncoder = new Encoder(4,5);
		leftEncoder.reset();
		leftEncoder.setReverseDirection(false);
		rightEncoder = new Encoder(2,3);
		rightEncoder.reset();
		rightEncoder.setReverseDirection(true);
		
		//pot = new AnalogPotentiometer(0,1800);
		//encoder = new Encoder(0,1);
	}
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int rangefinderPort = 1;
    // public static int rangefinderModule = 1;
}

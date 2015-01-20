package kbot.pid;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.AnalogPotentiometer;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	public static Talon pidTalon;
	public static AnalogPotentiometer pot;
	public static Encoder encoder, leftEncoder, rightEncoder;
	public static SpeedController leftMotor1, leftMotor2, leftMotor3;
	public static SpeedController rightMotor1, rightMotor2, rightMotor3;
	public static RobotDrive driveTrain;
	public static CANTalon canTalon;
	
	public static void init()
	{
		pidTalon = new Talon(0);
		pot = new AnalogPotentiometer(0,1800);
		encoder = new Encoder(0,1);
		leftEncoder = new Encoder(2,3,true);
		rightEncoder = new Encoder(4,5,false);
		//leftEncoder.setDistancePerPulse(1/225);
		//rightEncoder.setDistancePerPulse(1/225);
		leftEncoder.setDistancePerPulse(1);
		rightEncoder.setDistancePerPulse(1);
		
		leftMotor1 = new Talon(1);
		leftMotor2 = new Talon(2);
		leftMotor3 = new Talon(3);
		rightMotor1 = new Talon(5);
		rightMotor2 = new Talon(6);
		rightMotor3 = new Talon(7);
		
		canTalon = new CANTalon(0);
		canTalon.changeControlMode(ControlMode.Position);
	}
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
    
    // If you are using multiple modules, make sure to define both the port
    // number and the module. For example you with a rangefinder:
    // public static int r angefinderPort = 1;
    // public static int rangefinderModule = 1;
}

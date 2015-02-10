package KBot.subsystems;

import KBot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Wrist extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Wrist()
	{
		RobotMap.wristTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.wristTalon.enableControl();
		RobotMap.wristTalon.setPID(0, 0, 0);
		RobotMap.wristTalon.setProfile(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


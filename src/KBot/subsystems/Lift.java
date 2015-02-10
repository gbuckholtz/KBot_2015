package KBot.subsystems;

import KBot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Lift()
	{
		RobotMap.liftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.liftTalon1.enableControl();
		RobotMap.liftTalon1.setPID(0, 0, 0);
		RobotMap.liftTalon1.setProfile(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}


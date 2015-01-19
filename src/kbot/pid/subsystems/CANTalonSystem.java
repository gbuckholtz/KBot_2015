package kbot.pid.subsystems;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CANTalonSystem extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalonSystem()
	{
		RobotMap.canTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.canTalon.reverseOutput(true);
		RobotMap.canTalon.reverseSensor(true);
		RobotMap.canTalon.enableControl();
		RobotMap.canTalon.setPID(0.1,0.001,1.0);
		RobotMap.canTalon.setProfile(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void setPosition(double setpoint)
    {
    	RobotMap.canTalon.set(setpoint);
    	System.out.println("Output: "+ RobotMap.canTalon.getOutputVoltage() + 
    			" | SetPoint: " + RobotMap.canTalon.getSetpoint() + 
    			" | Position " + RobotMap.canTalon.get());
    }
}


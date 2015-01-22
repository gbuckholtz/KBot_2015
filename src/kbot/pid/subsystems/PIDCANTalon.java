package kbot.pid.subsystems;

import kbot.pid.RobotMap;
import kbot.pid.commands.CANTalonCommand;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class PIDCANTalon extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public PIDCANTalon()
	{
		RobotMap.pidTalon.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.pidTalon.reverseOutput(false);
		RobotMap.pidTalon.reverseSensor(false);
		RobotMap.pidTalon.enableControl();
		RobotMap.pidTalon.setPID(20,0,0);//-2.0,-0.5);
		RobotMap.pidTalon.setProfile(0);
		RobotMap.pidTalon.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CANTalonCommand());
    }
    
    public void out()
    {
    	System.out.println("setPoint: "+RobotMap.pidTalon.getSetpoint()+" | Position: "+RobotMap.pidTalon.get());
    }
}


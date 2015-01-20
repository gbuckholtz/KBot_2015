package kbot.pid.subsystems;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import kbot.pid.commands.CANCommand;
import kbot.pid.commands.PrintThings;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
		RobotMap.canTalon.reverseOutput(false);
		RobotMap.canTalon.reverseSensor(true);
		RobotMap.canTalon.enableControl();
		RobotMap.canTalon.setPID(1,0,0);//-2.0,-0.5);
		RobotMap.canTalon.setProfile(0);
		RobotMap.canTalon.set(500);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CANCommand());//new PrintThings());
    }
    
    private void setPosition(double setpoint)
    {
    	RobotMap.canTalon.set(setpoint);
    	
    }
    
    public void out()
    {
    	SmartDashboard.putNumber("Talon", RobotMap.canTalon.getPosition());
    	System.out.println("Output: "+ RobotMap.canTalon.getOutputVoltage() + 
    			" | SetPoint: " + RobotMap.canTalon.getSetpoint() + 
    			" | Position " + RobotMap.canTalon.get());
    }
}


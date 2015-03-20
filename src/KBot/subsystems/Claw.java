package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.OpenClaw;
import KBot.commands.MoveLifter;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The claw subsystem uses Talon SRX position mode PID using a potentiometer to
 * open and close the claw.
 */
public class Claw extends Subsystem {
	
	private final static int THRESHOLD = 4;	
	private final static double OPEN_VALUE = 930.0;	
	private final static double CLOSED_VALUE = 1018.0;	
    
	public Claw()
	{
		RobotMap.clawTalon.setProfile(0);
		RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		RobotMap.clawTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		RobotMap.clawTalon.reverseOutput(true); 	// Make it so that positive is open. FOR CLOSED LOOP ONLY.
		RobotMap.clawTalon.reverseSensor(false);	// encoder readout is currently opposite to motor direction
		RobotMap.clawTalon.setVoltageRampRate(0);		// use if necessary
		RobotMap.clawTalon.setCloseLoopRampRate(0);
		RobotMap.clawTalon.setPID(24.0, 0.001, 15.0);
		RobotMap.clawTalon.enableControl();
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void set(double value)
    {
    	RobotMap.clawTalon.set(value);
    	System.out.println("Claw set to:"+value);
    }
    
    public void open()
    {
    	set(OPEN_VALUE);
    }
  
    public void close()
    {
    	set(CLOSED_VALUE);
    }

    public void stop()
    {
		if (RobotMap.clawTalon.getControlMode() != ControlMode.Position) {
			RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		} 

		RobotMap.clawTalon.set(RobotMap.clawTalon.getPosition());	// set setpoint to current position
		RobotMap.clawTalon.ClearIaccum();							// clear built up error term
		
		// Leave PID on to maintain the position.
	}
    public void setVoltageMode()
    {
		RobotMap.clawTalon.changeControlMode(ControlMode.PercentVbus);
		RobotMap.clawTalon.enableControl();	//is it needed?
		RobotMap.clawTalon.set(0);
    }
    
    public void setPositionMode()
    {
		RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		RobotMap.clawTalon.enableControl();	//is it needed?
		RobotMap.clawTalon.set(RobotMap.clawTalon.getPosition());	// set setpoint to current position
    }
     
    public boolean isLimitSwitchFaulted()
    {
    	return false; //TODO: put back:  RobotMap.clawTalon.getFaultForLim()!=0 || RobotMap.clawTalon.getFaultRevLim()!=0;
    }
    
    public boolean isFinished()
    {
    	return RobotMap.clawTalon.getClosedLoopError()<THRESHOLD;
    }
    
    public int getPIDError()
    {
    	return RobotMap.clawTalon.getClosedLoopError();
    }
}


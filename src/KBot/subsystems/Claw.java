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
	private final static double CLOSED_VALUE = 1023.0;	
	private static boolean isOpen = false;
    
	public Claw()
	{
		RobotMap.clawTalon.setProfile(0);
		RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		RobotMap.clawTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		RobotMap.clawTalon.reverseOutput(true); 	// Make it so that positive is open. FOR CLOSED LOOP ONLY.
		RobotMap.clawTalon.reverseSensor(false);	// encoder readout is currently opposite to motor direction
		RobotMap.clawTalon.setVoltageRampRate(0);		// use if necessary
		RobotMap.clawTalon.setCloseLoopRampRate(0);
		RobotMap.clawTalon.setPID(24.0, 0.001, 5.0, 0.0, 10, 0.0, 0);
		RobotMap.clawTalon.enableControl();
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void set(double value)
    {
    	if (!Robot.wrist.isWristUp())
    		RobotMap.clawTalon.set(value);
    	else
    		System.out.println("Can't Open Claw; Wrist is Up.");
    }
    
    public void open()
    {
    	set(OPEN_VALUE);
    	isOpen = true;
    }
  
    public void close()
    {
    	set(CLOSED_VALUE);
    	isOpen = false;
    }

    public void stop()
    {
		if (RobotMap.clawTalon.getControlMode() != ControlMode.Position) {
			RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		} 

		set(RobotMap.clawTalon.getPosition());	// set setpoint to current position
		RobotMap.clawTalon.ClearIaccum();							// clear built up error term
		
		// Leave PID on to maintain the position.
	}
    
    public void setVoltageMode()
    {
		RobotMap.clawTalon.changeControlMode(ControlMode.PercentVbus);
		RobotMap.clawTalon.enableControl();	//is it needed?
		set(0);
    }
    
    public void setPositionMode()
    {
		RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		RobotMap.clawTalon.enableControl();	//is it needed?
		set(RobotMap.clawTalon.getPosition());	// set setpoint to current position
    }
    
	private static int overCurrentCount=0;
    public void checkMotors()
    {
    	if (RobotMap.clawTalon.getOutputCurrent()>2.5)
    		overCurrentCount++;
    	else
    		overCurrentCount=0;
    	if (RobotMap.clawTalon.getAnalogInVelocity()==0 && overCurrentCount>25)
    	{
    		// We are stalled, so stop at this point
    		System.out.println("Claw stalled; telling it to stay at:"+RobotMap.clawTalon.getPosition());
    		set(RobotMap.clawTalon.getPosition());
    	}
		//System.out.println("Claw current="+RobotMap.clawTalon.getOutputCurrent()+" velocity="+RobotMap.clawTalon.getAnalogInVelocity());
    }
    
    public boolean onTarget()
    {
    	return Math.abs(RobotMap.clawTalon.getPosition()-RobotMap.clawTalon.getSetpoint())<THRESHOLD;
    }
    
    public int getPIDError()
    {
    	return RobotMap.clawTalon.getClosedLoopError();
    }
    
    public boolean isOpen()
    {
    	return isOpen;
    }
}


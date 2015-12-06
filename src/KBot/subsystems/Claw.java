package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The claw subsystem uses Talon SRX position mode PID using a potentiometer to
 * open and close the claw.
 */
public class Claw extends Subsystem {
	
	private final static int THRESHOLD = 4;	
	private final static double OPEN_VALUE = 764.0;	
	private final static double CLOSED_VALUE = 934.0;
	private final static double minValueWhenWristUp = 890.0;
	private static boolean isOpen = false;
	public final double openSpeed = 0.6;
	public final double closeSpeed = -openSpeed;
	
	public static enum Mode {SPEED, PID};
	
	public Mode clawMode = Claw.Mode.PID;
    
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
    {}
    
    public void set(double value)
    {
    	if (Robot.wrist.isWristUp() && value<minValueWhenWristUp)
    		System.out.println("Can't Open Claw any more; Wrist is Up.");
    	else
    		RobotMap.clawTalon.set(value);
   }
    
    public void open()
    {
    	if (!Robot.wrist.isWristUp())
    	{
    		set(OPEN_VALUE);
    		System.out.println("Open value:"+Robot.wrist.getAngle());
        	isOpen = true;
    	}
    	else
    		System.out.println("Open ignored because wrist is up");
    	
    }
  
    public void close()
    {
    	set(CLOSED_VALUE);
        isOpen = false;
		System.out.println("Close value:"+Robot.wrist.getAngle());
    }
    
    public void setSpeed(double speed)
    {
    	if (RobotMap.clawTalon.getControlMode() != ControlMode.PercentVbus) {
			setVoltageMode();
		} 
    	RobotMap.clawTalon.set(speed);
    }

    public void stop()
    {
		if (RobotMap.clawTalon.getControlMode() != ControlMode.Position) {
			RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		} 

		set(RobotMap.clawTalon.getPosition());	// set setpoint to current position
		RobotMap.clawTalon.ClearIaccum();		// clear built up error term
		
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
    	if (RobotMap.clawTalon.getOutputCurrent()>4.0)
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
    public double getClawPosition()
    {
    	return (RobotMap.clawTalon.getPosition()/OPEN_VALUE);
    	
    }
}


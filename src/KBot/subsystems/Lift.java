package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.LiftManualOverride;
import KBot.commands.MoveLifter;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The lift subsystem uses Talon SRX position mode PID using an encoder to
 * go to and maintain a given height.
 */
public class Lift extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static enum offset { RAISE, LOWER };
	public static enum level { LVL0, LVL1, LVL2, LVL3, LVL4, LVL5 };
	public final static double ENCODER_COUNTS_PER_INCH = 178;	//TODO: tune this by trial and error
	
	private int setpoint=0, relative=0;
	
	// WE ONLY NEED TO CONTROL liftTalon1, since the other 2 are slaved to it using FOLLOWER mode.
	
	public Lift()
	{
		//The profile setting and control mode was set in the RobotMap. This function should only reset the encoders and define the PID values
		// for all three talon  motors.
		RobotMap.liftTalon1.setProfile(0);
		RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
		RobotMap.liftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.liftTalon1.reverseOutput(true); 	// Make it so that positive is up. FOR CLOSED LOOP ONLY.
		RobotMap.liftTalon1.reverseSensor(false);	// encoder readout is currently opposite to motor direction
		RobotMap.liftTalon1.setVoltageRampRate(30.0);		// use if necessary
		RobotMap.liftTalon1.setCloseLoopRampRate(30.0);
		RobotMap.liftTalon1.setPID(5.0, 0.0, 0.0);
		resetEncoders();
		RobotMap.liftTalon1.set(0);
		RobotMap.liftTalon1.enableControl();
	}

    public void initDefaultCommand() 
    {
    	//setDefaultCommand(new LiftManualOverride());
    }
    
    public void setSpeed(double speed)
    {
    	//Calling this at 50Hz is a bad idea. Preferably set this somewhere else, like at the start of a command
    	//Better?? -Mr. Wood
    	//(I was worried about other commands changing the control mode.)
		if (RobotMap.liftTalon1.getControlMode() != ControlMode.PercentVbus) {
			RobotMap.liftTalon1.changeControlMode(ControlMode.PercentVbus);
			RobotMap.liftTalon1.enableControl();	//is it needed?
		} 
		RobotMap.liftTalon1.set(-speed); // Make positive up
    }

    public void setPosition(level lvl, offset off)
    {
    	//This should stay, since the override will not cancel the ability of other buttons to run this code
		if (Robot.oi.operator.getOverride()  && Robot.isTeleop()) {
			System.out.println("Lift ignored a command due to Manual Override");
			return;
		}
		if (RobotMap.liftTalon1.getControlMode() != ControlMode.Position) {
			RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
		} 
		if (lvl!=null) {
			switch (lvl) {
			case LVL0:
				setpoint = 0;
				break;
			case LVL1:
				setpoint = (int)(ENCODER_COUNTS_PER_INCH*12);
				break;
			case LVL2:
				setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*2);
				break;
			case LVL3:
				setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*3);
				break;
			case LVL4:
				setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*4);
				break;
			case LVL5:
				setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*5);
				break;
			}
		}
		if (off!=null) {
			switch (off) {
			case RAISE:
				relative = (int)(ENCODER_COUNTS_PER_INCH*9);
				break;
			case LOWER:
				relative = 0;
				break;
			}			
		}

		RobotMap.liftTalon1.set(setpoint+relative);
    }
    
    public void resetEncoders()
    {
		RobotMap.liftTalon1.setPosition(0.0);
		RobotMap.liftTalon2.setPosition(0.0);
		RobotMap.liftTalon3.setPosition(0.0);
    }
    
    public void stop()
    {
		if (RobotMap.liftTalon1.getControlMode() != ControlMode.Position) {
			RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
		} 

		RobotMap.liftTalon1.set(RobotMap.liftTalon1.getPosition());	// set setpoint to current position
		RobotMap.liftTalon1.ClearIaccum();							// clear built up error term
		
		// Leave PID on to maintain the position.
	}
    
    public int getPIDError()
    {
    	return RobotMap.liftTalon1.getClosedLoopError();
    }
    
	private static int overCurrentCount=0;
    public void checkMotors()
    {
    	if (RobotMap.liftTalon1.getOutputCurrent()>4.0)
    		overCurrentCount++;
    	else
    		overCurrentCount=0;
    	if (RobotMap.liftTalon1.getAnalogInVelocity()==0 && overCurrentCount>25)
    	{
    		// We are stalled, so stop at this point
    		System.out.println("Lift stalled; telling it to stay at:"+RobotMap.liftTalon1.getPosition());
    		RobotMap.liftTalon1.set(RobotMap.liftTalon1.getPosition());
    	}
		//System.out.println("Lift talon 1 current="+RobotMap.liftTalon1.getOutputCurrent()+" velocity="+RobotMap.liftTalon1.getAnalogInVelocity());
		//System.out.println("Lift talon 2 current="+RobotMap.liftTalon2.getOutputCurrent()+" velocity="+RobotMap.liftTalon2.getAnalogInVelocity());
		//System.out.println("Lift talon 3 current="+RobotMap.liftTalon3.getOutputCurrent()+" velocity="+RobotMap.liftTalon3.getAnalogInVelocity());
    }

}


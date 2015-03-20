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
	
	private final static int THRESHOLD = 10;	
    
	public Claw()
	{
		RobotMap.clawTalon.setProfile(0);
		RobotMap.clawTalon.changeControlMode(ControlMode.Position);
		RobotMap.clawTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		//RobotMap.clawTalon.reverseSensor(true);	// if needed
		//RobotMap.clawTalon.setVoltageRampRate(6);		// use if necessary
		RobotMap.clawTalon.setPID(0.0, 0.0, 0.0);
		RobotMap.clawTalon.enableControl();
	}
	
    public void initDefaultCommand() 
    {
    }
    
    public void setAngle(int angle)
    {
    	int pos = angle;				//TODO: calculate setpoint from angle here
    	RobotMap.clawTalon.set(pos);
    }
    
    public void open()
    {
    	setAngle(180);			//TODO: trial and error!!
    }
  
    public void close()
    {
    	setAngle(10);			//TODO: trial and error!!
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
    
    public void setSpeed(double speed)
    {
		RobotMap.clawTalon.set(speed);
	}
     
    //This may not be necessary depending on if the limit switches act the way we want them to
    public boolean isLimitSwitchFaulted()
    {
    	return false; //TODO: put back:  RobotMap.clawTalon.getFaultForLim()!=0 || RobotMap.clawTalon.getFaultRevLim()!=0;
    }
    
    // Rename to OnTarget to fit with convention
    public boolean isFinished()
    {
    	return RobotMap.clawTalon.getClosedLoopError()<THRESHOLD;
    }
    
    public int getPIDError()
    {
    	return RobotMap.clawTalon.getClosedLoopError();
    }
    
    //This may not be necessary if the limit switches allow us to reverse
    public void driveOffLimitSwitch()
    {
		if (RobotMap.clawTalon.getFaultForLim()!=0) {
			// Forward limit switch fault:
			
			if (RobotMap.clawTalon.isFwdLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move back:
				if (RobotMap.clawTalon.getControlMode() != ControlMode.PercentVbus) {
					RobotMap.clawTalon.changeControlMode(ControlMode.PercentVbus);
				}
				RobotMap.clawTalon.enableLimitSwitch(false, false);
				RobotMap.clawTalon.set(-0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.clawTalon.changeControlMode(ControlMode.Position);
				RobotMap.clawTalon.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.claw.stop();
				// and wait for fault to clear
			}
			
		} else if (RobotMap.clawTalon.getFaultRevLim()!=0) {
			// Reverse limit switch fault:
			
			if (RobotMap.clawTalon.isRevLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move forward
				if (RobotMap.clawTalon.getControlMode() != ControlMode.PercentVbus) {
					RobotMap.clawTalon.changeControlMode(ControlMode.PercentVbus);
				}
				RobotMap.clawTalon.enableLimitSwitch(false, false);
				RobotMap.clawTalon.set(0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.clawTalon.changeControlMode(ControlMode.Position);
				RobotMap.clawTalon.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.claw.stop();
				// and wait for fault to clear
			}
		}    	
    }
}


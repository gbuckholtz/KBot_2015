package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
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
	
	// WE ONLY NEED TO CONTROL liftTalon1, since the other 2 are slaved to it using FOLLOWER mode.
	
	public Lift()
	{
		RobotMap.liftTalon1.setProfile(0);
		RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
		RobotMap.liftTalon1.setFeedbackDevice(FeedbackDevice.QuadEncoder);
		RobotMap.liftTalon1.reverseSensor(true);	// encoder readout is currently opposite to motor direction
		//RobotMap.liftTalon1.setVoltageRampRate(6);		// use if necessary
		RobotMap.liftTalon1.setPID(10.0, 0.0, 0.0);
		RobotMap.liftTalon1.enableControl();
	}

    public void initDefaultCommand() 
    {
    	setDefaultCommand (new MoveLifter()); //TODO: Maybe change to drive down onto lower limit switch and reset encoders
    }

    public void setPosition(int pos)
    {
    	RobotMap.liftTalon1.set(pos);
    }
    
    public void resetEncoders()
    {
		RobotMap.liftTalon1.setPosition(0.0);
		RobotMap.liftTalon2.setPosition(0.0);
		RobotMap.liftTalon3.setPosition(0.0);
    }
    
    public void stop()
    {
		if (RobotMap.liftTalon1.getControlMode() == ControlMode.Voltage) {
			RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
		} 

		RobotMap.liftTalon1.set(RobotMap.liftTalon1.getPosition());	// set setpoint to current position
		RobotMap.liftTalon1.ClearIaccum();							// clear built up error term
		
		// Leave PID on to maintain the position.
	}
    
    public boolean isLimitSwitchFaulted()
    {
    	return RobotMap.liftTalon1.getFaultForLim()!=0 || RobotMap.liftTalon1.getFaultRevLim()!=0;
    }
    
    public int getPIDError()
    {
    	return RobotMap.liftTalon1.getClosedLoopError();
    }
    
    public void driveOffLimitSwitch()
    {
		if (RobotMap.liftTalon1.getFaultForLim()!=0) {
			// Forward limit switch fault:
			
			if (RobotMap.liftTalon1.isFwdLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move back:
				if (RobotMap.liftTalon1.getControlMode() != ControlMode.Voltage) {
					RobotMap.liftTalon1.changeControlMode(ControlMode.Voltage);
				}
				RobotMap.liftTalon1.enableLimitSwitch(false, false);
				RobotMap.liftTalon1.set(-0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
				RobotMap.liftTalon1.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.lift.stop();
				// and wait for fault to clear
			}
			
		} else if (RobotMap.liftTalon1.getFaultRevLim()!=0) {
			// Reverse limit switch fault:
			
			if (RobotMap.liftTalon1.isRevLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move forward
				if (RobotMap.liftTalon1.getControlMode() != ControlMode.Voltage) {
					RobotMap.liftTalon1.changeControlMode(ControlMode.Voltage);
				}
				RobotMap.liftTalon1.enableLimitSwitch(false, false);
				RobotMap.liftTalon1.set(0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.liftTalon1.changeControlMode(ControlMode.Position);
				RobotMap.liftTalon1.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.lift.stop();
				// and wait for fault to clear
			}
		}    	
    }
}


package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.GetVisionData;
import KBot.commands.MoveWrist;
import KBot.commands.OpenClaw;
import KBot.commands.WristController;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.CANTalon.FeedbackDevice;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The Wrist subsystem uses Talon SRX position mode PID using a potentiometer to
 * rotate the wrist.
 */
public class Wrist extends Subsystem {
	
	private final static int THRESHOLD = 10;	

	public Wrist()
	{
		RobotMap.wristTalon.setProfile(0);
		RobotMap.wristTalon.changeControlMode(ControlMode.Position);
		RobotMap.wristTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		//RobotMap.wristTalon.reverseSensor(true);	// if needed
		//RobotMap.wristTalon.setVoltageRampRate(6);		// use if necessary
		RobotMap.wristTalon.setPID(0.0, 0.0, 0.0); //TODO: make p=1.0 or something
		RobotMap.wristTalon.enableControl(); //TODO: make this enableControl
	}

    public void initDefaultCommand() {
        setDefaultCommand(new WristController());   	
    }
    
    public void setAngle(double angle)
    {
		if (Robot.oi.operator.getOverride()  && Robot.isTeleop()) {
			System.out.println("Wrist ignored a command due to Manual Override");
			return;
		}
		double pos = angle;				//TODO: calculate setpoint from angle here
    	RobotMap.wristTalon.set(pos);
    }
    
    public void up()
    {
    	setAngle(180);			//TODO: trial and error!!
    }
    
    public void level()
    {
    	setAngle(20);			//TODO: trial and error!!
    }

    public void down()
    {
    	setAngle(5);			//TODO: trial and error!!
    }
    
    public void setVoltageMode()
    {
		RobotMap.wristTalon.changeControlMode(ControlMode.PercentVbus);
		RobotMap.wristTalon.enableControl();	//is it needed?
		RobotMap.wristTalon.set(0);
    }
    
    public void setPositionMode()
    {
		RobotMap.wristTalon.changeControlMode(ControlMode.Position);
		RobotMap.wristTalon.enableControl();	//is it needed?
		RobotMap.wristTalon.set(RobotMap.wristTalon.getPosition());	// set setpoint to current position
    }
    
    public void setSpeed(double speed)
    {
		RobotMap.wristTalon.set(-speed); // Make positive up
    }
    public void stop()
    {
		if (RobotMap.wristTalon.getControlMode() != ControlMode.Position) {
			RobotMap.wristTalon.changeControlMode(ControlMode.Position);
		} 

		RobotMap.wristTalon.set(RobotMap.wristTalon.getPosition());	// set setpoint to current position
		RobotMap.wristTalon.ClearIaccum();							// clear built up error term
		
		// Leave PID on to maintain the position.
	}
    
    public boolean isLimitSwitchFaulted()
    {
    	return false; //TODO: put back:  RobotMap.wristTalon.getFaultForLim()!=0 || RobotMap.wristTalon.getFaultRevLim()!=0;
    }
    
    public boolean isFinished()
    {
    	return RobotMap.wristTalon.getClosedLoopError()<THRESHOLD;
    }
    
    public int getPIDError()
    {
    	return RobotMap.wristTalon.getClosedLoopError();
    }
    
    public void driveOffLimitSwitch()
    {
		if (RobotMap.wristTalon.getFaultForLim()!=0) {
			// Forward limit switch fault:
			
			if (RobotMap.wristTalon.isFwdLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move back:
				if (RobotMap.wristTalon.getControlMode() != ControlMode.PercentVbus) {
					RobotMap.wristTalon.changeControlMode(ControlMode.PercentVbus);
				}
				RobotMap.wristTalon.enableLimitSwitch(false, false);
				RobotMap.wristTalon.set(-0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.wristTalon.changeControlMode(ControlMode.Position);
				RobotMap.wristTalon.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.wrist.stop();
				// and wait for fault to clear
			}
			
		} else if (RobotMap.wristTalon.getFaultRevLim()!=0) {
			// Reverse limit switch fault:
			
			if (RobotMap.wristTalon.isRevLimitSwitchClosed()) {
				// switch to voltage control, disable the switch and move forward
				if (RobotMap.wristTalon.getControlMode() != ControlMode.PercentVbus) {
					RobotMap.wristTalon.changeControlMode(ControlMode.PercentVbus);
				}
				RobotMap.wristTalon.enableLimitSwitch(false, false);
				RobotMap.wristTalon.set(0.25);
			} else {
				// the limit switch is off now, so restore position control mode and enable switch
				RobotMap.wristTalon.changeControlMode(ControlMode.Position);
				RobotMap.wristTalon.enableLimitSwitch(true, true);
				//stop so we don't drive onto the switch again
				Robot.wrist.stop();
				// and wait for fault to clear
			}
		}    	
    }
}


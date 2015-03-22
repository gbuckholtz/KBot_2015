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
	
	private final static double THRESHOLD = 5.0;	

	public Wrist()
	{
		RobotMap.wristTalon.setProfile(0);
		RobotMap.wristTalon.changeControlMode(ControlMode.Position);
		RobotMap.wristTalon.setFeedbackDevice(FeedbackDevice.AnalogPot);
		//RobotMap.wristTalon.reverseSensor(true);	// NOT needed
		RobotMap.wristTalon.setVoltageRampRate(24);		// use if necessary
		RobotMap.wristTalon.setCloseLoopRampRate(0);
		RobotMap.wristTalon.setPID(28.0, 0.001, 4.0, 0.0, 10, 0.0, 0);
		RobotMap.wristTalon.enableControl();
	}

    public void initDefaultCommand() {
        setDefaultCommand(new WristController());   	// Autonomous does not work with this in!!!
    }
    
    public void setAngle(double angle)
    {
    	// This can be removed - the WristManualOverride controls the wrist's speed
		if (Robot.oi.operator.getOverride()  && Robot.isTeleop()) {
			System.out.println("Wrist ignored a command due to Manual Override");
			return;
		}
		double pos = 419+angle*4.626;
    	RobotMap.wristTalon.set(pos);
    	//System.out.println("Wrist set to angle adc="+pos);
    }
    
    //I don't think the wrist should have specific "up" or "level" methods. The setAngle method should work enough, which means this
    // can be removed.
    // For autonomous I think it should ... it makes more sense to have the values for these "fixed" positions in one place in the sub-system
    // than spread out in a whole load of places in different autonomous modes.  It could be done as constants or an enum if you think that
    // would be better?
    public void up()
    {
    	setAngle(105);
    }
    
    public void level()
    {
    	setAngle(0);
    }

    public void down()
    {
    	setAngle(-30);	
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
    
	private static int overCurrentCount=0;
    public void checkMotors()
    {
    	if (RobotMap.wristTalon.getOutputCurrent()>2.5)
    		overCurrentCount++;
    	else
    		overCurrentCount=0;
    	if (RobotMap.wristTalon.getAnalogInVelocity()==0 && overCurrentCount>25)
    	{
    		// We are stalled, so stop at this point
    		System.out.println("Wrist stalled; telling it to stay at:"+RobotMap.wristTalon.getPosition());
    		RobotMap.wristTalon.set(RobotMap.wristTalon.getPosition());
    	}
		//System.out.println("Wrist current="+RobotMap.wristTalon.getOutputCurrent()+" velocity="+RobotMap.wristTalon.getAnalogInVelocity());
    }
    
    public boolean onTarget()
    {
    	return Math.abs(RobotMap.wristTalon.getPosition()-RobotMap.wristTalon.getSetpoint())<THRESHOLD;
    }
    
    public int getPIDError()
    {
    	return RobotMap.wristTalon.getClosedLoopError();
    }
}


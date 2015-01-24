package kbot.pid.subsystems;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import kbot.pid.commands.RightDriveCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class RightDrivePID extends PIDSubsystem {

    // Initialize your subsystem here
    public RightDrivePID() {
    	
    	super("Right",2.0,0.5,0,1.0);
    	setSetpoint(0);
    	setPercentTolerance(5);
    	RobotMap.rightEncoder.reset();
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new RightDriveCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	
    	return RobotMap.rightEncoder.getRate()/4000;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	output = -output;
    	RobotMap.rightTalon1.set(output);
    	RobotMap.rightTalon2.set(output);
    	RobotMap.rightTalon3.set(output);
    }
}

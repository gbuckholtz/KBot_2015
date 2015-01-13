package kbot.pid.subsystems;

import kbot.pid.commands.EncoderTest;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class DistancePID extends PIDSubsystem {
	static final double kP=1,kI=1,kD=1;
    
	
	// Initialize your subsystem here
    public DistancePID() {
    	
    	super("DistancePID",kP,kI,kD);
    	
    	
    	
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new EncoderTest());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return 0.0;
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    }
}

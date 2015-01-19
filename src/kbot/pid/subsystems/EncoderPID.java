package kbot.pid.subsystems;

import kbot.pid.RobotMap;
import kbot.pid.commands.EncoderPosition;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class EncoderPID extends PIDSubsystem {

    // Initialize your subsystem here
    public EncoderPID() {
    	super("EncoderPID",0.75,0.11,0.1);
    	setAbsoluteTolerance(5);
    	double max = .75;
    	setOutputRange(-max,max);
    	setSetpoint(0);

        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new EncoderPosition());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return RobotMap.encoder.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	RobotMap.pidTalon.set(-output);
    	System.out.println("Encoder: " + RobotMap.encoder.get() + "  |  Motor: " + output);
    }
}

package kbot.pid.subsystems;

import kbot.pid.RobotMap;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class LeftDrivePID extends PIDSubsystem {

    // Initialize your subsystem here
    public LeftDrivePID() {
    	super("LeftDrivePID",0.75,0.11,0.1);
    	setAbsoluteTolerance(5);
    	double max = .75;
    	setOutputRange(-max,max);
    	setSetpoint(0);
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return RobotMap.leftEncoder.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	RobotMap.leftMotor1.set(-output);
    	RobotMap.leftMotor2.set(-output);
    	RobotMap.leftMotor3.set(-output);
    }
}

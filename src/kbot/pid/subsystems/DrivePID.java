package kbot.pid.subsystems;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import kbot.pid.commands.DrivePIDCommand;
import kbot.pid.commands.feedbackCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivePID extends PIDSubsystem {

    // Initialize your subsystem here
    public DrivePID() {
    	super(0.005,0,0);//0.01, 0.001, 0.1); //(0.15,0.1,0.3);
    	setSetpoint(0);
    	setPercentTolerance(10);
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new feedbackCommand());
        }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
    	return -RobotMap.leftEncoder.get();
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	output = (output * 0.4);
    	RobotMap.leftTalon1.set(output);
    	RobotMap.leftTalon2.set(output);
    	RobotMap.leftTalon3.set(output);
    	/*RobotMap.rightTalon1.set(-output);
    	RobotMap.rightTalon2.set(-output);
    	RobotMap.rightTalon3.set(-output);*/
    }
    
    public void out() {
    	SmartDashboard.putNumber("LeftEncRate", RobotMap.leftEncoder.getRate());
    	SmartDashboard.putNumber("LeftEncPos", RobotMap.leftEncoder.get());
    	SmartDashboard.putNumber("rightEncRate", RobotMap.rightEncoder.getRate());
    	SmartDashboard.putNumber("rightEncPos", RobotMap.rightEncoder.get());
    }
}

package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.TrackYellowTote;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class VisionPIDDrive extends PIDSubsystem {
    // Initialize your subsystem here
    public VisionPIDDrive() {
    	super(1.0,0.0,0.0);
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	setSetpoint(0.0);
    	//enable();
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
    	if(Robot.visionSubsystem.blobCount==0)
    		return 0.0;
    	return (Robot.visionSubsystem.boxX-160)/(Math.pow(Robot.visionSubsystem.boxSize,0.9)+160);    //320;
    }
    
    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
    	//System.out.println(output);
    	RobotMap.drive.tankDrive(0.6-output/3.2,0.6+output/3.2);
    }
}

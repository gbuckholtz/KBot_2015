package kbot.subsystems;

import kbot.RobotMap;
import kbot.commands.PositionCommand;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class PositionPID extends PIDSubsystem {

	double point=0.5;
	static final double kP=1,kI=1,kD=1;
    // Initialize your subsystem here
    public PositionPID() {
    	super("PositionPID",kP,kI,kD);
    	setSetpoint(point);
    	double max = .75;
    	setOutputRange(-max, max);
    	setInputRange(0,1800);
    	setAbsoluteTolerance(5);
    	enable();
        // Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    }
    
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new PositionCommand());
    }
    
    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
		return RobotMap.pot.get();
    	//return 0.0;
    }
    
    public void calibratePID() {
    	//System.out.println(this.returnPIDInput());
    }
    
    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	RobotMap.pidTalon.set(-output);
    	System.out.println("Output:"+output+", Pot:"+RobotMap.pot.get()+", Encoder:"+RobotMap.encoder.get()+",   Rate:"+RobotMap.encoder.getRate());
    }
}

package kbot.pid.commands;

import kbot.pid.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DrivePID extends Command {
	
	public double setpoint = 0.0;
	
    public DrivePID() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	new DrivePID(0.0);
    	
    }
    
    public DrivePID(double setpoint)
    {
    	this.setpoint = setpoint;
    }

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	SmartDashboard.putNumber("Setpoint", setpoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return Robot.encoderpid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

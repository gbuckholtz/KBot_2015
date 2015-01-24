package kbot.pid.commands;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class feedbackCommand extends Command {
	
	int maxRateR;
	int maxRateL;
    public feedbackCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivepid);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.maxRateL = 0;
    	this.maxRateR = 0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivepid.out();
    	SmartDashboard.putNumber("Setpoint", Robot.rightDrivepid.getSetpoint());
    	/*int Rrate = (int)RobotMap.rightEncoder.getRate();
    	int Lrate = (int)RobotMap.leftEncoder.getRate();
    	if(Math.abs(Rrate) > maxRateR)
    	{
    		this.maxRateR = Math.abs(Rrate);
    	}
    	if(Math.abs(Lrate) > maxRateL)
    	{
    		this.maxRateL = Math.abs(Lrate);
    	}
    	System.out.println("Rrate: " + Rrate + " Max: " + this.maxRateR + "| Lrate: " + Lrate + " Max: " + maxRateL);*/
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

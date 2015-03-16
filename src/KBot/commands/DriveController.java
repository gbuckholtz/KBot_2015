package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveController extends Command {

    public DriveController() 
    {
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {	
    	Robot.drivetrain.driveCurve(0.0, 0.0);	// stop
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double left = Robot.oi.leftDriver.getY();
    	double right = Robot.oi.rightDriver.getY();
    	boolean leftTrigger = Robot.oi.leftDriver.getTrigger();
    	boolean rightTrigger = Robot.oi.rightDriver.getTrigger();
    
    	Robot.drivetrain.drive(left,right,leftTrigger,rightTrigger);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    	Robot.drivetrain.driveCurve(0.0, 0.0);	// stop
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    	end();
    }
}

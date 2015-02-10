package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveController extends Command {

    public DriveController() 
    {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    }

    // Called just before this Command runs the first time
    protected void initialize()
    {	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	double rawLeftJoy = Robot.oi.driver.getJoyLeftY();
    	double rawRightJoy = Robot.oi.driver.getJoyRightY();
    	
    	if(Math.abs(rawLeftJoy) > 0.01)
    	{
    		Robot.drivetrain.setLeftDrive(rawLeftJoy);
    	}
    	
    	if(Math.abs(rawRightJoy) > 0.01)
    	{
        	Robot.drivetrain.setRightDrive(rawRightJoy);
    	}
    	if(Robot.oi.driver.getLB())
    	{
    		Robot.drivetrain.turnLeft();
    	}
    	if(Robot.oi.driver.getRB())
    	{
    		Robot.drivetrain.turnRight();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished()
    {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() 
    {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() 
    {
    }
}

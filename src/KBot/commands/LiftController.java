package KBot.commands;

import KBot.Robot;
import KBot.commands.LiftController.offset;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftController extends Command 
{
	int setpoint;
	public enum offset {RAISE, LOWER};

    public LiftController(int setpoint) 
    {
        this.setpoint = setpoint;
    }

	public LiftController(offset e)
    {
    	switch(e)
    	{
    	case RAISE:
    		Robot.lift.setRelativeSetpoint();
			break;
    	case LOWER:
    		Robot.lift.setRelativeSetpoint();
    		break;
    	}
    }
    

    // Called just before this Command runs the first time
    protected void initialize() 
    {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() 
    {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() 
    {
        return true;
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

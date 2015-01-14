package kbot.commands;

import kbot.Robot;
import kbot.subsystems.PositionPID;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.command.PIDCommand;

/**
 *
 */
public class PositionCommand extends Command {

    public PositionCommand() {
    	//requires(subsytems.PositionPID);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.positionSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.positionSubsystem.setSetpoint(500);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	boolean a = Robot.oi.stick.getA();
    	boolean b = Robot.oi.stick.getB();
    	boolean x = Robot.oi.stick.getX();
    	
    	if(a)
    	{
    		Robot.positionSubsystem.setSetpoint(500);
    	}
    	else if(b)
    	{
    		Robot.positionSubsystem.setSetpoint(1000);
    	}
    	
    	else if(x)
    	{
    		Robot.positionSubsystem.setSetpoint(750);
    	}
    	
    	if(Robot.positionSubsystem.onTarget())
    		Robot.positionSubsystem.disable();
    	else
    		Robot.positionSubsystem.enable();
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

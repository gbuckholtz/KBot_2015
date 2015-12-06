package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CollapseCommand extends Command {

    public CollapseCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.claw);
    	requires(Robot.lift);
    	requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setInterruptible(false);
    	System.out.println("Init Collapse");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Opening Claw");
    	Robot.claw.open();
    	if (Robot.claw.onTarget())
    	{
    		System.out.println("Collapsing");
    		Robot.wrist.setAngle(105);
    		Robot.claw.close();
    	}
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

package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrepareLift extends Command {

    public PrepareLift() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.setTimeout(1);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.setSpeed(-0.5); // Drive down slowly, we're just going to make sure we're at the bottom
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.lift.setSpeed(0);
    	Robot.lift.resetEncoders();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

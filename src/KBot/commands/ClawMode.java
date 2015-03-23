package KBot.commands;

import KBot.Robot;
import KBot.subsystems.Claw;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawMode extends Command {

	Claw.Mode mode;
    public ClawMode(Claw.Mode mode) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.mode = mode;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if(Robot.oi.operator.getOverride())
    		Robot.claw.clawMode = mode;
    	else
    		Robot.claw.clawMode = Claw.Mode.PID;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

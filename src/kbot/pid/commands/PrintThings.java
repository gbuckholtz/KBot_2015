package kbot.pid.commands;

import kbot.pid.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class PrintThings extends Command {

    public PrintThings() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.TalonPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.TalonPID.out();
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

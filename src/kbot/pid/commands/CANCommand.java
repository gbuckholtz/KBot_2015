package kbot.pid.commands;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CANCommand extends Command {

	private static double setpoint;
	
    public CANCommand(double setpoint) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.TalonPID.setPosition(setpoint);
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

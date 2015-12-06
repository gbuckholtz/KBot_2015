package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class WristManualOverride extends Command {

    public WristManualOverride() {
        requires(Robot.wrist);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	System.out.println("Wrist Manual override init");
    	Robot.wrist.setVoltageMode();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.wrist.setSpeed(Robot.oi.operator.getManualX());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.operator.getOverride();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.wrist.setSpeed(0);
    	System.out.println("Wrist Manual Override End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

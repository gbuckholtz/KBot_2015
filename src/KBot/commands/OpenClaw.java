package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {
	private final static double TIMEOUT = 1.0;			// second.

    public OpenClaw() {
		super("OpenClaw", TIMEOUT);
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.setPositionMode();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		Robot.claw.open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (isTimedOut()) {
			System.out.println("OpenClaw timed out");
			return true;
		}
		return Robot.claw.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
		//Robot.claw.stop(); // leave PID running??
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

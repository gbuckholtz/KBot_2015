package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CloseClaw extends Command {
	private final static double TIMEOUT = 3.0;			// second.
	private static boolean manualControl = false;
    public CloseClaw() {
		super("CloseClaw", TIMEOUT);
        requires(Robot.claw);
    }
    
    public CloseClaw(boolean control)
    {
    	super("CloseClaw", TIMEOUT);
    	requires(Robot.claw);
    	manualControl = control;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
   }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.claw.setPositionMode();
    		Robot.claw.close();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (isTimedOut()) {
    		System.out.println("CloseClaw timed out");
    		return true;
    	}
    	return Robot.claw.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
		//Robot.claw.stop(); //Leave PID running
   }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

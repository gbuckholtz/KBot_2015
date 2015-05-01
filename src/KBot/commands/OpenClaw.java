package KBot.commands;

import KBot.Robot;
import KBot.subsystems.Claw;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class OpenClaw extends Command {
	private final static double TIMEOUT = 2.0;			// second.
	private boolean speedMode;
    public OpenClaw() {
		super("OpenClaw", TIMEOUT);
        requires(Robot.claw);
    }
    
    
    public OpenClaw(double time)
    {
    	super("OpenClaw", time);
    	requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	if (Robot.isTeleop() && Robot.oi.operator.getOverride())
    		speedMode = Robot.claw.clawMode == Claw.Mode.SPEED;
    	else
    		speedMode = false;
    	
    	if(speedMode)
    		Robot.claw.setVoltageMode();
    	else
    		Robot.claw.setPositionMode();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (speedMode)
    		Robot.claw.setSpeed(Robot.claw.openSpeed);
    	else
    		Robot.claw.open();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if (speedMode)
    		return !Robot.oi.operator.getOpen();
    	else
    	{
			if (isTimedOut()) {
				System.out.println("OpenClaw timed out");
				return true;
			}
			return Robot.claw.onTarget();
    	}
    }

    // Called once after isFinished returns true
    protected void end() {
    	if (speedMode)
    		Robot.claw.setSpeed(0);
		//Robot.claw.stop(); // leave PID running
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

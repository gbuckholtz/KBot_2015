package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LiftManualOverride extends Command {

    public LiftManualOverride() {
        requires(Robot.lift);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.lift.setSpeed(0);
    	//System.out.println("Lift Manual Override init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.lift.setSpeed(Robot.oi.operator.getManualY());
    	if(Robot.oi.operator.get0())
    		Robot.lift.resetEncoders();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;//!Robot.oi.operator.getOverride();
    }

    // Called once after isFinished returns true
    protected void end()
    {
    	//System.out.println("Lift Manual Override End");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

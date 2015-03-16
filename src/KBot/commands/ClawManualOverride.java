package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClawManualOverride extends Command {

    public ClawManualOverride() {
        requires(Robot.claw);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.claw.setVoltageMode();
    	System.out.println("ClawManualOverride init");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.claw.setSpeed(Robot.oi.operator.getManualX());
    	System.out.println(RobotMap.clawTalon.getAnalogInPosition());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return !Robot.oi.operator.getOverride();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.claw.setSpeed(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

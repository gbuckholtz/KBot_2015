package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
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
    	double speed=Robot.oi.operator.getManualY();
    	if (speed > 1.0)
    	{
    		speed=1.0;
    	}
    	if (speed<-0.8)
    	{
    		speed=-0.8;
    	}
    	Robot.lift.setSpeed(speed);
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
    	// Set current encoder position to what the levels are set at:
    	double val= Robot.oi.operator.getLevel() * Robot.lift.ENCODER_COUNTS_PER_INCH*12;
    	if (Robot.oi.operator.isRaised())
    		val += Robot.lift.ENCODER_COUNTS_PER_INCH*9;
		RobotMap.liftTalon1.setPosition(val);
		RobotMap.liftTalon2.setPosition(val);
		RobotMap.liftTalon3.setPosition(val);  	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

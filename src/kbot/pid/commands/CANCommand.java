package kbot.pid.commands;

import kbot.pid.Robot;
import kbot.pid.RobotMap;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CANCommand extends Command {

	private static double setPoint;
	
	public CANCommand()
	{
		//new CANCommand(0,"Empty");
		requires(Robot.TalonPID);
	}
	
    public CANCommand(double setpoint, String name) {
        // Use requires() here to declare subsystem dependencies
        // requires(Robot.TalonPID);
    	//setPoint = setpoint;
    	//SmartDashboard.putNumber("setpoint", setPoint);
    	//SmartDashboard.putString("Button", name);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//RobotMap.canTalon.set(setPoint);
    	//SmartDashboard.putNumber("setpoint", setPoint);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Robot.oi.stick.getA())
    	{
    		RobotMap.canTalon.set(5000);
    		SmartDashboard.putNumber("setpoint", 500);
    	}
    	else if(Robot.oi.stick.getB())
    	{
    		RobotMap.canTalon.set(0);
    		SmartDashboard.putNumber("setpoint", 0);
    	}
    	
    	Robot.TalonPID.out();
    }
    
    protected boolean isFinished()
    {
    	return false;
    }
    
    protected void end()
    {
    	
    }
    
    protected void interrupted()
    {
    	
    }
}


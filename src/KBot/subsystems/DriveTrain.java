package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.DriveController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveController());
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turnLeft()
    {
    	setLeftDrive(0.5);
    	setRightDrive(-0.1);
    }
    
    public void turnRight()
    {
    	setRightDrive(0.5);
    	setLeftDrive(-0.1);
    }
    
    public void setLeftDrive(double left)
    {
    	RobotMap.leftDrive1.set(left);
    	RobotMap.leftDrive2.set(left);
    	RobotMap.leftDrive3.set(left);
    }
    
    public void setRightDrive(double right)
    {
    	RobotMap.rightDrive1.set(right);
    	RobotMap.rightDrive2.set(right);
    	RobotMap.rightDrive3.set(right);
    }
}


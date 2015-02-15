package KBot.subsystems;

import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.DriveController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
    private final double DEADBAND = 0.01;
    private final boolean INVERT = true;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
    	setDefaultCommand(new DriveController());
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void drive(double left, double right, boolean leftTrigger, boolean rightTrigger)
    {
    	if (Math.abs(left) < 0.01)
    		left = 0;
    	if (Math.abs(right) < 0.01)
    		right = 0;
    	
    	if(leftTrigger)
    		left = 0;
    	if(rightTrigger)
    		right = 0;
    	
    	if(INVERT)
    		left = -left; right = -right;
    		
    	RobotMap.drive.tankDrive(left, right, true);
    }
}


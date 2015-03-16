package KBot.subsystems;

//import KBot.Robot;
import KBot.RobotMap;
import KBot.commands.DriveController;
//import edu.wpi.first.wpilibj.Relay.Direction;
import edu.wpi.first.wpilibj.Relay.Value;
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
    	
    	//System.out.println("Encoder: " + RobotMap.driveEncoderLeft.get() + " | EncoderRight: " + RobotMap.driveEncoderRight.get());
    	if (left>0.2)
    	{
    		RobotMap.red.set(Value.kReverse);
    	} else {
    		RobotMap.red.set(Value.kForward);
    	}
    	if (right>0.2)
    	{
    		RobotMap.blue.set(Value.kReverse);
    	} else {
    		RobotMap.blue.set(Value.kForward);
    	}
    	if (left<0 || right<0)
    	{
    		RobotMap.green.set(Value.kReverse);
    	} else {
    		RobotMap.green.set(Value.kForward);    		
    	}
    }
    
    public void driveCurve(double dist, double curve) {
    	if (dist>0.2)
    	{
    		RobotMap.red.set(Value.kReverse);
    	} else {
    		RobotMap.red.set(Value.kForward);
    	}
    	if (curve>0.2 || curve<-0.2)
    	{
    		RobotMap.blue.set(Value.kReverse);
    	} else {
    		RobotMap.blue.set(Value.kForward);
    	}
    	if (dist<0 )
    	{
    		RobotMap.green.set(Value.kReverse);
    	} else {
    		RobotMap.green.set(Value.kForward);    		
    	}
    	RobotMap.drive.drive(dist, curve);
    }
}


package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.Robot;
import KBot.RobotMap;


/**
 *
 */
public class AutoJustDrive extends CommandGroup {
    
    public  AutoJustDrive() {
    	
    	double time=RobotMap.autoTimerInput.getValue();//-200;		 Lowest value seen = 198
    	
    	System.out.println("Delay timer value:"+time);
    	
    	addSequential(new DoNothing(time*15/1023));
    	addSequential(new DriveRelative(0.5, 0.0, 2.5));	// drive to auto zone
    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

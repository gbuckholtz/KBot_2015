package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.Robot;
import KBot.RobotMap;
import KBot.subsystems.Lift;

/**
 *
 */
public class AutoJustDrive extends CommandGroup {
    
    public  AutoJustDrive() {
    	// MUST RAISE BEFORE TILTING!!
    	//addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER));
    	double time=RobotMap.autoTimerInput.getValue();/*-200;		// Lowest value seen = 198
    	if (time<0)
    		time=0;*/
    	System.out.println("Delay timer value:"+time);
    	addParallel(new PrepareLift());
    	addSequential(new DoNothing(time*15/1023));
    	//addSequential(new DriveRelative(0.5, 0.0, 2.5));	// drive to auto zone
    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

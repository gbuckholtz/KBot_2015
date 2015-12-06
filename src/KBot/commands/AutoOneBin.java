package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.subsystems.Lift;

/**
 *
 */
public class AutoOneBin extends CommandGroup {
    
    public  AutoOneBin() {
    	// position in front of the bin facing the drivers
    	//addParallel(new OpenClaw());
    	//addSequential(new MoveWrist(0)); // Reach for the bin
       	//addSequential(new CloseClaw()); 
       	//addSequential(new MoveWrist(20)); // Grab the bin and move up at the same time
       	
       	// This starts at the 3.5 second mark
    	addSequential(new DriveRelative(-0.5, 0.0, 2.0));	// drive to auto zone in reverse
    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}
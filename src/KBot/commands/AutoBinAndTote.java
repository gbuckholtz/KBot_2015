package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoBinAndTote extends CommandGroup {
    
    public  AutoBinAndTote() {
    	addSequential(new CloseClaw());
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	//addSequential(new RotateWrist(105));
    	addSequential(new DriveRelative(0.5, 0.0, 0.4));	// move up to first tote
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting tote
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.RAISE));
    	
    	addSequential(new DriveRelative(0.45, -1.0, 0.45));	// turn left 90
    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(-0.4, 0.0, 0.25));	// don't touch it!
    	
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER)); // placeholder??

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

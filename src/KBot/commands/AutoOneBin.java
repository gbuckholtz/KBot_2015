package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoOneBin extends CommandGroup {
    
    public  AutoOneBin() {
    	// position behind the bin
       	addSequential(new CloseClaw());
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));

    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(-0.4, 0.0, 0.25));	// don't touch it!
    	
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER)); // placeholder??

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.subsystems.Lift;

/**
 *
 */
public class AutoOneBin extends CommandGroup {
    
    public  AutoOneBin() {
    	// position behind the bin
       	addSequential(new CloseClaw());
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new PrepareLift());

    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER));
    	addSequential(new DriveRelative(-0.4, 0.0, 0.25));	// don't touch it!
    	
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER)); // placeholder??

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}
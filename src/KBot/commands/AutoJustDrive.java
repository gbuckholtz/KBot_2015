package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.subsystems.Lift;

/**
 *
 */
public class AutoJustDrive extends CommandGroup {
    
    public  AutoJustDrive() {
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER));

    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

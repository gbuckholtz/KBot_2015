package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import KBot.subsystems.Lift;

/**
 *
 */
public class AutoStack2NoVision extends CommandGroup {
    
    public  AutoStack2NoVision() {
       	addSequential(new CloseClaw());
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new PrepareLift());
    	//addSequential(new RotateWrist(105));
    	addSequential(new DriveRelative(0.5, 0.0, 0.4));	// move up to first tote
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting tote out of blind spot
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.RAISE));
    	// raise more while tracking next tote
    	addParallel(new MoveLifter(Lift.level.LVL1, Lift.offset.RAISE));

    	//TODO: move up to next tote, pushing bin out of the way
    	
       	addSequential(new MoveLifter(Lift.level.LVL1, Lift.offset.LOWER));
    	addSequential(new DriveRelative(-0.2, 0.0, 0.1));	// get hook out of the way
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER));
    	addSequential(new DriveRelative(+0.2, 0.0, 0.1));	// back in
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting 3 totes 
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.RAISE));
    	
    	addSequential(new DriveRelative(0.45, -1.0, 0.45));	// turn left 90
    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER));
    	addSequential(new DriveRelative(-0.4, 0.0, 0.25));	// don't touch it!
    	
    	addSequential(new MoveLifter(Lift.level.LVL0, Lift.offset.LOWER)); // placeholder??

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

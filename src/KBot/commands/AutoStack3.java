package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Get 1 bin and stack 3 totes in autonomous.
 * Procedure:
 * - close claw (on bin)
 * - raise it up
 * - tilt back
 * - move up to tote
 * - raise tote
 * - track next tote
 * - lower onto tote
 * - move back, down, fwd
 * - raise stack of 2
 * - track last tote
 * - lower onto tote
 * - move back, down, fwd
 * - raise stack of 3
 * - turn left
 * - drive fwd into auto zone
 * - lower and back up
 */
public class AutoStack3 extends CommandGroup {
    
    public  AutoStack3() {
    	addSequential(new CloseClaw());
    	// MUST RAISE BEFORE TILTING!!
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	//addSequential(new RotateWrist(105));
    	addSequential(new DriveRelative(0.5, 0.0, 0.4));	// move up to first tote
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting tote out of blind spot
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.RAISE));
    	// raise more while tracking next tote
    	addParallel(new MoveLifter(MoveLifter.level.LVL1, MoveLifter.offset.RAISE));
    	addSequential(new TrackYellowTote());
    	addSequential(new DriveRelative(0.0, 0.0, 0.1)); 	// stop
    	
    	addSequential(new MoveLifter(MoveLifter.level.LVL1, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(-0.2, 0.0, 0.1));	// get hook out of the way
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(+0.2, 0.0, 0.1));	// back in
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting 2 totes out of blind spot
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.RAISE));
    	// raise more while tracking next tote
    	addParallel(new MoveLifter(MoveLifter.level.LVL1, MoveLifter.offset.RAISE));
    	addSequential(new TrackYellowTote());
    	addSequential(new DriveRelative(0.0, 0.0, 0.1)); 	// stop
    	
       	addSequential(new MoveLifter(MoveLifter.level.LVL1, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(-0.2, 0.0, 0.1));	// get hook out of the way
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(+0.2, 0.0, 0.1));	// back in
    	addParallel(new DriveRelative(0.2, 0.0, 0.2));		// drive a bit while lifting 3 totes 
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.RAISE));
    	
    	addSequential(new DriveRelative(0.45, -1.0, 0.45));	// turn left 90
    	addSequential(new DriveRelative(0.5, 0.0, 3.0));	// drive to auto zone
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER));
    	addSequential(new DriveRelative(-0.4, 0.0, 0.25));	// don't touch it!
    	
    	addSequential(new MoveLifter(MoveLifter.level.LVL0, MoveLifter.offset.LOWER)); // placeholder??

    	addSequential(new DriveRelative(0.0, 0.0, 10)); 	// stop
    }
}

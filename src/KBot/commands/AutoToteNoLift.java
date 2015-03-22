package KBot.commands;

import KBot.subsystems.Wrist;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoToteNoLift extends CommandGroup {
    
    public  AutoToteNoLift() {
    	addParallel(new OpenClaw());
    	addSequential(new MoveWrist(-36.5));				// pot value of 250
    	addSequential(new CloseClaw());
    	// TOO SLOW addSequential(new MoveWrist(91.2));					// pot value of 841
    	addSequential(new MoveWrist(75));
    	addSequential(new DriveRelative(0.5,  1.0, 0.57));	// turn 90 deg CW
    	addSequential(new DriveRelative(0.5, 0.0, 2.0));	// drive forward __
    	addSequential(new DriveRelative(0.5,  1.0, 0.57));	// turn 90 deg CW
    	addSequential(new MoveWrist(MoveWrist.position.LEVEL));
    	addSequential(new OpenClaw());
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}

package KBot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoToteWithClaw extends CommandGroup {
    
    public  AutoToteWithClaw() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addSequential(new RotateWrist(0)); // Rotate wrist down
    	addSequential(new CloseClaw()); // Close the claw
    	addSequential(new RotateWrist(0)); // Rotate wrist up
    	addSequential(new DriveRelative(0,0,0)); // Turn 90 degrees
    	addSequential(new DriveRelative(0,0,0)); // drive straight
    	addSequential(new RotateWrist(0)); // rotate wrist down
    	addSequential(new OpenClaw()); // open the claw
    	addSequential(new RotateWrist(0)); // rotate wrist up
    	
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

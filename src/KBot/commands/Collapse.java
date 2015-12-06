package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import edu.wpi.first.wpilibj.command.WaitUntilCommand;

/**
 *
 */
public class Collapse extends CommandGroup {
    
    public  Collapse() {
    	
    	System.out.println("Collapsing Robot");

    	this.setInterruptible(false);					// MR WOOD ADDED THIS!!  DO WE WANT IT?
    	
    	if(!Robot.claw.isOpen())
    		addSequential(new OpenClaw(1.0));
    	addParallel(new CloseClaw());
    	addSequential(new MoveWrist(80));
    	addSequential(new WaitCommand(3));
    	//addSequential(new WaitUntilCommand(0));
    }
}

package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
/*
 * The stuff that uses the enum was planned for autonomous mode to be able to abstractly define where the wrist should move.
 * However, it is not used at all in teleop mode, and so should be placed into its own command. This command should only
 * do the mapping between the operator console potentiometer and the wrist potentiometer - that is, this command should move the wrist
 * to a desired angle instead of to an enumerated position. It should be set as the default command for the subsystem and refactored to
 * "Wrist Controller"
 * 
 * Another concern is that the enum will not work as well as we'd like. The code that moves the wrist to an angle should be generated first,
 * then we can simply run the "MoveWristToAngle" function from the autonomous command and pass it the angle we need. Different position variales
 * can be secified to achieve this result.
 */
public class MoveWrist extends Command {
	//MOVE ENUM TO SUBSYSTEM
	private final static double TIMEOUT = 1.0;			// second.
	public enum position { UP, DOWN, LEVEL };
	private position pos;
	private double angle=0;

    public MoveWrist(position pos) {
		super("MoveWrist", TIMEOUT);
		requires(Robot.wrist);
		this.pos=pos;
	}
    
    public MoveWrist(double angle) {
		super("MoveWrist", TIMEOUT);
		requires(Robot.wrist);
		this.angle=angle;
		this.pos=null;	// Indicate that we want to use angle mode
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.wrist.setPositionMode();    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
		if (pos==null) {
			// pos is null, so move to specific angle
			Robot.wrist.setAngle(angle);
		} else {
			switch (pos) {
			case UP:
				Robot.wrist.up();
				break;
			case DOWN:
				Robot.wrist.down();
				break;
			case LEVEL:
				Robot.wrist.level();
				break;
			}
		}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
		if (isTimedOut()) {
			System.out.println("MoveWrist timed out");
			return true;
		}
		return Robot.wrist.isFinished();
    }

    // Called once after isFinished returns true
    protected void end() {
		Robot.wrist.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}

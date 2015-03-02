package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveWrist extends Command {
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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if (Robot.wrist.isLimitSwitchFaulted()) {
			Robot.wrist.driveOffLimitSwitch();
		} else {
			// No faults, so move it
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

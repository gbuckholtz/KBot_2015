package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.command.Command;

/**
 * TrackYellowTote uses the VisionPIDDrive subsystem to drive toward a yellow tote using PID.
 * It finishes when the tote image reaches the MAX_BOX_SIZE, or it times out.
 */
public class TrackYellowTote extends Command {
	private final static double TIMEOUT = 5.0;			// seconds
	private final static double MAX_BOX_SIZE = 177;		// Size of box when we are at the tote
	private final static double RAMP_UP_TIME = 0.8;		// seconds (min 0.020 = no ramping)
	private double rampSpeed=0;

	public TrackYellowTote() {
		super("TrackYellowTote", TIMEOUT);
		requires(Robot.visionPIDSubsystem);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		rampSpeed=0;
        Robot.visionPIDSubsystem.enable();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// Not needed because PIDsubsystem automatically does this. This would override the PID...
		// -- Robot.visionPIDSubsystem.usePIDOutput(Robot.visionPIDSubsystem.returnPIDInput());
		if (rampSpeed<=1.0) {
			rampSpeed += 0.020/RAMP_UP_TIME;	// execute gets called about every 20ms
			Robot.visionPIDSubsystem.setOutputRange(-rampSpeed, rampSpeed);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
    	if(isTimedOut()) {
 			System.out.println("TrackYellowTote timed out");
 		}
		return isTimedOut() || Robot.visionSubsystem.boxSize>MAX_BOX_SIZE;
	}

	// Called once after isFinished returns true
	protected void end() {
        Robot.visionPIDSubsystem.disable();
		RobotMap.drive.tankDrive(0.0,0.0);	// stop
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}

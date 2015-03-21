package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import KBot.subsystems.Lift;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the lift to a given position. This command finishes when it is within
 * the THRESHOLD for STABLE_CYCLE_COUNT cycles, but leaves the PID loop running
 * to maintain the position.
 * Other commands using the lift should make sure they disable PID!
 */

/*
 * The threshold variable and cycle count should be moved into the subsystem. A command should only run the functions and should not define
 * how the functions work.
 * Also, let's rename this to LiftController to keep with our conventions.
 */
public class MoveLifter extends Command {
	private Lift.level level=null;
	private Lift.offset offset=null;
	
	private int cycleCount=0;
	private boolean firstCycle=true;
	
	private final static double TIMEOUT = 2.0;			// seconds. Timeout for any lift command
	private final static int THRESHOLD = 10;	
	private final static int STABLE_CYCLE_COUNT = 10;	// How many cycles output must be within THRESHOLD before being considered "finished"
	

	public MoveLifter(Lift.level lvl, Lift.offset off) {
		super("MoveLifter", TIMEOUT);
		requires(Robot.lift);
		this.level = lvl;
		this.offset = off;
	}

	public MoveLifter(Lift.level lvl) {
		this(lvl,null);
	}

	public MoveLifter(Lift.offset off) {
		this(null,off);
	}

	public MoveLifter() {
		super("MoveLifter", TIMEOUT);
		requires(Robot.lift);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		firstCycle = true;
		Robot.lift.setPosition(level, offset);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (Robot.lift.isLimitSwitchFaulted()) {
			Robot.lift.driveOffLimitSwitch();
		} else {
			// No faults, so give it the setpoint
			Robot.lift.setPosition(level, offset);
			//System.out.println(level+"   "+offset);
		}
		firstCycle=false;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isTimedOut()) {
			System.out.println("MoveLifter timed out");
			return true;
		}
		/*
		int error =  Robot.lift.getPIDError();
		if (error < THRESHOLD && error > -THRESHOLD && !firstCycle) {
			if (cycleCount > STABLE_CYCLE_COUNT) {
				System.out.println("MoveLifter Finished");
				return true;
			} else {
				cycleCount++;
			}
		} else {
			cycleCount=0;
		}*/
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.lift.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		// Don't end because it would be a new setpoint that interrupted it.
	}
}

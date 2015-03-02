package KBot.commands;

import KBot.Robot;
import KBot.RobotMap;
import edu.wpi.first.wpilibj.CANTalon.ControlMode;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Move the lift to a given position. This command finishes when it is within
 * the THRESHOLD for STABLE_CYCLE_COUNT cycles, but leaves the PID loop running
 * to maintain the position.
 * Other commands using the lift should make sure they disable PID!
 */
public class MoveLifter extends Command {
	private int setpoint=0, relativeSetpoint=0;
	private int cycleCount=0;
	private boolean firstCycle=true;
	
	private final static double TIMEOUT = 1.0;			// seconds. Timeout for any lift command
	private final static int THRESHOLD = 10;	
	private final static int STABLE_CYCLE_COUNT = 10;	// How many cycles output must be within THRESHOLD before being considered "finished"
	private final static double ENCODER_COUNTS_PER_INCH = 9.5;	//TODO: tune this by trial and error
	
	public enum offset { RAISE, LOWER };

	public enum level { LVL0, LVL1, LVL2, LVL3, LVL4, LVL5 };

	public MoveLifter(level lvl, offset e) {
		super("SetLiftHeight", TIMEOUT);
		requires(Robot.lift);
		if (lvl!=null) {
			switch (lvl) {
			case LVL0:
				this.setpoint = 0;
				break;
			case LVL1:
				this.setpoint = (int)(ENCODER_COUNTS_PER_INCH*12);
				break;
			case LVL2:
				this.setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*2);
				break;
			case LVL3:
				this.setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*3);
				break;
			case LVL4:
				this.setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*4);
				break;
			case LVL5:
				this.setpoint = (int)(ENCODER_COUNTS_PER_INCH*12*5);
				break;
			}
		}
		if (e!=null) {
			switch (e) {
			case RAISE:
				this.relativeSetpoint = (int)(ENCODER_COUNTS_PER_INCH*9);
				break;
			case LOWER:
				this.relativeSetpoint = 0;
				break;
			}			
		}
	}

	public MoveLifter(level lvl) {
		this(lvl,null);
	}

	public MoveLifter(offset e) {
		this(null,e);
	}

	public MoveLifter(int setpoint) {
		super("SetLiftHeight", TIMEOUT);
		requires(Robot.lift);
		this.setpoint = setpoint;
		this.relativeSetpoint = 0;
	}

	public MoveLifter() {
		super("SetLiftHeight", TIMEOUT);
		requires(Robot.lift);
		this.setpoint = 0;
		this.relativeSetpoint = 0;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		firstCycle = true;
		Robot.lift.resetEncoders();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		firstCycle=false;
		
		if (Robot.lift.isLimitSwitchFaulted()) {
			Robot.lift.driveOffLimitSwitch();
		} else {
			// No faults, so give it the setpoint
			Robot.lift.setPosition(setpoint+relativeSetpoint);
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (isTimedOut()) {
			System.out.println("MoveLifter timed out");
			return true;
		}
		
		int error =  Robot.lift.getPIDError();
		if (error < THRESHOLD && error > -THRESHOLD && !firstCycle) {
			if (cycleCount > STABLE_CYCLE_COUNT) {
				return true;
			} else {
				cycleCount++;
			}
		} else {
			cycleCount=0;
		}
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

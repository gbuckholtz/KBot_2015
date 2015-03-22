package KBot.commands;

import KBot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveRelative extends Command {
	private double speed, curve;
	
    public DriveRelative(double speed, double curve, double time) {
		super("DriveRelative",time);
        // Use requires() here to declare subsystem dependencies
    	requires(Robot.drivetrain);
    	this.speed=speed;
    	this.curve=curve;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.driveCurve(speed, curve);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(isTimedOut()) {
 			System.out.println("DriveRelative timed out");
 		}
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.driveCurve(0.0, 0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}

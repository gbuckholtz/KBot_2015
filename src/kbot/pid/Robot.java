
package kbot.pid;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import kbot.pid.commands.*;
import kbot.pid.subsystems.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static PIDCANTalon pidcantalon;
	public static DrivePID drivepid;
	public static RightDrivePID rightDrivepid;
	public static OI oi;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
		oi = new OI();
		pidcantalon = new PIDCANTalon();
		drivepid = new DrivePID();
		rightDrivepid = new RightDrivePID();
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
        SmartDashboard.putNumber("LP", drivepid.getPIDController().getP());
        SmartDashboard.putNumber("LI", drivepid.getPIDController().getI());
        SmartDashboard.putNumber("LD", drivepid.getPIDController().getD());
        SmartDashboard.putNumber("RP", rightDrivepid.getPIDController().getP());
        SmartDashboard.putNumber("RI", rightDrivepid.getPIDController().getI());
        SmartDashboard.putNumber("RD", rightDrivepid.getPIDController().getD());
    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

    public void autonomousInit() {
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        RobotMap.leftEncoder.reset();
        RobotMap.rightEncoder.reset();
        drivepid.getPIDController().setPID(SmartDashboard.getNumber("LP"),
        		SmartDashboard.getNumber("LI"), SmartDashboard.getNumber("LD"));
        drivepid.setSetpoint(0);
        rightDrivepid.getPIDController().setPID(SmartDashboard.getNumber("RP"), 
        		SmartDashboard.getNumber("RI"), SmartDashboard.getNumber("RD"));
        rightDrivepid.setSetpoint(0);
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	RobotMap.leftEncoder.reset();
    	RobotMap.rightEncoder.reset();
    	Robot.drivepid.getPIDController().reset();
    	Robot.drivepid.enable();
    	rightDrivepid.getPIDController().reset();
    	rightDrivepid.enable();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

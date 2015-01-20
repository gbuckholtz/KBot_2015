
package kbot.pid;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
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
	public static PositionPID positionSubsystem;
	public static LeftDrivePID leftDistanceSubsystem;
	//public static DistancePIDRight rightDistanceSubsystem;
	public static EncoderPID encoderpid;
	public static CANTalonSystem TalonPID;
	public static OI oi;
	double p,i,d;
	double setpoint;

    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    	RobotMap.init();
		
        // instantiate the command used for the autonomous period
        autonomousCommand = new ExampleCommand();
       // positionSubsystem = new PositionPID(); 
        encoderpid = new EncoderPID();
        TalonPID = new CANTalonSystem();
        //leftDistanceSubsystem = new DistancePIDLeft ();
        //rightDistanceSubsystem = new DistancePIDRight ();
        oi = new OI();
        SmartDashboard.putNumber("P", encoderpid.getPIDController().getP());
        SmartDashboard.putNumber("I", encoderpid.getPIDController().getI());
        SmartDashboard.putNumber("D", encoderpid.getPIDController().getD());
        setpoint = 0;
        SmartDashboard.putNumber("P", encoderpid.getPIDController().getP());
        SmartDashboard.putNumber("I", encoderpid.getPIDController().getI());
        SmartDashboard.putNumber("D", encoderpid.getPIDController().getD());
        SmartDashboard.putNumber("Setpoint", setpoint);
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
        
        //encoderpid.getPIDController().startLiveWindowMode();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    public void disabledInit(){
    	RobotMap.leftEncoder.reset();
    	RobotMap.rightEncoder.reset();
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        //SmartDashboard.putData("PIDOut",Robot.encoderpid);
        //p = SmartDashboard.getNumber("P");
        //i = SmartDashboard.getNumber("I");
        //d = SmartDashboard.getNumber("D");
        //setpoint = SmartDashboard.getNumber("Setpoint");
        //encoderpid.getPIDController().setPID(p, i, d);
        //encoderpid.setSetpoint(setpoint);
        SmartDashboard.putBoolean("a_butt", oi.stick.getA());
        SmartDashboard.putBoolean("b_butt", oi.stick.getB());      
        SmartDashboard.putNumber("Encoder", RobotMap.encoder.get());
        //SmartDashboard.putData("PIDOut",Robot.encoderpid);
        p = SmartDashboard.getNumber("P");
        i = SmartDashboard.getNumber("I");
        d = SmartDashboard.getNumber("D");
        setpoint = SmartDashboard.getNumber("Setpoint");
        encoderpid.getPIDController().setPID(p, i, d);
        encoderpid.setSetpoint(setpoint);
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
}

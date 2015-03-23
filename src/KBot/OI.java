package KBot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import KBot.XboxController;
import KBot.commands.ClawManualOverride;
import KBot.commands.CloseClaw;
import KBot.commands.DoNothing;
import KBot.commands.LiftManualOverride;
import KBot.commands.OpenClaw;
import KBot.commands.MoveLifter;
import KBot.commands.WristManualOverride;
import KBot.subsystems.Lift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
	public Joystick leftDriver = new Joystick(0);
	public Joystick rightDriver = new Joystick(1);
	public OperatorController operator = new OperatorController(2);
	
	Button level0 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_0);
	Button level1 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_1);
	Button level2 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_2);
	Button level3 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_3);
	Button level4 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_4);
	Button level5 = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_5);
	Button open = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_OPEN);
	Button close = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_CLOSE);
	Button raise = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_RAISE);
	Button lower = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_LOWER);
	Button override = new JoystickButton(operator.m_joy, OperatorController.OPERATOR_OVERRIDE);
	
	public OI()
	{
		enableLiftButtons();
		clawPosition();
		
		raise.whenPressed(new MoveLifter(Lift.offset.RAISE));
		lower.whenPressed(new MoveLifter(Lift.offset.LOWER));
	}
	
	public void clawVoltage()
	{
		
	}
	
	public void clawPosition()
	{
		open.whenPressed(new OpenClaw());
		close.whenPressed(new CloseClaw());
	}
	
	public void initializeOverrideButtons()
	{
		override.whileHeld(new LiftManualOverride());
		override.whileHeld(new WristManualOverride());	// ONLY USE WRIST OR CLAW AS THEY BOTH USE THE X-AXIS OF THE JOYSTICK
		override.whileHeld(new ClawManualOverride());
	}

	public void enableLiftButtons()
	{
		level1.whenPressed(new MoveLifter(Lift.level.LVL1));
		level2.whenPressed(new MoveLifter(Lift.level.LVL2));
		level3.whenPressed(new MoveLifter(Lift.level.LVL3));
		level4.whenPressed(new MoveLifter(Lift.level.LVL4));
		level5.whenPressed(new MoveLifter(Lift.level.LVL5));
	}
	public void disableLiftButtons()
	{
		level1.whenPressed(new DoNothing());
		level2.whenPressed(new DoNothing());
		level3.whenPressed(new DoNothing());
		level4.whenPressed(new DoNothing());
		level5.whenPressed(new DoNothing());
	}
	
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}


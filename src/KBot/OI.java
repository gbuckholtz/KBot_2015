package KBot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import KBot.commands.ClawMode;
import KBot.commands.CloseClaw;
import KBot.commands.LiftManualOverride;
import KBot.commands.OpenClaw;
import KBot.commands.MoveLifter;
import KBot.commands.WristManualOverride;
import KBot.subsystems.Claw;
import KBot.subsystems.Lift;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
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
		level0.whenPressed(new MoveLifter(Lift.level.LVL0));
		level1.whenPressed(new MoveLifter(Lift.level.LVL1));
		level2.whenPressed(new MoveLifter(Lift.level.LVL2));
		level3.whenPressed(new MoveLifter(Lift.level.LVL3));
		//level4.whenPressed(new MoveLifter(Lift.level.LVL4));
		//level5.whenPressed(new MoveLifter(Lift.level.LVL5));
		
		open.whenPressed(new OpenClaw());
		close.whenPressed(new CloseClaw());
		raise.whenPressed(new MoveLifter(Lift.offset.RAISE));
		raise.whenPressed(new ClawMode(Claw.Mode.SPEED));
		lower.whenPressed(new MoveLifter(Lift.offset.LOWER));
		lower.whenPressed(new ClawMode(Claw.Mode.PID));
	}
	
	public void initializeOverrideButtons()
	{
		override.whileHeld(new LiftManualOverride());
		override.whileHeld(new WristManualOverride());
	}
}


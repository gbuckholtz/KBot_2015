package kbot.pid;

import kbot.pid.commands.CANCommand;
import kbot.pid.commands.EncoderPosition;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public XboxController stick = new XboxController(0);
	public JoystickButton a_butt,b_butt,x_butt;

	public OI()
	{
		a_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_A);
		b_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_B);
		//b_butt.whenPressed(new CANCommand(10000,"B"));
		//a_butt.whenPressed(new CANCommand(5000,"A"));
		
	}
	
	public JoystickButton a_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_A);
	public JoystickButton b_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_B);
	public JoystickButton x_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_X);
	
	public OI()
	{
	//	a_butt.whenPressed(new CANCommand(500));
	//	b_butt.whenPressed(new CANCommand(1000));
	//	x_butt.whenPressed(new CANCommand(750));
	}
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
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


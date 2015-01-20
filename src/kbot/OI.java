package kbot;


/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
<<<<<<< HEAD:src/kbot/OI.java
=======
	public XboxController stick = new XboxController(0);
	public JoystickButton a_butt,b_butt,x_butt;

	public OI()
	{
		a_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_A);
		b_butt = new JoystickButton(stick.m_joy, XboxController.XBOX_B);
		//b_butt.whenPressed(new CANCommand(10000,"B"));
		//a_butt.whenPressed(new CANCommand(5000,"A"));
		
	}
	
>>>>>>> 85c6f4f... Got TalonSRX to work with positional PID under no load. It doesn't quite work with CommandBased buttons, so we had to use iterative code in the command i.e. getA(), getB(), etc... Need to fix this soon. Also, discovered that PID values can only be positive and that P works best at very high numbers without the load (20).:src/kbot/pid/OI.java
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


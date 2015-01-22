package kbot.pid;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author KBotics
 */

// We should change the XboxController to extend a Joystick, as it will make it much more correct in object oriented programming
public class XboxController {
    public Joystick m_joy;
    //Buttons
    public static final int XBOX_A = 1;
    public static final int XBOX_B = 2;
    public static final int XBOX_X = 3;
    public static final int XBOX_Y = 4;
    public static final int XBOX_LB = 5;
    public static final int XBOX_RB = 6;
    public static final int XBOX_SELECT = 7;
    public static final int XBOX_START = 8;
    public static final int XBOX_R3 = 9;
    public static final int XBOX_L3 = 10;
    //Axes
    public static final int XBOX_LEFT_X = 0;
    public static final int XBOX_LEFT_Y = 1;
    public static final int XBOX_LEFT_TRIGGER = 2;
    public static final int XBOX_RIGHT_TRIGGER = 3;
    public static final int XBOX_RIGHT_X = 4;
    public static final int XBOX_RIGHT_Y = 5;
    
    
    
    
    public XboxController(int number) {
        m_joy = new Joystick(number);
    }
    
    public boolean getA() {
        return m_joy.getRawButton(XBOX_A);
    }
    
    public boolean getB() {
        return m_joy.getRawButton(XBOX_B);
    }
    
    public boolean getX()
    {
        return m_joy.getRawButton(XBOX_X);
    }
    
    public boolean getY()
    {
        return m_joy.getRawButton(XBOX_Y);
    }
    
    public boolean getLB()
    {
        return m_joy.getRawButton(XBOX_LB);
    }
    
    public boolean getRB()
    {
        return m_joy.getRawButton(XBOX_RB);
    }
    
    public boolean getStart()
    {
        return m_joy.getRawButton(XBOX_START);
    }
    
    public boolean getSelect()
    {
        return m_joy.getRawButton(XBOX_SELECT);
    }
    
    public double getJoyLeftX()
    {
        return m_joy.getRawAxis(XBOX_LEFT_X);
    }
    
    public double getJoyLeftY()
    {
        return m_joy.getRawAxis(XBOX_LEFT_Y);
    }
    
    public double getJoyRightX()
    {
        return m_joy.getRawAxis(XBOX_RIGHT_X);
    }
    
    public double getJoyRightY()
    {
        return m_joy.getRawAxis(XBOX_RIGHT_Y);
    }
    
    public double getTriggerRightY()
    {
        return m_joy.getRawAxis(XBOX_RIGHT_TRIGGER);
    }
    
    public double getTriggerLeft()
    {
        return m_joy.getRawAxis(XBOX_LEFT_TRIGGER);
    }
}      		
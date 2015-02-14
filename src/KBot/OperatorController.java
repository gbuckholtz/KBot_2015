package KBot;

import edu.wpi.first.wpilibj.Joystick;

/**
 *
 * @author KBotics
 */

// We should change the OPERATORController to extend a Joystick, as it will make it much more correct in object oriented programming
public class OperatorController {
    public Joystick m_joy;
    //Buttons
    public static final int OPERATOR_0 = 1;
    public static final int OPERATOR_1 = 2;
    public static final int OPERATOR_2 = 3;
    public static final int OPERATOR_3 = 4;
    public static final int OPERATOR_4 = 5;
    public static final int OPERATOR_5 = 6;
    public static final int OPERATOR_LOWER = 7;
    public static final int OPERATOR_RAISE = 8;
    public static final int OPERATOR_CLOSE = 9;
    public static final int OPERATOR_OPEN = 10;
    public static final int OPERATOR_OVERRIDE = 11;
    
    //Axes
    public static final int OPERATOR_MANUAL_Y = 0;
    public static final int OPERATOR_MANUAL_X = 1;
    
    //Array
    public static int[] levels = {OPERATOR_0, OPERATOR_1, OPERATOR_2, OPERATOR_3, OPERATOR_4, OPERATOR_5};
    
    //Enum
    private enum Group { LEVELS, CLAW, LIFT, ALL, OVERRIDE};
    
    public OperatorController(int number) 
    {
        m_joy = new Joystick(number);
    }
    
    public boolean get0() 
    {
    	return setLight(OPERATOR_0, Group.LEVELS);
    }
    
    public boolean get1() 
    {
        return setLight(OPERATOR_1, Group.LEVELS);
    }
    
    public boolean get2()
    {
        return setLight(OPERATOR_2, Group.LEVELS);
    }
    
    public boolean get3()
    {
        return setLight(OPERATOR_3, Group.LEVELS);
    }
    
    public boolean get4()
    {
        return setLight(OPERATOR_4, Group.LEVELS);
    }
    
    public boolean get5()
    {
        return setLight(OPERATOR_5, Group.LEVELS);
    }
    
    public boolean getLower()
    {
    	return setLight(OPERATOR_LOWER, Group.LIFT);
    }
    
    public boolean getRaise()
    {
    	return setLight(OPERATOR_RAISE, Group.LIFT);
    }
    
    public boolean getOpen()
    {
    	return setLight(OPERATOR_OPEN, Group.CLAW);
    }
    
    public boolean getClose()
    {
    	return setLight(OPERATOR_CLOSE, Group.CLAW);
    }
    
    public boolean getOverride()
    {
    	if(!m_joy.getRawButton(OPERATOR_OVERRIDE))
    		disableLights(Group.OVERRIDE);
    	return setLight(OPERATOR_OVERRIDE, Group.OVERRIDE); 
    }
    
    public double getManualX()
    {
    	if (getOverride())
    		return m_joy.getRawAxis(OPERATOR_MANUAL_X);
    	return 0;
    }
    
    public double getManualY()
    {
    	if (getOverride())
    		return m_joy.getRawAxis(OPERATOR_MANUAL_Y);
        return 0;
    }
    
    public void disableLights(Group group)
    {
    	switch(group)
    	{
    		case CLAW:
    			m_joy.setOutput(OPERATOR_OPEN, false);
    			m_joy.setOutput(OPERATOR_CLOSE, false);
    			break;
    		case LIFT:
    			m_joy.setOutput(OPERATOR_RAISE, false);
    			m_joy.setOutput(OPERATOR_LOWER, false);
    			break;
    		case LEVELS:
    			for (int i = 0; i<levels.length; i++)
    			{
    				m_joy.setOutput(levels[i],false);
    			}
    			break;
    		case ALL:
    			m_joy.setOutput(OPERATOR_OPEN, false);
    			m_joy.setOutput(OPERATOR_CLOSE, false);
    			m_joy.setOutput(OPERATOR_RAISE, false);
    			m_joy.setOutput(OPERATOR_LOWER, false);
    			for (int i = 0; i<levels.length; i++)
    			{
    				m_joy.setOutput(levels[i],false);
    			}
    			break;
    		case OVERRIDE:
    			m_joy.setOutput(OPERATOR_OVERRIDE, false);
    			break;
    	}
    }
    
    private boolean setLight(int button, Group group)
    {
    	if (m_joy.getRawButton(button))
    	{
    		disableLights(group);
    		m_joy.setOutput(button, true);
    	}
    	return m_joy.getRawButton(button);
    }
    
    public void tron()
    {
    	get0();
    	get1();
    	get2();
    	get3();
    	get4();
    	get5();
    	getLower();
    	getRaise();
    	getOpen();
    	getClose();
    	getOverride();
    }
}      		
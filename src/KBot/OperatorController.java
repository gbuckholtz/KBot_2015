package KBot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.Timer;

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
    public static final int OPERATOR_OPEN = 7;
    public static final int OPERATOR_CLOSE = 8;
    public static final int OPERATOR_RAISE = 9;
    public static final int OPERATOR_LOWER = 10;
    public static final int OPERATOR_OVERRIDE = 11;
    
    //Axes
    public static final int OPERATOR_MANUAL_X = 0;
    public static final int OPERATOR_MANUAL_Y = 1;
    public static final int OPERATOR_POT = 3;
    
    //Array for level lights
    public static int[] levels = {OPERATOR_0, OPERATOR_1, OPERATOR_2, OPERATOR_3, OPERATOR_4, OPERATOR_5};
    
    //Light groupings
    private enum Group { LEVELS, CLAW, LIFT, ALL, OVERRIDE};
    
    //Axis ranges (for scaling)
    private final double minPot = 0.015748, maxPot = 0.102362;
    private final double minX = 0.03937, maxX = 0.070866;
    private final double minY = 0.03937, maxY = 0.07874;
    
    //Variables for pot and manual control
    private double scaledPot, scaledX, scaledY;
    private final double DEADBAND = 0.05;
    
    //Flashes the lights when disabled
    Timer timer;
    private boolean lightsOn = false;
    private boolean timerOn = false;
    
    private int lastLevel=0;
    private boolean isRaised=false;
    
    public OperatorController(int number) 
    {
        m_joy = new Joystick(number);
        scaledPot = 0;
        scaledX = 0;
        scaledY = 0;
        timer = new Timer();
        timer.reset();
        timer.start();
        timerOn = true;
    }
    
    public boolean get0() 
    {
    	if (setLight(OPERATOR_0, Group.LEVELS))
    	{
    		lastLevel=0;
    		return true;
    	}
    	return false;
    }
    
    public boolean get1() 
    {
    	if ( setLight(OPERATOR_1, Group.LEVELS))
    	{
    		lastLevel=1;
    		return true;
    	}
    	return false;
    }
    
    public boolean get2()
    {
    	if ( setLight(OPERATOR_2, Group.LEVELS))
    	{
    		lastLevel=2;
    		return true;
    	}
    	return false;
    }
    
    public boolean get3()
    {
    	if ( setLight(OPERATOR_3, Group.LEVELS))
    	{
    		lastLevel=3;
    		return true;
    	}
    	return false;
    }
    
    public boolean get4()
    {
    	if ( setLight(OPERATOR_4, Group.LEVELS))
    	{
    		lastLevel=4;
    		return true;
    	}
    	return false;
    }
    
    public boolean get5()
    {
    	if ( setLight(OPERATOR_5, Group.LEVELS))
    	{
    		lastLevel=5;
    		return true;
    	}
    	return false;
    }
    
    public boolean getLower()
    {
    	if ( setLight(OPERATOR_LOWER, Group.LIFT))
    	{
    		isRaised=false;
    		return true;
    	}
    	return false;
    }
    
    public boolean getRaise()
    {
    	if ( setLight(OPERATOR_RAISE, Group.LIFT))
    	{
    		isRaised=true;
    		return true;
    	}
    	return false;
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
    	{
    		//scaledX = (m_joy.getRawAxis(OPERATOR_MANUAL_X)-minX)/(maxX-minX);
    		return -m_joy.getRawAxis(OPERATOR_MANUAL_X);
    	}
    	return 0;
    }
    
    public double getManualY()
    {
    	if (getOverride())
    	{
    		//scaledY = (m_joy.getRawAxis(OPERATOR_MANUAL_Y)-minY)/(maxY-minY);
    		return -m_joy.getRawAxis(OPERATOR_MANUAL_Y);
    	}
        return 0;
    }
    
    public double getPotRaw()
    {
    	return m_joy.getRawAxis(OPERATOR_POT);
    }
    
    public double getPotAngle()
    {
    	//Scaled to 0-290 degrees
    		//55 is horizontal right
    		//145 is vertical
    		//235 is horizontal left
    	return (131*(m_joy.getRawAxis(OPERATOR_POT))+145);
    }
    
    public void disableLights()
    {
    	disableLights(Group.ALL);
    }
    
    private void disableLights(Group group)
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
    
    public void enableLights()
    {
    	enableLights(Group.ALL);
    }
    
    private void enableLights(Group group)
    {
    	switch(group)
    	{
    		case CLAW:
    			m_joy.setOutput(OPERATOR_OPEN, true);
    			m_joy.setOutput(OPERATOR_CLOSE, true);
    			break;
    		case LIFT:
    			m_joy.setOutput(OPERATOR_RAISE, true);
    			m_joy.setOutput(OPERATOR_LOWER, true);
    			break;
    		case LEVELS:
    			for (int i = 0; i<levels.length; i++)
    			{
    				m_joy.setOutput(levels[i],true);
    			}
    			break;
    		case ALL:
    			m_joy.setOutput(OPERATOR_OPEN, true);
    			m_joy.setOutput(OPERATOR_CLOSE, true);
    			m_joy.setOutput(OPERATOR_RAISE, true);
    			m_joy.setOutput(OPERATOR_LOWER, true);
    			for (int i = 0; i<levels.length; i++)
    			{
    				m_joy.setOutput(levels[i],true);
    			}
    			break;
    		case OVERRIDE:
    			m_joy.setOutput(OPERATOR_OVERRIDE, true);
    			break;
    	}
    }
    
    public int getLevel()
    {
    	return lastLevel;
    }
    
    public boolean isRaised()
    {
    	return isRaised;
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
    
    public void pacman()
    {
    	if(getOverride())
    	{
    		if(timerOn)
    		{
    			timer.stop();
    			timer.reset();
    			timerOn = false;
    		}
    		disableLights();			
    	}
    	else if(!getOverride())
    	{
    		if(!timerOn)
    		{
    			timerOn = true;
    			timer.start();
    		}
    		blinky();
    	}
    }
    
    private void blinky()
    {
    	if(timer.get() > 0.25)
    	{
    		lightsOn = !lightsOn;
    		timer.reset();
    	} 
    	
    	if(lightsOn)
    		enableLights();
    	else
    		disableLights();
    }
}      		
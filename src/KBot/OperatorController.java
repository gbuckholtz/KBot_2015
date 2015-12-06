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
    public static final int JOY_SOUND = 1;
    
    
    public OperatorController(int number) 
    {
        m_joy = new Joystick(number);
        
    }
    
  
}      		
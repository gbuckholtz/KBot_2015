package KBot.subsystems;

import KBot.RobotMap;
import KBot.commands.GetVisionData;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Vision extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public double blobCount=0;
	public double boxSize=0;
	public double boxX=160;
	public double boxY=120;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GetVisionData());
    }
    public void getVisionData() {
    	if (RobotMap.visionTable.containsKey("BLOB_COUNT"))
    		blobCount = RobotMap.visionTable.getNumber("BLOB_COUNT");
    	if (RobotMap.visionTable.containsKey("COG_BOX_SIZE"))
    		boxSize = RobotMap.visionTable.getNumber("COG_BOX_SIZE");
    	if (RobotMap.visionTable.containsKey("COG_X"))
    		boxX = RobotMap.visionTable.getNumber("COG_X");
    	if (RobotMap.visionTable.containsKey("COG_Y"))
    		boxY = RobotMap.visionTable.getNumber("COG_Y");

    }
}

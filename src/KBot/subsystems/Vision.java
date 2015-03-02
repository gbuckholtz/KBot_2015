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
	public double boxX=0;
	public double boxY=0;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new GetVisionData());
    }
    public void getVisionData() {
    	blobCount = RobotMap.visionTable.getNumber("BLOB_COUNT");
    	boxSize = RobotMap.visionTable.getNumber("COG_BOX_SIZE");
    	boxX = RobotMap.visionTable.getNumber("COG_X");
    	boxY = RobotMap.visionTable.getNumber("COG_Y");

    }
}

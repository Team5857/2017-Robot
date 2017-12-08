package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_StationaryDebug extends CommandGroup {
	
	public Auto_StationaryDebug()
	{
		addSequential(new AUTO_TURN(-15));
	}

}

package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_BeginMid extends CommandGroup {
	
	public Auto_BeginMid()
	{
		addSequential(new AUTO_REV(3.0, 0, 0.20));
    	addSequential(new AUTO_DRIVESTOP(0.2));
	}

}

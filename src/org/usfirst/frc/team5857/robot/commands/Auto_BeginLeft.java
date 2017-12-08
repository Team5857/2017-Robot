package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_BeginLeft extends CommandGroup {
	
	public Auto_BeginLeft()
	{
		addSequential(new AUTO_REV(3, 0, 0.20));
    	addSequential(new AUTO_DRIVESTOP(0.5));
    	addSequential(new AUTO_TURN(60));
    	addSequential(new AUTO_DRIVESTOP(0.5));
    	addSequential(new AUTO_REV(2, 60, 0.20));
	}

}

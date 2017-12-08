package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_BeginRight extends CommandGroup {
	
	public Auto_BeginRight()
	{
		
		/*
		addSequential(new AUTO_REV(3, 0, 0.20));
    	addSequential(new AUTO_DRIVESTOP(0.5));
    	addSequential(new AUTO_TURN(-60));
    	addSequential(new AUTO_DRIVESTOP(0.5));
    	addSequential(new AUTO_REV(3, -60, 0.20));
    	*/
		
		addSequential(new AUTO_REV(5,0,0.20));
		addSequential(new AUTO_DRIVESTOP(0.2));
	}

}

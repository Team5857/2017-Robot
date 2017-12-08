package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class Auto_DriveStraight extends CommandGroup {
	
	public Auto_DriveStraight()
	{
		addSequential(new AUTO_REV(5, 0, 0.20));
		addSequential(new AUTO_DRIVESTOP(0.3));
	}

}

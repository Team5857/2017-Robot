package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberOff extends Command {

	public ClimberOff()
	{
		super("ClimberOff");
		requires(Robot.climber);
	}

	protected void execute()
	{
		Robot.climber.stop();
	}

	protected boolean isFinished() {
		return false;
	}
}

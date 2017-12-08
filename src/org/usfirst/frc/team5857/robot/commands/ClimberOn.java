package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberOn extends Command {
	
	public int port;
	
	public ClimberOn()
	{
		super("ClimberOn");
		requires(Robot.climber);
	}

	protected void execute()
	{
		Robot.climber.climb(Robot.oi.getDriveStick2());
	}

	protected boolean isFinished() {
		return false;
	}
}

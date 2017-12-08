package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AUTO_DRIVESTOP extends Command {
	
	public double ms;
	
	public AUTO_DRIVESTOP(double milliseconds)
	{
		super("AUTO_DRIVESTOP");
		requires(Robot.drivetrain);
		ms = milliseconds;
	}

	protected void execute()
	{
		Robot.drivetrain.driveStop(ms);
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted()
	{
		end();
	}
}

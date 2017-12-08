package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class AUTO_TURN extends Command {
	
	public double cmdAngle;
	
	public AUTO_TURN(int angle)
	{
		super("AUTO_TURN");
		requires(Robot.drivetrain);
		cmdAngle = angle;
	}
	
	protected void initialize() {}
	
	protected void execute()
	{
		Robot.drivetrain.autoTurn(cmdAngle);
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

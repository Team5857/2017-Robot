package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class DecrementFactor extends Command {

	public DecrementFactor()
	{
		super("DecrementFactor");
		requires(Robot.drivetrain);
	}
	
	protected void execute()
	{
		Robot.drivetrain.decrementFactor();
	}
	
	protected boolean isFinished() {
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
}

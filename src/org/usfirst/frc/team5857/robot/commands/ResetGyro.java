package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team5857.robot.Robot;

public class ResetGyro extends Command {

	public ResetGyro()
	{
		requires(Robot.gyro);
	}
	
	public void execute()
	{
		Robot.gyro.reset();
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
}

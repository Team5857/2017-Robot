package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

public class AUTO_FWD extends Command {
	
	public double seconds, assignedAngle, assignedFactor;
	
	public AUTO_FWD(double sec, double angle, double the_factor)
	{
		super("AUTO_FWD");
		requires(Robot.drivetrain);
		seconds = sec;
		assignedAngle = angle;
		assignedFactor = the_factor;
	}

	protected void initialize() {}
	
	protected void execute()
	{
		Robot.gyro.reset();
		Timer.delay(0.005);
		Robot.drivetrain.autoDriveFWD(seconds, assignedAngle, assignedFactor);
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

package org.usfirst.frc.team5857.robot.commands;

import org.usfirst.frc.team5857.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ReverseControls extends Command {

	public ReverseControls() {
		super("ReverseControls");
		requires(Robot.drivetrain);		
	}

	protected void initialize() {}
	
	public void execute() {
		Robot.drivetrain.toggleControlsReversed();
	}
	
	public boolean isFinished() {
		return true;
	}
	
	public void end() {}
	
	public void interrupted() {
		end();
	}
}

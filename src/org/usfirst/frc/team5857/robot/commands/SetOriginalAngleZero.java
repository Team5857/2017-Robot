package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5857.robot.Robot;

public class SetOriginalAngleZero extends Command {

	public SetOriginalAngleZero()
	{
		super("SetOriginalAngleZero");
		requires(Robot.lift);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.drivetrain.setAngleZero();
	}
	
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return true;
	}
	
	protected void end() {}
	
	protected void interrupted() {
		end();
	}
}

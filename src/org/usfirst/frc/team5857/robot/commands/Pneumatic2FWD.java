package org.usfirst.frc.team5857.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team5857.robot.Robot;

public class Pneumatic2FWD extends Command {

	public Pneumatic2FWD()
	{
		super("Pneumatic2FWD");
		requires(Robot.lift);
	}
	
	protected void initialize() {}
	
	protected void execute() {
		Robot.lift.Pneumatic2FWD();
	}

	protected boolean isFinished() {
		return true;
	}

	protected void end() {}
	
	protected void interrupted() {
		end();
	}

}

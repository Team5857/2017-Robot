package org.usfirst.frc.team5857.robot.subsystems;

import org.usfirst.frc.team5857.robot.commands.ClimberOn;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Climber extends Subsystem {
	private SpeedController climber1, climber2;

	public Climber() {
		climber1 = new Victor(8);
		climber2 = new Victor(9);
	}

	
	public void climb(int port, Joystick joy) { 
		if(port == 1)
			climber1.set(-1*(Math.abs(joy.getRawAxis(3) - 1) / 2.0));
		else if(port == 2)
			climber2.set((Math.abs(joy.getRawAxis(3) - 1) / 2.0));
	}

	public void stop(int port) {
		if(port == 1)
			climber1.set(0);
		else if(port == 2)
			climber2.set(0);
	}
	
	public void climb(Joystick joy) {
		if (joy.getRawAxis(3) <= 0)
		{
			climber1.set(0);
			climber2.set(0);
		}
		else {
			climber1.set(-1 * joy.getRawAxis(3));
			climber2.set(joy.getRawAxis(3));
		}
	}
	
	public void stop() {
		climber1.set(0);
		climber2.set(0);
	}
	
	public void customSet(int motor, double factor)
	{
		if(motor == 1)
		{
			climber1.set(factor);
		}
		
		else if(motor == 2)
		{
			climber2.set(factor);
		}
	}

	
	/*
	public void toggleClimb() {
		if (climberOn) 
			stop();
		else
			climb();
	}

	 */

	public void initDefaultCommand() {
		setDefaultCommand(new ClimberOn());
	}
	
	public double getSpeed(int num)
	{
		if(num == 6)
		{
			return climber1.get();
		}
		else if(num == 8)
		{
			return climber2.get();
		}
		else return 1337;
	}
}

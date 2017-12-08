
package org.usfirst.frc.team5857.robot;

import edu.wpi.first.wpilibj.Joystick;
import org.usfirst.frc.team5857.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	
	public Joystick driveStick;
	public Joystick secondaryStick;
	
	public OI() {
		driveStick = new Joystick(0);				//Logitech Dual Action
		secondaryStick = new Joystick(1);
		
		/** Logitech Dual Action**/
		new JoystickButton(driveStick, 3).whenPressed(new ReverseControls());			//button X
		new JoystickButton(driveStick, 5).whenPressed(new DecrementFactor());			//L bump
		new JoystickButton(driveStick, 6).whenPressed(new IncrementFactor());			//R bump
		 
		/**Logitech Extreme 3D Pro**/
		new JoystickButton(secondaryStick, 2).whenPressed(new ResetGyro());
		
		new JoystickButton(secondaryStick, 8).whenPressed(new ToggleCompressor());
		
		new JoystickButton(secondaryStick, 5).whenPressed(new Pneumatic1TOG());
		new JoystickButton(secondaryStick, 6).whenPressed(new Pneumatic2TOG());
		
		new JoystickButton(secondaryStick, 1).whenPressed(new ClimberOff());
		
		
		/**Saitek ST290**/
		/*
		new JoystickButton(secondaryStick, 5).whenPressed(new ToggleCompressor());
		new JoystickButton(secondaryStick, 3).whenPressed(new Pneumatic1TOG());
		new JoystickButton(secondaryStick, 2).whenPressed(new Pneumatic2TOG());
		*/
		//new JoystickButton(driveStick, 4).whenPressed(new Pneumatic3TOG());	
		
	}
	
	public Joystick getDriveStick1() {
		return driveStick;
	}
	
	public Joystick getDriveStick2() {
		return secondaryStick;
	}

}


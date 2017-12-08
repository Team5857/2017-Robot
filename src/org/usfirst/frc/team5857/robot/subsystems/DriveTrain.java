package org.usfirst.frc.team5857.robot.subsystems;

import org.usfirst.frc.team5857.robot.Robot;
import org.usfirst.frc.team5857.robot.commands.DriveWithJoystick;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveTrain extends Subsystem {
	public static SpeedController left;
	public static SpeedController right;
	private static double factor =  0.3; //teleop factor
	private static boolean controlsReversed = false;
	public static double autoFactor = 0.25;
	public static double compFactor = 0.01;
	public static double autoReverseFactor = 0.25;
	public static double originalAngle;
	public static double T0to1_turnFactor = 0.22;
	public static double T1to2_turnFactor = 0.16;
	public static double T2to3_turnFactor = 0.09;
	public static final double TOLERANCE = 2;
	public static final double RIGHTCOMP = 0.97;
	public static final double T1_TURN_TOLERANCE = 25;
	public static final double T2_TURN_TOLERANCE = 12.5;	
	public static final double T3_TURN_TOLERANCE = 2;
	public static boolean breakLoop = false;
	public static double timeToTurn = 1.5;
	public static double turnStartTime;

	public DriveTrain() {
		left = new Victor(0);					//initialize left motors on port 0
		right = new Victor(1);					//initialize right motors on port 1
	}

	public void incrementFactor()
	{
		if (factor < 0)
			factor = 0;	
		else if (factor > 1)
			factor = 1;
		if (factor < 1 && factor >= 0){
			factor = factor + 0.05;
			Timer.delay(0.1);
		}
	}


	public void decrementFactor()
	{
		if (factor < 0)
			factor = 0;	
		else if (factor > 1)
			factor = 1;
		if (factor < 1 && factor >= 0){
			factor = factor - 0.05;
			Timer.delay(0.1);
		}
	}

	public void tankDrive(Joystick driveStick) {
		if (!controlsReversed) {
			left.set(-driveStick.getRawAxis(1) * factor);					//left y-axis
			right.set(-driveStick.getRawAxis(5)* factor);					//Right y-axis
		}
		else 
		{
			left.set(driveStick.getRawAxis(5) * factor);
			right.set(driveStick.getRawAxis(1) * factor);
		}
	}

	public void tankDrive(Joystick left, Joystick right)
	{
		factor = (right.getRawAxis(3) + 1) / 2.0;
		if(!controlsReversed)
		{
			DriveTrain.left.set(-left.getRawAxis(1)*factor);
			DriveTrain.right.set(-RIGHTCOMP*right.getRawAxis(1)*factor);
		}
		else if(controlsReversed)
		{
			DriveTrain.left.set(right.getRawAxis(1)*factor);
			DriveTrain.right.set(RIGHTCOMP*left.getRawAxis(1)*factor);
		}
	}


	public double getFactor() {
		return factor;
	}

	public double getRightSpeed() {
		return right.get();
	}

	public double getLeftSpeed() {
		return left.get();
	}

	public boolean getControlsReversed() {
		return controlsReversed;
	}

	public void toggleControlsReversed() {
		if (controlsReversed)
			controlsReversed = false;
		else
			controlsReversed = true;
	}

	public void initDefaultCommand() {
		setDefaultCommand(new DriveWithJoystick());
	}

	//AUTONOMOUS METHODS
	public void autoDriveFWD(double seconds, double angle, double the_factor)
	{
		int iterations = (int)(seconds * 50);
		originalAngle = angle;
		autoFactor = the_factor;
		while(iterations > 0)
		{
			if(Robot.gyro.getAngle() < originalAngle && Math.abs(originalAngle - Robot.gyro.getAngle()) < TOLERANCE)
			{
				//DriveTrain.left.set(autoFactor  + compFactor * Math.abs(originalAngle - Robot.gyro.getAngle()));
				DriveTrain.right.set(RIGHTCOMP*(autoFactor - compFactor * Math.abs(originalAngle - Robot.gyro.getAngle())));
			}
			else if(Robot.gyro.getAngle() > originalAngle && Math.abs(originalAngle - Robot.gyro.getAngle()) < TOLERANCE)
			{
				//DriveTrain.right.set(autoFactor + compFactor * Math.abs(originalAngle - Robot.gyro.getAngle()));
				DriveTrain.left.set(autoFactor - compFactor * Math.abs(originalAngle - Robot.gyro.getAngle()));
			}

			else
			{
				DriveTrain.left.set(autoFactor);
				DriveTrain.right.set(autoFactor*RIGHTCOMP);

			}

			Timer.delay(0.020); //20ms delay
			iterations--;
		}
	}

	public void autoTurn(double angle)
	{
		int iterations = 1*50;

		if(angle < Robot.gyro.getAngle())
		{
			while(iterations > 0)
			{
				if(Math.abs(Robot.gyro.getAngle() - angle) > T1_TURN_TOLERANCE)
				{
					DriveTrain.left.set(-T0to1_turnFactor); //negative 
					DriveTrain.right.set(RIGHTCOMP*T0to1_turnFactor); //positive
					if(DriverStation.getInstance().isOperatorControl()) break;

				}

				else if(Math.abs(Robot.gyro.getAngle() - angle) > T2_TURN_TOLERANCE)
				{
					DriveTrain.left.set(-T1to2_turnFactor);
					DriveTrain.right.set(RIGHTCOMP*T1to2_turnFactor);
					if(DriverStation.getInstance().isOperatorControl()) break;

				}

				else if(Math.abs(Robot.gyro.getAngle() - angle) > T3_TURN_TOLERANCE)
				{
					DriveTrain.left.set(-T2to3_turnFactor);
					DriveTrain.right.set(RIGHTCOMP*T2to3_turnFactor);
					if(DriverStation.getInstance().isOperatorControl()) break;

				}
				if(Math.abs(Robot.gyro.getAngle()) - angle <= T3_TURN_TOLERANCE) break;

				
				Timer.delay(0.020);
				iterations--;
			}
		}

		else if(angle > Robot.gyro.getAngle())
		{
			while(iterations > 0)
			{
				while(Math.abs(Robot.gyro.getAngle() - angle) > T1_TURN_TOLERANCE)
				{
					DriveTrain.left.set(T0to1_turnFactor); //negative 
					DriveTrain.right.set(-RIGHTCOMP*T0to1_turnFactor); //positive
					if(DriverStation.getInstance().isOperatorControl()) break;

				}

				while(Math.abs(Robot.gyro.getAngle() - angle) > T2_TURN_TOLERANCE)
				{
					DriveTrain.left.set(T1to2_turnFactor);
					DriveTrain.right.set(-RIGHTCOMP*T1to2_turnFactor);
					if(DriverStation.getInstance().isOperatorControl()) break;

				}

				while(Math.abs(Robot.gyro.getAngle() - angle) > T3_TURN_TOLERANCE)
				{
					DriveTrain.left.set(T2to3_turnFactor);
					DriveTrain.right.set(-RIGHTCOMP*T2to3_turnFactor);
					if(DriverStation.getInstance().isOperatorControl()) break;

				}
				
				if(Math.abs(Robot.gyro.getAngle()) - angle <= T3_TURN_TOLERANCE) break;
				
				Timer.delay(0.020);
				iterations--;

			}
		}
	}

	public void autoDriveREV(double seconds, double angle, double the_factor)
	{
		originalAngle = angle;
		int iterations = (int)(seconds * 50);
		autoFactor = the_factor;
		while(iterations > 0)
		{
			if(Robot.gyro.getAngle() < originalAngle && Math.abs(originalAngle - Robot.gyro.getAngle()) < TOLERANCE)
			{
				DriveTrain.right.set(-RIGHTCOMP*(autoReverseFactor - compFactor * Math.abs(originalAngle - Robot.gyro.getAngle())));
			}
			else if(Robot.gyro.getAngle() > originalAngle && Math.abs(originalAngle - Robot.gyro.getAngle()) < TOLERANCE)
			{
				DriveTrain.left.set(-(autoReverseFactor - compFactor * Math.abs(originalAngle - Robot.gyro.getAngle())));
			}

			DriveTrain.left.set(-autoReverseFactor);
			DriveTrain.right.set(-autoReverseFactor*RIGHTCOMP);
			if(DriverStation.getInstance().isOperatorControl()) break;
			
			Timer.delay(0.020);
			iterations--;
		}
	}



	public void driveStop(double milliseconds)
	{
		DriveTrain.left.set(0);
		DriveTrain.right.set(0);

		Timer.delay(milliseconds);
	}

	public void driveStop()
	{
		DriveTrain.left.set(0);
		DriveTrain.right.set(0);
	}

	public void setAngleZero()
	{
		originalAngle = 0;
	}
}

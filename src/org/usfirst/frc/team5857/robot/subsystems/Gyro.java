package org.usfirst.frc.team5857.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Gyro extends Subsystem {
	public ADXRS450_Gyro gyro;
	
	public Gyro() {
		gyro = new ADXRS450_Gyro();
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public void initDefaultCommand() {}
	
	public void reset()
	{
		gyro.reset();
	}
}

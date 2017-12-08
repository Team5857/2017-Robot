//==============================================
// Robot AMOS - Experimental Code
// Last Modified 03/18/2017
// Author: WVR Programming Dept. 
// Notes:
//	-Autonomous: Based on FOR loop timing system
//	-Outdated subsystems (Pulley, Intake, etc.)
//	-Status: Incomplete	-Build? True
//==============================================

package org.usfirst.frc.team5857.robot;

import org.usfirst.frc.team5857.robot.commands.*;
import org.usfirst.frc.team5857.robot.subsystems.*;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static DriveTrain drivetrain;
	public PowerDistributionPanel pdp;
	public static Gyro gyro;
	public static Lift lift;
	public static Timer timer;
	public static Climber climber;

	public double currentAngle = 0;

	public static OI oi;

	public static double directionalFactor;
	public static double timeToDrive;
	public static double currentTimer = 0;

	Command autonomousCommand, Auto_BeginLeft, Auto_BeginMid, Auto_BeginRight;
	SendableChooser chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		
		System.out.println("INFO: AMOS CMD EXPERIMENTAL. FOR loop AUTO, custom DriveTrain. Last modified 03/17/2017");
		System.out.println("WARNING: This code is experimental and uses for loop termination for auto. Proceed with caution.");
		drivetrain = new DriveTrain();
		pdp = new PowerDistributionPanel(0);
		gyro = new Gyro();	
		lift = new Lift();
		climber = new Climber();	

		oi = new OI();

		pdp.clearStickyFaults();

		chooser = new SendableChooser();
		SmartDashboard.putData("Auto mode", chooser);

		Robot.gyro.reset();
		currentAngle = gyro.getAngle();
		directionalFactor = 0.03;
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	public void disabledInit(){
		

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		currentAngle = gyro.getAngle();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
	public void autonomousInit() {
		Robot.climber.stop();
		gyro.reset();
		currentAngle = gyro.getAngle();
		
		Robot.lift.Pneumatic2FWD();
		
		Auto_BeginLeft = new Auto_BeginLeft();
		Auto_BeginRight = new Auto_BeginRight();
		Auto_BeginMid = new Auto_BeginMid();
		
		//-----CHOOSE ONE-----
		//=-=-=-=-=-=-=-=-=-=-=--=-=
		//chooser.addDefault("left", new Auto_BeginLeft());
		chooser.addDefault("right", new Auto_BeginRight()); //basically run to baseline
		//chooser.addDefault("mid", new Auto_BeginMid());
		//chooser.addDefault("straight", new Auto_DriveStraight());
		
		String position = "right";
		if(position.equalsIgnoreCase("right")) Auto_BeginRight.start();
		else if(position.equalsIgnoreCase("mid")) Auto_BeginMid.start();
		else if(position.equalsIgnoreCase("left")) Auto_BeginLeft.start();
		else Auto_BeginMid.start();
		
		Timer.delay(0.005);
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		currentAngle = Robot.gyro.getAngle();
		log();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to 
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) autonomousCommand.cancel();

		Scheduler.getInstance().removeAll();
	}

	/**
	 * This function is called periodically during o	perato'r control
	 */
	public void teleopPeriodic() {

		Scheduler.getInstance().run();
		currentAngle = gyro.getAngle();
		System.out.println("Gyro current angle: "+ currentAngle);
		log();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
		currentAngle = gyro.getAngle();
	}

	public void log() {
		if (!drivetrain.getControlsReversed()) {
			SmartDashboard.putString("DB/String 0", "Speed (L): " + String.format( "%.2f", (drivetrain.getLeftSpeed() * 100)) + "%");
			SmartDashboard.putString("DB/String 5", "Speed (R): " + String.format( "%.2f", (drivetrain.getRightSpeed() * 100)) + "%");
		}
		else {
			SmartDashboard.putString("DB/String 0", "Speed (L): " + String.format( "%.2f", (-drivetrain.getRightSpeed() * 100)) + "%");
			SmartDashboard.putString("DB/String 5", "Speed (R): " + String.format( "%.2f", (-drivetrain.getLeftSpeed() * 100)) + "%");
		}

		SmartDashboard.putString("DB/String 1", "Factor: " );
		SmartDashboard.putString("DB/String 6", String.format( "%.2f", drivetrain.getFactor()));
		
		SmartDashboard.putString("DB/String 0", "PORT6FAC: " + String.format( "%.2f", (climber.getSpeed(6) * 100)) + "%");
		SmartDashboard.putString("DB/String 5", "PORT8FAC: " + String.format( "%.2f", (climber.getSpeed(8) * 100)) + "%");

		SmartDashboard.putString("DB/String 2", "Gyro Angle: " + String.format( "%.1f", gyro.getAngle()));
		SmartDashboard.putString("DB/String 3", "Control Reversed: " + drivetrain.getControlsReversed());
		
		Timer.delay(0.05);
	}
}

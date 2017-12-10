# 2017 Robot
This is the final version of code we used at competition for FRC's 2017 STEAMworks competition. It uses the [command-based system](https://wpilib.screenstepslive.com/s/currentCS/m/java/c/88893) provided by WPILib. 

The .java files are in src/. 

## Systems
For our [drivebase](src/org/usfirst/frc/team5857/robot/subsystems/DriveTrain.java), we used basic tank drive, with two joysticks controlling the speeds of each side. For autonomous code we used information from the gyroscope to move in a straight line. 

The [climber](src/org/usfirst/frc/team5857/robot/subsystems/Climber.java) takes in an input between -1 and 1 and converts that to a speed between 0 and 1 (since we only wanted the climber motors to run in one direction). 

The [Lift subsystem](src/org/usfirst/frc/team5857/robot/subsystems/Lift.java) contains our pneumatic system, including the compressor and two double solenoids. 

In [Robot.java](src/org/usfirst/frc/team5857/robot/Robot.java), we used the power distribution panel to clear sticky faults at initialization. We also coded the SendableChooser but ended up not using it (we just used one default option instead). The log() method prints the status of certain variables to the SmartDashboard in Driver Station. 

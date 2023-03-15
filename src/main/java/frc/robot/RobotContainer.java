// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.AutoTurn;
import frc.robot.commands.driveCommand;
import frc.robot.commands.simpleAuto;
import frc.robot.subsystems.drivesubsystem;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final drivesubsystem mdrivesub = new drivesubsystem();
  private final XboxController mxbox = new XboxController(Constants.XBOXCONTROLLER);
  SendableChooser<Command> m_chooser = new SendableChooser<>(); 
  private final Command one = new simpleAuto(mdrivesub).withTimeout(2);
  private final Command two = new SequentialCommandGroup(
    new simpleAuto(mdrivesub).withTimeout(2),
    new AutoTurn (mdrivesub, 90).withTimeout(1.5),
    new simpleAuto(mdrivesub).withTimeout(2),
    new AutoTurn (mdrivesub, 180).withTimeout(1.5),
    new simpleAuto(mdrivesub).withTimeout(2),
    new AutoTurn (mdrivesub, 270).withTimeout(1.5),
    new simpleAuto(mdrivesub).withTimeout(2),
    new AutoTurn (mdrivesub, 359).withTimeout(1.5)
  );
  
  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    mdrivesub.setDefaultCommand(new driveCommand(mdrivesub, mxbox));
    m_chooser.setDefaultOption("Simple Auto", one);
    m_chooser.addOption("Complex Auto", two);

    // Put the chooser on the dashboard
    SmartDashboard.putData(m_chooser);

  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {}


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    mdrivesub.setOnce(true);
    return m_chooser.getSelected();
  }
}

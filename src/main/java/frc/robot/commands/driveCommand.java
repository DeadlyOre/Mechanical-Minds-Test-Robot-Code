// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.math.filter.SlewRateLimiter;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivesubsystem;

public class driveCommand extends CommandBase {
  /** Creates a new driveCommand. */
  private final drivesubsystem mdrive;
  private final XboxController mxbox;
  private SlewRateLimiter filter1 = new SlewRateLimiter(0.98);
  private SlewRateLimiter filter2 = new SlewRateLimiter(0.98);

 
  public driveCommand(drivesubsystem drive, XboxController Xbox) {
    // Use addRequirements() here to declare subsystem dependencies.
    mdrive = drive;
    mxbox = Xbox;
    addRequirements(mdrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    mdrive.drive(-filter1.calculate(mxbox.getLeftY()),filter2.calculate(-mxbox.getRightX()));
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivesubsystem;
import edu.wpi.first.math.controller.PIDController;

public class AutoTurn extends CommandBase {
  /** Creates a new AutoTurn. */
  private drivesubsystem mdriver;
  private double mangle;
  private final PIDController pid = new PIDController(0.01,0,0);
  public AutoTurn(drivesubsystem Driver, double angle) {
    mdriver = Driver;
    mangle = angle;
    addRequirements(mdriver);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (mdriver.once) {
      mdriver.mgyro.reset();
      mdriver.once = false;
    }
    double s = pid.calculate(mdriver.mgyro.getAngle(), mangle);
    if (s>1){
      s=1;
    } else if (s<-1){
      s=-1;
    }
    mdriver.differentdrive.tankDrive(s,-s);
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

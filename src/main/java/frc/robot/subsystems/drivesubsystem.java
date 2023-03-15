// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;


public class drivesubsystem extends SubsystemBase {
  /** Creates a new drivesubsystem. */

  private final MotorController frontLeft;
  private final MotorController backLeft;
  private final MotorController backRight;
  private final MotorController frontRight;
  public final DifferentialDrive differentdrive;
  public final ADXRS450_Gyro mgyro; 
  public boolean once;


  public drivesubsystem() {
    mgyro = new ADXRS450_Gyro();
    frontLeft = new WPI_VictorSPX(Constants.Motors.FRONT_LEFT);
    frontRight = new WPI_VictorSPX(Constants.Motors.FRONT_RIGHT);
    backLeft = new WPI_VictorSPX(Constants.Motors.BACK_LEFT);
    backRight = new WPI_VictorSPX(Constants.Motors.BACK_RIGHT);
    frontRight.setInverted(true);
    backRight.setInverted(true);
    MotorControllerGroup left = new MotorControllerGroup(frontLeft, backLeft);
    MotorControllerGroup right = new MotorControllerGroup(frontRight, backRight);
    differentdrive = new DifferentialDrive(left, right);
    mgyro.reset();
    once = true;
    SmartDashboard.putNumber("Gyro Angle", mgyro.getAngle());
  }

  public void drive (double forward, double clockwise){
    differentdrive.arcadeDrive(forward, clockwise);
  }

  public void setOnce(boolean value) {
    once = value;
  }

  public void turn(double angle) {
    
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package pink.utils;

import java.util.function.DoubleSupplier;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.sensors.CANCoder;

import pink.utils.Modules.SwervePod;
import pink.utils.Types.SwervePosition;

interface CanCoder {
  public static CANCoder encoder = null;
  public static SwervePosition position = null;
}

/** Add your docs here. */
public class Swerve {
  public SwervePod[] pods;

  public DoubleSupplier x;
  public DoubleSupplier y;
  public DoubleSupplier z;

  public int Length;
  public int Width;

  private boolean isArmed = false;

  public Swerve(SwervePod[] _pods, DoubleSupplier _x, DoubleSupplier _y, DoubleSupplier _z, int _length, int _width) {
    this.pods = _pods;

    this.x = _x;
    this.y = _y;
    this.z = _z;

    this.Length = _length;
    this.Width = _width;
  }

  public void Drive() {
    // the -x is to invert the x axis
    double FWD = -y.getAsDouble();
    double STR = -x.getAsDouble();
    double RCW = z.getAsDouble();

    double temp = FWD * Math.cos(0) + STR * Math.sin(0);
    STR = -FWD * Math.sin(0) + STR * Math.cos(0);
    FWD = temp;

    double L = this.Length;
    double W = this.Width;
    double R = Math.sqrt(Math.pow(L, 2) + Math.pow(W, 2));

    double A = STR - RCW * (L / R);
    double B = STR + RCW * (L / R);
    double C = FWD - RCW * (W / R);
    double D = FWD + RCW * (W / R);

    double ws1 = Math.sqrt(Math.pow(B, 2) + Math.pow(C, 2));
    double wa1 = Math.atan2(B, C) * 180 / Math.PI;

    double ws2 = Math.sqrt(Math.pow(B, 2) + Math.pow(D, 2));
    double wa2 = Math.atan2(B, D) * 180 / Math.PI;

    double ws3 = Math.sqrt(Math.pow(A, 2) + Math.pow(D, 2));
    double wa3 = Math.atan2(A, D) * 180 / Math.PI;

    double ws4 = Math.sqrt(Math.pow(A, 2) + Math.pow(C, 2));
    double wa4 = Math.atan2(A, C) * 180 / Math.PI;

    double max = ws1;

    // normalize the speed
    if (ws2 > max) {
      max = ws2;
    }

    if (ws3 > max) {
      max = ws3;
    }

    if (ws4 > max) {
      max = ws4;
    }

    // this is the sensitivity BE CAREFULL WHEN CHANGING THIS
    if (max > 1) {
      ws1 /= max;
      ws2 /= max;
      ws3 /= max;
      ws4 /= max;
    }

    for (SwervePod _pod : pods) {
      switch (_pod.getPosition()) {
        case FRONT_LEFT:
          _pod.GetDriveMotor().set(ControlMode.PercentOutput, ws2);
          _pod.getAngleMotor().setPosition(wa2);
          break;

        case FRONT_RIGHT:
          _pod.GetDriveMotor().set(ControlMode.PercentOutput, ws1);
          _pod.getAngleMotor().setPosition(wa1);
          break;

        case BACK_LEFT:
          _pod.GetDriveMotor().set(ControlMode.PercentOutput, ws3);
          _pod.getAngleMotor().setPosition(wa3);
          break;

        case BACK_RIGHT:
          _pod.GetDriveMotor().set(ControlMode.PercentOutput, ws4);
          _pod.getAngleMotor().setPosition(wa4);
          break;
      }
    }
  }

  public boolean isArmed() {
    return this.isArmed;
  }

  public boolean Arm(boolean _arm) {
    this.isArmed = _arm;

    return this.isArmed;
  }

  /**
   * This Will Zero Motors In Whatever Positon There Currenty In
   */
  public void ZeroAll() {
    for (SwervePod _pod : pods) {
      _pod.Zero();
    }
  }
}
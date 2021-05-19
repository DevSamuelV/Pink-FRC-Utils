package pink.utils.Modules;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.CANCoder;

import pink.utils.Types.SwervePosition;

public class SwervePod {
  private SwervePosition positon;
  private PinkTalon angleMotor;
  private TalonFX driveMotor;
  private CANCoder canCoder;

  public SwervePod(SwervePosition _position, PinkTalon _angleMotor, TalonFX _driveMotor, CANCoder _canCoder) {
    this.positon = _position;
    this.canCoder = _canCoder;
    this.angleMotor = _angleMotor;
    this.driveMotor = _driveMotor;
  }

  public void Zero() {
    this.angleMotor.Zero();
    this.canCoder.setPosition(0);
  }

  public TalonFX GetDriveMotor() {
    return this.driveMotor;
  }

  public PinkTalon getAngleMotor() {
    return this.angleMotor;
  }

  public SwervePosition getPosition() {
    return this.positon;
  }
}

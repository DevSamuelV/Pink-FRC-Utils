package pink.utils.Types;

import java.util.function.DoubleSupplier;

public class CalulatedValues {
  public DoubleSupplier ws1;
  public DoubleSupplier ws2;
  public DoubleSupplier ws3;
  public DoubleSupplier ws4;

  public DoubleSupplier wa1;
  public DoubleSupplier wa2;
  public DoubleSupplier wa3;
  public DoubleSupplier wa4;

  public CalulatedValues(DoubleSupplier _ws1, DoubleSupplier _ws2, DoubleSupplier _ws3, DoubleSupplier _ws4,
      DoubleSupplier _wa1, DoubleSupplier _wa2, DoubleSupplier _wa3, DoubleSupplier _wa4) {
    this.ws1 = _ws1;
    this.ws2 = _ws2;
    this.ws3 = _ws3;
    this.ws4 = _ws4;

    this.wa1 = _wa1;
    this.wa2 = _wa2;
    this.wa3 = _wa3;
    this.wa4 = _wa4;
  }

}

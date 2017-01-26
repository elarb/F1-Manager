package edu.tudelft.games.f1manager.core;

public class Engine {

  /**
   * The name of the brand of the engine.
   */
  private String brand;

  /**
   * The power of the engine represented as a double.
   */
  private double power;

  /**
   * The drivability of the engine represented as a double.
   */
  private double drivability;

  /**
   * The fuel-efficiency of the engine represented as a double.
   */
  private double fuelEfficiency;

  private double price;

  /**
   * Creates an object that represents an Engine for a F1 Car.
   *
   * @param brand          brand of the engine
   * @param power          power of the engine
   * @param drivability    drivability of the engine
   * @param fuelEfficiency fuel-efficiency of the engine
   */
  public Engine(String brand, double power, double drivability, double fuelEfficiency) {
    this.brand = brand;
    this.power = power;
    this.drivability = drivability;
    this.fuelEfficiency = fuelEfficiency;
  }

  public double getPrice() {
    return Constants.VALUE_ENGINE * ((Constants.POWER_COEF * power)
      * (Constants.DRIVABILITY_COEF * drivability)
      * (Constants.FUEL_EFFICIENCYCOEF * fuelEfficiency));
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String newbrand) {
    brand = newbrand;
  }

  public double getPower() {
    return power;
  }

  public void setPower(double newpower) {
    power = newpower;
  }

  public double getDrivability() {
    return drivability;
  }

  public void setDrivability(double newdrivability) {
    drivability = newdrivability;
  }

  public double getFuelEfficiency() {
    return fuelEfficiency;
  }

  public void setFuelEfficiency(double fuelEfficiency) {
    this.fuelEfficiency = fuelEfficiency;
  }

  public void determineprice() {

    this.price = Constants.VALUE_ENGINE * ((Constants.POWER_COEF * power)
      * (Constants.DRIVABILITY_COEF * drivability)
      * (Constants.FUEL_EFFICIENCYCOEF * fuelEfficiency));

  }

}





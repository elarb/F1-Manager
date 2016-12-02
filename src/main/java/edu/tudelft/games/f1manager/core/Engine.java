package edu.tudelft.games.f1manager.core;

public class Engine {

  private String brand;
  private double power;
  private double drivability;
  private double fuelEfficiency;
  private double price;


  public Engine(String brand, double power, double drivability, double fuelEfficiency) {
    this.brand = brand;
    this.power = power;
    this.drivability = drivability;
    this.fuelEfficiency = fuelEfficiency;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String newbrand) {
    brand = newbrand;
  }

  public double getPrice() {
    return price;
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

  public void setPrice() {
    double calculus = Constants.VALUE_ENGINE * ((Constants.POWER_COEF * power) * (Constants.DRIVABILITY_COEF * drivability) * (Constants.FUEL_EFFICIENCYCOEF * fuelEfficiency));
    price = calculus;
  }


}



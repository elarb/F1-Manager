package edu.tudelft.games.f1manager.core;

/**
 * This class represents an engine of a F1 car.
 */
public class Engine {

  private String brand;
  private double power;
  private double driveAbility;
  private double fuelEfficiency;
  private int price;

  /**
   * Create an Object that represents an Engine.
   *
   * @param brand          The brand of the Engine
   * @param power          Power of the Engine
   * @param driveAbility   The Drive-ability of the Engine
   * @param fuelEfficiency The Fuel-efficienty of the Engine
   */
  public Engine(String brand, double power, double driveAbility, double fuelEfficiency, int price) {
    this.brand = brand;
    this.power = power;
    this.driveAbility = driveAbility;
    this.fuelEfficiency = fuelEfficiency;
    this.price = price;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public double getPower() {
    return power;
  }

  public void setPower(double power) {
    this.power = power;
  }

  public double getDriveAbility() {
    return driveAbility;
  }

  public void setDriveAbility(double driveAbility) {
    this.driveAbility = driveAbility;
  }

  public double getFuelEfficiency() {
    return fuelEfficiency;
  }

  public void setFuelEfficiency(double fuelEfficiency) {
    this.fuelEfficiency = fuelEfficiency;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }
}



package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.Driver;


public class DriverResult {

  /**
   * The driver associated to the result.
   */
  private Driver driver;

  /**
   * The result of the driver in seconds.
   */
  private double time;

  /**
   * Creates an object that represents the result of a driver in a race.
   *
   * @param driver the driver associated to the result
   * @param time   the result of the driver in seconds
   */
  public DriverResult(Driver driver, double time) {

    this.driver = driver;
    this.time = time;
  }

  public Driver getDriver() {
    return driver;
  }

  public void setDriver(Driver driver) {
    this.driver = driver;
  }

  public double getTime() {
    return time;
  }

  public void setTime(double time) {
    this.time = time;
  }

}
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

  @Override
  public String toString() {
    return "DriverResult{" + "driver=" + driver
      + ", time=" + time + '}';
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

  /**gives the time of the driver represented as a string.
   * @return a string representing the driver result time.
   */
  public String getTimeString() {
    double totalseconds = this.getTime();
    if (totalseconds == 100000000) {
      return "CRASHED";
    }
    int hours = (int) Math.round(totalseconds / 3600);
    int minutes = (int) Math.round(totalseconds % 3600) / 60;
    int seconds = (int) Math.round(totalseconds % 60);

    return String.format("%02d", hours) + ":" + String.format("%02d", minutes)
      + ":" + String.format("%02d", seconds);
  }


}


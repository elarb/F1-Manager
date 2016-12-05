package edu.tudelft.games.f1manager.core;


import java.util.ArrayList;

/**
 * This class represents a list of Drivers.
 */
public class DriverList {

  /**
   * A list of Drivers.
   */
  private ArrayList<Driver> driverList;

  /**
   * Creates an Object that represents a list of Drivers.
   *
   * @param driverList
   */
  public DriverList(ArrayList<Driver> driverList) {
    this.driverList = driverList;
  }

  public ArrayList<Driver> getDriverList() {
    return driverList;
  }

  public void setDriverList(ArrayList<Driver> driverList) {
    this.driverList = driverList;
  }
}

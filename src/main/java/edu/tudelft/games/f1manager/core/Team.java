package edu.tudelft.games.f1manager.core;

import com.google.gson.annotations.Expose;

import java.util.List;

/**
 * This abstract class represents a F1 Race Team, that has 2 Drivers, 2 Cars, a strategist,
 * an Aerodynamicist and a mechanic.
 */
public abstract class Team {

  /**
   * List of drivers of the team.
   */

  @Expose
  private List<Driver> driverList;


  /**
   * List of cars owned by the team.
   */
  @Expose
  private List<Car> carList;


  /**
   * The Strategist of the team.
   */

  @Expose
  private Strategist strategist;

  /**
   * The Aerodynamicist of the team.
   */

  @Expose
  private Aerodynamicist aerodynamicist;

  /**
   * The mechanic of the team.
   */

  @Expose
  private Mechanic mechanic;

  /**
   * Creates an object that represents a F1 Team.
   *
   * @param driverList     list of drivers in the team
   * @param carList        list of cars owned by the team
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   */
  public Team(List<Driver> driverList, List<Car> carList, Strategist strategist,
              Aerodynamicist aerodynamicist, Mechanic mechanic) {
    this.driverList = driverList;
    this.carList = carList;
    this.strategist = strategist;
    this.aerodynamicist = aerodynamicist;
    this.mechanic = mechanic;
  }


  /**
   * Checks whether the current object is equal to some other object.
   */
  public boolean equals(Object other) {
    if (other instanceof Team) {
      Team that = (Team) other;
      return this.driverList.equals(that.getDriverList()) && this.carList.equals(that.getCarList())
        && this.strategist.equals(that.getStrategist()) && this.mechanic.equals(that.getMechanic())
        && this.aerodynamicist.equals(that.getAerodynamicist());
    }
    return false;
  }

  ;

  public List<Driver> getDriverList() {
    return driverList;
  }

  public void setDriverList(List<Driver> driverList) {
    this.driverList = driverList;
  }

  public List<Car> getCarList() {
    return carList;
  }

  public void setCarList(List<Car> carList) {
    this.carList = carList;
  }

  public Strategist getStrategist() {
    return strategist;
  }

  public void setStrategist(Strategist strategist) {
    this.strategist = strategist;
  }

  public Aerodynamicist getAerodynamicist() {
    return aerodynamicist;
  }

  public void setAerodynamicist(Aerodynamicist aerodynamicist) {
    this.aerodynamicist = aerodynamicist;
  }

  public Mechanic getMechanic() {
    return mechanic;
  }

  public void setMechanic(Mechanic mechanic) {
    this.mechanic = mechanic;
  }
}

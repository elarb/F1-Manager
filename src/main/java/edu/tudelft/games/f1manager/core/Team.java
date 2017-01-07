package edu.tudelft.games.f1manager.core;

import java.util.List;

/**
 * This abstract class represents a F1 Race Team, that has 2 Drivers, 2 Cars, a strategist,
 * an Aerodynamicist and a mechanic.
 */
public abstract class Team {

  /**
   * List of drivers of the team.
   */
  private List<Driver> driverList;


  /**
   * List of cars owned by the team.
   */
  private List<Car> carList;


  /**
   * The Strategist of the team.
   */
  private Strategist strategist;

  /**
   * The Aerodynamicist of the team.
   */
  private Aerodynamicist aerodynamicist;

  /**
   * The mechanic of the team.
   */
  private Mechanic mechanic;

  /**
   * Id of the team
   */
  private int id;

  /**
   * Creates an object that represents a F1 Team.
   *
   * @param driverList     list of drivers in the team
   * @param carList        list of cars owned by the team
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   * @param id             id of the team
   */
  public Team(List<Driver> driverList, List<Car> carList, Strategist strategist,
              Aerodynamicist aerodynamicist, Mechanic mechanic, int id) {
    this.driverList = driverList;
    this.carList = carList;
    this.strategist = strategist;
    this.aerodynamicist = aerodynamicist;
    this.mechanic = mechanic;
    this.id = id;
  }

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

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
}

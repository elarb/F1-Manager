package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.tools.RandomDouble;

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
   * Car owned by the team.
   */
  private Car car;


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
   * The amount of points the team has.
   */
  private int points;

  /**
   * The id of the team (unique).
   */
  private int id;

  /**
   * Name of the team.
   */
  private String name;


  /**
   * Creates an object that represents a F1 Team.
   *
   * @param name
   * @param id             the id of the team
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   * @param driverList     list of drivers in the team
   * @param car            car owned by the team
   * @param points         the amount of points of the team
   */
  public Team(String name, int id, Strategist strategist, Aerodynamicist aerodynamicist, Mechanic mechanic, List<Driver> driverList, Car car,
              int points) {
    this.name = name;
    this.driverList = driverList;
    this.car = car;
    this.strategist = strategist;
    this.aerodynamicist = aerodynamicist;
    this.mechanic = mechanic;
    this.points = points;
    this.id = id;
  }


  /**
   * Checks whether the current object is equal to some other object.
   */
  public boolean equals(Object other) {
    if (other instanceof Team) {
      Team that = (Team) other;
      return this.id == that.id;
    }
    return false;
  }

  public double teamFactor() {

    if (this.strategist.hasCrashed()) {

      return 0;

    } else {

      double strategist = 1 / (Constants.STRATEGIST_COEF * this.strategist.getRating());
      double grip = 1 / (Constants.GRIP_COEF * this.car.getTyres().getGrip());
      double aerodynamics = 1 / (Constants.AERODYNAMISIST_COEF * this.getAerodynamicist().getExpertise());
      double body = 1 / (Constants.BODY_COEF * this.car.getBody());
      double engine = 1 / (Constants.ENGINE_COEF * this.car.getEngine().getPrice());

      return strategist * grip * aerodynamics * body * engine;

    }

  }

  /**
   * Adds the driver to the team's list of drivers.
   *
   * @param driver the driver that gets added
   */
  public void addDriver(Driver driver) {
    this.driverList.add(driver);
  }

  public double getResultsDriver1() {
    double driver = 1 / (Constants.DRIVER_COEF * this.driverList.get(0).getValue());
    return RandomDouble.generate(0, 1) * driver * this.teamFactor();
  }

  public double getResultsDriver2() {
    double driver = 1 / (Constants.DRIVER_COEF * this.driverList.get(1).getValue());
    return RandomDouble.generate(0, 1) * driver * this.teamFactor();
  }

  public List<Driver> getDriverList() {
    return driverList;
  }

  public void setDriverList(List<Driver> driverList) {
    this.driverList = driverList;
  }

  public Car getCar() {
    return car;
  }

  public void setCar(Car car) {
    this.car = car;
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

  public int getPoints() {
    return points;
  }

  public void setPoints(int points) {
    this.points = points;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}





package edu.tudelft.games.f1manager.core;

/**
 * This abstract class represents a F1 Race Team, that has 2 Drivers, 2 Cars, a strategist,
 * an Aerodynamicist and a mechanic.
 */
public abstract class Team {

  /**
   * Driver #1 of the team.
   */
  private Driver driver1;

  /**
   * Driver #2 of the team.
   */
  private Driver driver2;


  /**
   * Car #1 of the team.
   */
  private Car car1;

  /**
   * Car #2 of the team.
   */
  private Car car2;

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
   * Creates an Object that represents a F1 Team.
   *
   * @param driver1        Driver #1 of the team
   * @param driver2        Driver #2 of the team
   * @param car1           Car #1 of the team
   * @param car2           Car #2 of the team
   * @param strategist     Strategist of the team
   * @param aerodynamicist Aerodynamicist of the team
   * @param mechanic       Mechanic of the team
   */
  public Team(Driver driver1, Driver driver2, Car car1, Car car2, Strategist strategist,
              Aerodynamicist aerodynamicist, Mechanic mechanic) {
    this.driver1 = driver1;
    this.driver2 = driver2;
    this.car1 = car1;
    this.car2 = car2;
    this.strategist = strategist;
    this.aerodynamicist = aerodynamicist;
    this.mechanic = mechanic;
  }



}

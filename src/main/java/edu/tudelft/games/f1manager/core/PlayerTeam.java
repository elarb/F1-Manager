package edu.tudelft.games.f1manager.core;


public class PlayerTeam extends Team {

  /**
   * The budget a PlayerTeam has in Euro's. Is divisible by 100.   budget + (100 - (x % 100 ?: 100))
   */
  private int budget;

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
   * @param budget
   */
  public PlayerTeam(Driver driver1, Driver driver2, Car car1, Car car2, Strategist strategist, Aerodynamicist aerodynamicist, Mechanic mechanic, int budget) {
    super(driver1, driver2, car1, car2, strategist, aerodynamicist, mechanic);
    this.budget = budget;
  }


}

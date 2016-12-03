package edu.tudelft.games.f1manager.core;


import java.util.List;

public class PlayerTeam extends Team {

  /**
   * The budget a PlayerTeam has in Euro's. Is divisible by 100.   budget + (100 - (x % 100 ?: 100))
   */
  private int budget;

  /**
   * Is true if the team owns a software tester. If a team doesn't own a software-tester,
   * the chance for a crash increases significantly.
   */
  private boolean hasSoftwareTester;


  /**
   * Creates an object that represents the F1 Team of a player.
   *
   * @param driverList        list of drivers in the team
   * @param carList           list of cars owned by the team
   * @param strategist        strategist of the team
   * @param aerodynamicist    aerodynamicist of the team
   * @param mechanic          mechanic of the team
   * @param hasSoftwareTester is true if the team owns a software tester
   */
  public PlayerTeam(List<Driver> driverList, List<Car> carList,
                    Strategist strategist, Aerodynamicist aerodynamicist,
                    Mechanic mechanic, int budget, boolean hasSoftwareTester) {
    super(driverList, carList, strategist, aerodynamicist, mechanic);
    this.budget = budget;
    this.hasSoftwareTester = hasSoftwareTester;
  }
}

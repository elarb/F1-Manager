package edu.tudelft.games.f1manager.core;

import java.util.List;


/**
 * Class that represents an AI F1 team.
 */
public class AiTeam extends Team {


  /**
   * Creates an object that represents a AI Team.
   *
   * @param driverList     list of drivers in the team
   * @param carList        list of cars owned by the team
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   * @param points         the amount of points of the team
   * @param id             the id of the team
   */
  public AiTeam(List<Driver> driverList, List<Car> carList, Strategist strategist,
                Aerodynamicist aerodynamicist, Mechanic mechanic, int points, int id) {
    super(driverList, carList, strategist, aerodynamicist, mechanic, points, id);
  }

}

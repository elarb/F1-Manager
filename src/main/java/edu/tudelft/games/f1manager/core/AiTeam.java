package edu.tudelft.games.f1manager.core;

import com.google.common.base.Preconditions;

import java.util.List;


/**
 * Class that represents an AI F1 team.
 */
public class AiTeam extends Team {


  /**
   * Constructor for AI Team.
   *
   * @param name           name of the team
   * @param id             id of the team (not 0 or 1)
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   * @param driverList     drivers in the team
   * @param car            car developed by the team
   * @param points         amount of points the team has
   */
  @SuppressWarnings("CheckStyle")
  public AiTeam(String name, int id, Strategist strategist, Aerodynamicist aerodynamicist,
                Mechanic mechanic, List<Driver> driverList, Car car, int points) {
    super(name, id, strategist, aerodynamicist, mechanic, driverList, car, points);
    Preconditions.checkArgument(id > 1, "ID not valid for an aitaem, id: %s", id);
  }
}

package edu.tudelft.games.f1manager.core;

import java.util.List;

/**
 * Class that represents a AI F1 team.
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
   * @param id             id of the team
   */
  public AiTeam(List<Driver> driverList, List<Car> carList, Strategist strategist,
                Aerodynamicist aerodynamicist, Mechanic mechanic, int id) {
    super(driverList, carList, strategist, aerodynamicist, mechanic, id);
  }

  /**
   * @param driver driver that gets bought
   * @param team   team the driver gets bought from
   */
  void buyDriver(Driver driver, Team team) {
    if (team instanceof PlayerTeam) {
      PlayerTeam playerteam = (PlayerTeam) team;
      playerteam.setBudget(playerteam.getBudget() + driver.getValue());
    }
    driver.setTeamId(this.getId());
    this.getDriverList().add(driver);
  }

}

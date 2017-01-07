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
   */
  public AiTeam(List<Driver> driverList, List<Car> carList, Strategist strategist,
                Aerodynamicist aerodynamicist, Mechanic mechanic) {
    super(driverList, carList, strategist, aerodynamicist, mechanic);
  }

  /**

   * transfers the driver to this AiTeam
   * if the old team was a PlayerTeam the PlayerTeam gets the value of the driver added to their budget.
   * @param driver - Driver
   */
  public void buyDriver(Driver driver){
    if(driver.getTeam() instanceof AiTeam){
        driver.transfer(this);
    } else{
      PlayerTeam oldTeam = (PlayerTeam)driver.getTeam();
      oldTeam.setBudget(oldTeam.getBudget() + driver.getValue());
        driver.transfer(this);
    }
  }
}

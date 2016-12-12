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
   * @param drivers           list of drivers in the team
   * @param cars              list of cars owned by the team
   * @param strategist        strategist of the team
   * @param aerodynamicist    aerodynamicist of the team
   * @param mechanic          mechanic of the team
   * @param hasSoftwareTester is true if the team owns a software tester
   */
  public PlayerTeam(List<Driver> drivers, List<Car> cars,
                    Strategist strategist, Aerodynamicist aerodynamicist,
                    Mechanic mechanic, int budget, boolean hasSoftwareTester) {
    super(drivers, cars, strategist, aerodynamicist, mechanic);
    this.budget = budget;
    this.hasSoftwareTester = hasSoftwareTester;
  }

  /**
   * method to buy/transfer a driver from another team
   * @param driver
   */
  public void buyDriver(Driver driver, int offer){
    for(int i = 0; i<this.getDriverList().size(); i++){
      if(driver == this.getDriverList().get(i)){
        return;
      }
    }
    int acceptValue = driver.getValue() /*+ add random*/;
    if((acceptValue <= offer) && (this.getBudget() >= offer)){
      driver.transfer(this);
      this.setBudget(this.getBudget() - offer);
    }
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public boolean isHasSoftwareTester() {
    return hasSoftwareTester;
  }

  public void setHasSoftwareTester(boolean hasSoftwareTester) {
    this.hasSoftwareTester = hasSoftwareTester;

  }
}


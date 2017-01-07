package edu.tudelft.games.f1manager.core;

import com.google.common.base.Preconditions;

public class Mechanic {

  /**
   * Time it takes for the mechanic to handle a pitstop.
   */
  private int pitstopTime;
  private int upgradePrice;

  /**
   * Creates an objec that represents a mechanic in a F1 Team.
   *
   * @param ipitstopTime time it takes for the mechanic to handle a pitstop
   */
  public Mechanic(int ipitstopTime) {
    Preconditions.checkArgument(ipitstopTime <= 8, "Pitstoptime above 8", ipitstopTime);
    Preconditions.checkArgument(ipitstopTime >= 2, "Pitstoptime below 2", ipitstopTime);
    this.pitstopTime = ipitstopTime;
  }

  //TODO: there should be a better way to check if the team has enough budget,
  // this method could be in the playerteam Class for example.

  /**
   * Improves the pit-stop time of the mechanic.
   *
   * @param team team the mechanic is in,
   *              the budget is getting checked to make sure there is enough
   */
  public void improve(PlayerTeam team) {
    if (this.pitstopTime > 2) {
      if (team.getBudget() >= this.upgradePrice) {
        team.setBudget(team.getBudget() - this.upgradePrice);
        this.pitstopTime -= 1;
        updateUpgradePrice();
      }
    }
  }

  public void updateUpgradePrice() {
    this.upgradePrice = (this.pitstopTime * -1 + 9) * Constants.BASE_PITSTOP_UP_PRICE;
  }

  public int getPitstopTime() {
    return pitstopTime;
  }

  public int getUpgradePrice() {
    return upgradePrice;
  }

}

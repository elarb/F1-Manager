package edu.tudelft.games.f1manager.core;

import com.google.common.base.Preconditions;

import edu.tudelft.games.f1manager.game.GameEvent;

public class Mechanic implements Upgradeable {

  /**
   * Time it takes for the mechanic to handle a pitstop.
   */
  private int pitstopTime;

  private int upgradePrice;

  /**
   * Creates an objec that represents a mechanic in a F1 Team.
   *
   * @param pitstoptime time it takes for the mechanic to handle a pitstop
   */
  public Mechanic(int pitstoptime) {
    Preconditions.checkArgument(pitstoptime <= 8, "Pitstoptime above 8", pitstoptime);
    Preconditions.checkArgument(pitstoptime >= 2, "Pitstoptime below 2", pitstoptime);
    this.pitstopTime = pitstoptime;
  }


  /**
   * Improves the pit-stop time of the mechanic.
   */
  public GameEvent upgrade(int stat) {
    if (this.pitstopTime > 2) {
      this.pitstopTime -= 1;
      updateUpgradePrice();
      String msg = "Your Mechanic has been upgraded! New pitstoptime: " + this.pitstopTime;
      return new GameEvent(msg, GameEvent.Type.UPGRADE);
    }
    String msg = "Your pitstoptime is already at its lowest";
    return new GameEvent(msg, GameEvent.Type.UPGRADE);
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
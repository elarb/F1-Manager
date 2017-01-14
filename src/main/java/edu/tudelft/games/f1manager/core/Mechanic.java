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

  @Override
  public GameEvent upgrade() {
    if (this.pitstopTime > 2) {
      this.pitstopTime -= 1;
      this.upgradePrice = (this.pitstopTime * -1 + 9) * Constants.BASE_PITSTOP_UP_PRICE;

      String msg = String.format("Your mechanic has been upgraded! New average Pitstop "
          + "time is now: %d seconds.", this.pitstopTime);
      return new GameEvent(msg, GameEvent.Type.UPGRADE, true);
    }
    String failMsg = "Upgrade failed, your Mechanic is already maxed.";
    return new GameEvent(failMsg, GameEvent.Type.UPGRADE, false);
  }

  @Override
  public void upgradeBy(int num) {

  }

  public int getPitstopTime() {
    return pitstopTime;
  }

  public int getUpgradePrice() {
    return upgradePrice;
  }

}

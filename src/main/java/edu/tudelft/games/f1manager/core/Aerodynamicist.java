package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.GameEvent;

/**
 * This class represents an Aerodynamicist.
 */
public class Aerodynamicist implements Upgradeable {

  /**
   * Expertise the Aerodynamicist has, expressed as an integer from 60-99.
   */
  private int expertise;

  /**
   * Creates an object that represents an Aerodynamicist of a F1 team.
   *
   * @param expertise The expertise the Aerodynamicist has
   */
  public Aerodynamicist(int expertise) {
    this.expertise = expertise;
  }


  /**
   * Returns this event.
   * Suppose x = expertise.
   * If <79, then expertise can increase with 3, 4 or 5
   * If 80< x <89, then expertise can increase with 2, 3 or 4
   * If 90< x <99, then expertise can increase with 1, 2 or 3
   */
  public GameEvent upgrade(int stat) {
    if (this.expertise <= 79) {
      upgradeBy(3);
    } else if (this.expertise <= 89) {
      upgradeBy(2);
    } else if (this.expertise <= 95) {
      upgradeBy(1);
    } else if (this.expertise <= 99) {
      this.expertise++;
    }
    String msg = "Your Aerodynamicist has been upgraded! New expertise: " + this.expertise;
    return new GameEvent(msg, GameEvent.Type.UPGRADE);
  }

  /**
   * Upgrades the expertise by a number, or the number + 1 , or by the number + 2 (randomly).
   *
   * @param num The lowest number the expertise will increase with
   */
  public void upgradeBy(int num) {
    double random = Math.random();

    if (random < 0.3) {
      //30% chance
      this.expertise = this.expertise + num;
    } else if (random < 0.7) {
      //40% chance
      this.expertise = this.expertise + num + 1;
    } else {
      //30% chance
      this.expertise = this.expertise + num + 2;
    }
  }

  public int getExpertise() {
    return expertise;
  }

  public void setExpertise(int expertise) {
    this.expertise = expertise;
  }
}

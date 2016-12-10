package edu.tudelft.games.f1manager.core;

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
   * Suppose x = expertise.
   * If 60< x <69, then expertise can increase with 4, 5 or 6.
   * If 70< x <79, then expertise can increase with 3, 4 or 5
   * If 80< x <89, then expertise can increase with 2, 3 or 4
   * If 90< x <99, then expertise can increase with 1, 2 or 3
   */
  public void upgrade() {
    if (this.expertise >= 60 && this.expertise <= 69) {
      upgradeBy(4);
    } else if (this.expertise >= 70 && this.expertise <= 79) {
      upgradeBy(3);
    } else if (this.expertise >= 80 && this.expertise <= 89) {
      upgradeBy(2);
    } else if (this.expertise >= 90 && this.expertise <= 99) {
      upgradeBy(1);
    }
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

}
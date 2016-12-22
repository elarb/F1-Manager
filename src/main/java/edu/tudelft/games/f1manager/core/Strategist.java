package edu.tudelft.games.f1manager.core;

import com.google.common.base.Preconditions;

public class Strategist implements Upgradeable {

  /**
   * The strategy of the strategist. Can be low-, medium-, high-risk.
   */
  private Risk strategy;

  /**
   * Rating of the strategist expressed as an integer from 70 - 99.
   */
  private int rating;

  /**
   * Creates an object that represents a Strategist of a F1 team.
   *
   * @param strategy the strategy of the strategist
   * @param rating   the rating of the strategist
   */
  public Strategist(Risk strategy, int rating) {
    Preconditions.checkArgument(rating >= 70, "rating below 70: %s", rating);
    Preconditions.checkArgument(rating <= 99, "rating above 99: %s", rating);
    this.strategy = strategy;
    this.rating = rating;
  }

  /**
   * Suppose x = expertise.
   * If 70< x <79, then expertise can increase with 3, 4 or 5
   * If 80< x <89, then expertise can increase with 2, 3 or 4
   * If 90< x <95, then expertise can increase with 1, 2 or 3
   * If x > 96, then expertise will increase with 1
   */
  public void upgrade() {

    if (this.rating <= 79) {
      upgradeBy(3);
    } else if (this.rating <= 89) {
      upgradeBy(2);
    } else if (this.rating <= 95) {
      upgradeBy(1);
    } else if (this.rating < 99) {
      this.rating++;
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
      this.rating = this.rating + num;
    } else if (random < 0.7) {
      //40% chance
      this.rating = this.rating + num + 1;
    } else {
      //30% chance
      this.rating = this.rating + num + 2;
    }
  }

  /**
   * Returns true if the strategist is max rated.
   *
   * @return true if the strategist is max rated
   */
  public boolean isMaxRated() {

    return this.rating == 99;
  }

  /**
   * Returns the increased crash chance based on the rating of
   * the strategist and the strategy picked.
   *
   * @return increased crash chance
   */
  public double getIncreasedCrashChance() {
    switch (this.strategy) {
      case LOW:
        return calculateIncreasedChanceLowRisk();
      case MEDIUM:
        return calculateIncreasedChanceLowRisk() + (0.5 * Constants.CRASH_CHANCE);
      case HIGH:
        return calculateIncreasedChanceLowRisk() + Constants.CRASH_CHANCE;
      default:
        throw new IllegalArgumentException("Risk Strategy not set");
    }
  }

  /**
   * Returns the increased crash chance at low risk based on the rating of the driver.
   * Get's calculated by a polynomial function.
   *
   * @return the increased crash chance at low risk
   */
  public double calculateIncreasedChanceLowRisk() {
    return ((1.784892334 * Math.pow(10, -4)) * Math.pow(this.getRating(), 3)
      - (3.794692929 * Math.pow(10, -2)) * Math.pow(this.getRating(), 2))
      + (2.069456242 * this.rating) - 14.14001765;
  }

  /**
   * Indicates whether some other object is equal to the current object.
   *
   * @param other the other object
   * @return true if the other object is equal to the current object
   */
  //TODO: Write tests for this method
  public boolean equals(Object other) {


    if (other instanceof Strategist) {
      Strategist oth = (Strategist) other;

      return this.strategy.equals(oth.strategy) && this.rating == oth.rating;
    }
    return false;
  }


  public Risk getStrategy() {
    return strategy;
  }

  public void setStrategy(Risk strategy) {
    this.strategy = strategy;
  }

  public int getRating() {
    return rating;
  }

  public void setRating(int rating) {
    this.rating = rating;
  }

  public enum Risk {
    LOW, MEDIUM, HIGH
  }
}

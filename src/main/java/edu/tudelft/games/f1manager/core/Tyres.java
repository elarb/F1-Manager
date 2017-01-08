package edu.tudelft.games.f1manager.core;

/**
 * Tyres of a F1 car.
 */
public class Tyres {

  /**
   * Type of the tyres.
   */
  private int hardness;

  /**
   * Creates an Object that represents the tyres of a F1 Car.
   *
   * @param hardness Type of the tyres
   */
  public Tyres(int hardness) {
    this.hardness = hardness;
  }

  /**
   * calculates the durability with the hardness.
   *
   * @return int - Durability
   */
  public int getDurability() {
    return 20 * this.getHardness();
  }

  /**
   * calculates the grip with the hardness.
   *
   * @return int - grip
   */
  public int getGrip() {
    return 20 * (5 - this.getHardness());
  }


  public int getHardness() {
    return hardness;
  }

  public void setHardness(int hardness) {
    this.hardness = hardness;
  }
}

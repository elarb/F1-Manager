package edu.tudelft.games.f1manager.core;

/**
 * Tyres of a F1 car.
 */
public class Tyres {

  /**
   * Name of the tyres.
   */
  private String name;

  /**
   * Type of the tyres.
   */
  private int hardness;

  /**
   * Creates an Object that represents the tyres of a F1 Car.
   *
   * @param name Name of the tyres
   * @param hardness Type of the tyres
   */
  public Tyres(String name, int hardness) {
    this.name = name;
    this.hardness = hardness;
  }

  /**
   * calculates the durability with the hardness
   * @return int - Durability
   */
  public int getDurability(){
    return 20 * this.getHardness();
  }

  /**
   * calculates the grip with the hardness
   * @return int - grip
   */
  public int getGrip(){
    return 20 * (5 - this.getHardness());
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getHardness() {
    return hardness;
  }

  public void setHardness(int hardness) {
    this.hardness = hardness;
  }
}

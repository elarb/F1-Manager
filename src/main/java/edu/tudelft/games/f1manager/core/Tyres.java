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
  private String type;

  /**
   * Creates an Object that represents the tyres of a F1 Car.
   *
   * @param name Name of the tyres
   * @param type Type of the tyres
   */
  public Tyres(String name, String type) {
    this.name = name;
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }
}

package edu.tudelft.games.f1manager.core;

/**
 * This class represents a F1 Car.
 */
public class Car {
  /**
   * Engine of the Car.
   */
  private Engine engine;

  /**
   * Body of the Car.
   */
  private double body;

  /**
   * Tyres of the Car.
   */
  private Tyres tyres;

  /**
   * Creates a new Object that represents a Car.
   *
   * @param engine The engine of the Car
   * @param body   The body of the Car
   * @param tyres  The tyres of the Car
   */

  public Car(Engine engine, double body, Tyres tyres) {
    this.engine = engine;
    this.body = body;
    this.tyres = tyres;
  }

  public Engine getEngine() {
    return engine;
  }

  public void setEngine(Engine engine) {
    this.engine = engine;
  }

  public double getBody() {
    return body;
  }

  public void setBody(double body) {
    this.body = body;
  }

  public Tyres getTyres() {
    return tyres;
  }

  public void setTyres(Tyres tyres) {
    this.tyres = tyres;
  }
}

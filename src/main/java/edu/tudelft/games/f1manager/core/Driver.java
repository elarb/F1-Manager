package edu.tudelft.games.f1manager.core;

public class Driver {

  private String name;
  private double speed;
  private double racecraft;
  private double strategyinsight;
  private int value;

  /**
   * Id of the team associated to the driver.
   */
  private int teamId;

  /**
   * Creates an object that represents a F1 Driver.
   *
   * @param name   name of the driver
   * @param teamId if of the team associated to the driver
   */
  public Driver(String name, int teamId) {
    this.name = name;
    this.teamId = teamId;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getSpeed() {
    return speed;
  }

  public void setSpeed(double speed) {
    this.speed = speed;
  }

  public double getRacecraft() {
    return racecraft;
  }

  public void setRacecraft(double racecraft) {
    this.racecraft = racecraft;
  }

  public double getStrategyinsight() {
    return strategyinsight;
  }

  public void setStrategyinsight(double strategyinsight) {
    this.strategyinsight = strategyinsight;
  }

  public int getValue() {
    return value;
  }

  public void setValue(int value) {
    this.value = value;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
  }
}

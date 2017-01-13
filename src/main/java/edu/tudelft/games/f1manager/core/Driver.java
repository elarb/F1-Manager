package edu.tudelft.games.f1manager.core;


public class Driver {


  private String name;

  private double speed;

  private double racecraft;

  private double strategyinsight;

  private int value;

  private int teamId;

  /**
   * Creates an object that represents a Driver in a F1 Team.
   *
   * @param name   name of the driver
   * @param teamId of the team the driver is in, null if the driver doesn't have a team
   */
  public Driver(String name, int teamId) {
    this.name = name;
    this.teamId = teamId;
    this.value = (int) ((this.speed * Constants.SPEEDCOEF)
      * (this.racecraft * Constants.RACECRAFTCOEF)
      * (this.strategyinsight * Constants.STRATEGYINSIGHTCOEF)
      * Constants.DRIVERBASEPRICE);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getTeamId() {
    return teamId;
  }

  public void setTeamId(int teamId) {
    this.teamId = teamId;
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


}

package edu.tudelft.games.f1manager.core;

public class Driver {

  private String name;
  private Team team;
  private double speed;
  private double racecraft;
  private double strategyinsight;
  private int value;

  /**
   * Creates an object that represents a Driver in a F1 Team.
   *
   * @param iname name of the driver
   * @param iteam team the driver is in, null if driver has no team
   */
  public Driver(String iname, Team iteam) {
    this.name = iname;
    this.team = iteam;
    this.value = (int) ((this.speed * Constants.SPEEDCOEF)
      * (this.racecraft * Constants.RACECRAFTCOEF)
      * (this.strategyinsight * Constants.STRATEGYINSIGHTCOEF)
      * Constants.DRIVERBASEPRICE);
  }

  /**
   * Transfers the driver to another team.
   *
   * @param team the team that the driver gets transferred to
   */
  public void transfer(Team team) {
    this.getTeam().getDriverList().remove(this);
    this.setTeam(team);
    team.getDriverList().add(this);
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Team getTeam() {
    return team;
  }

  public void setTeam(Team team) {
    this.team = team;
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

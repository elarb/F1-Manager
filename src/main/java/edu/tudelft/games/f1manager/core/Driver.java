package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.GameEvent;

import java.util.Random;

public class Driver implements Upgradeable {


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
  }

  @Override
  public String toString() {
    return "Driver{"
      + "name='" + name + '\''
      + ", speed=" + speed + ", racecraft=" + racecraft
      + ", strategyinsight=" + strategyinsight
      + ", value=" + value + ", teamId=" + teamId + '}';
  }

  /**
   * Upgrades the driver.
   *
   * @param stat indicates which property gets upgraded
   * @return a GameEvent
   */
  public GameEvent upgrade(int stat) {
    Random rand = new Random();
    if (stat == 0) {
      if (this.speed != 10) {
        this.speed += rand.nextDouble() * Constants.DRIVERUPGRADE;
        if (this.speed > 10) {
          this.speed = 10;
        }
        String msg = "Your Driver's speed is now: " + this.speed;
        return new GameEvent(msg, GameEvent.Type.UPGRADE);
      }
      String msg = "Your driver's speed is already at max";
      return new GameEvent(msg, GameEvent.Type.UPGRADE);

    } else if (stat == 1) {
      if (this.racecraft != 10) {
        this.racecraft += rand.nextDouble() * Constants.DRIVERUPGRADE;
        if (this.racecraft > 10) {
          this.racecraft = 10;
        }
        this.determineValue();
        String msg = "Your Driver's racecraft is now: " + this.racecraft;
        return new GameEvent(msg, GameEvent.Type.UPGRADE);
      }
      this.determineValue();
      String msg = "Your driver's racecraft is already at max";
      return new GameEvent(msg, GameEvent.Type.UPGRADE);

    } else if (stat == 2) {
      if (this.strategyinsight != 10) {
        this.strategyinsight += rand.nextDouble() * Constants.DRIVERUPGRADE;
        if (this.strategyinsight > 10) {
          this.strategyinsight = 10;
        }
        this.determineValue();
        String msg = "Your Driver's strategy insight is now: " + this.strategyinsight;
        return new GameEvent(msg, GameEvent.Type.UPGRADE);
      }
      this.determineValue();
      String msg = "Your driver's strategy insight is already at max";
      return new GameEvent(msg, GameEvent.Type.UPGRADE);
    } else {
      this.determineValue();
      String msg = "The stat you tried to update was invalid";
      return new GameEvent(msg, GameEvent.Type.UPGRADE);
    }
  }

  @Override
  public boolean equals(Object other) {
    if (other instanceof Driver) {
      Driver oth = (Driver) other;
      return name.equals(oth.getName());
    }
    return false;
  }

  /**
   * Determines the value of the driver.
   */
  public void determineValue() {
    this.value = (int) (
      ((this.speed * Constants.SPEEDCOEF)
      + (this.racecraft * Constants.RACECRAFTCOEF)
      + (this.strategyinsight * Constants.STRATEGYINSIGHTCOEF)
      ) * Constants.DRIVERBASEPRICE);
  }

  public int getRating() {
    double rating = ((this.racecraft + this.speed + this.strategyinsight) / 3) * 10;
    return (int) rating;
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

  /**returns the value af the driver.
   * @return an int representing the value of the driver.
   */
  public int getValue() {
    return (int) (((this.speed * Constants.SPEEDCOEF)
      + (this.racecraft * Constants.RACECRAFTCOEF)
      + (this.strategyinsight * Constants.STRATEGYINSIGHTCOEF)
      ) * Constants.DRIVERBASEPRICE);
  }

  public void setValue(int value) {
    this.value = value;
  }

}

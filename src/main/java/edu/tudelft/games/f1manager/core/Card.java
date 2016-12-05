package edu.tudelft.games.f1manager.core;

public class Card {
  private double speed;
  private double racecraft;
  private double strategyinsight;
  private int value;

  public Card(double ispeed, double iracecraft, double istrategyinsight) {
    this.speed = ispeed;
    this.racecraft = iracecraft;
    this.strategyinsight = istrategyinsight;
  }

  public void evaluate() {
    this.value = (int) ((this.speed*Constants.SPEEDCOEF)*(this.racecraft*Constants.RACECRAFTCOEF)*(this.strategyinsight*Constants.STRATEGYINSIGHTCOEF)*Constants.DRIVERBASEPRICE);
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

}

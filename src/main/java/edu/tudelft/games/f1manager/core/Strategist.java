package edu.tudelft.games.f1manager.core;

public class Strategist {

  /**
   * The strategy of the strategist. Can be low-, medium-, high-risk.
   */
  private String strategy;

  /**
   * Creates an object that represents a Strategist of a F1 team.
   *
   * @param strategy The strategy of the strategist
   */
  public Strategist(String strategy) {
    this.strategy = strategy;
  }

  public String getStrategy() {
    return strategy;
  }

  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }
}

package edu.tudelft.games.f1manager.core;

public class Strategist {

  /**
   * The strategy of the strategist. Can be low-, medium-, high-risk.
   */
  private int risk;

  /**
   * Creates an object that represents a Strategist of a F1 team.
   *
   * @param strategy The strategy of the strategist
   */
  public Strategist(int iRisk) {
    this.risk = iRisk;
  }

  public int getRisk() {
    return risk;
  }

  public void setStrategy(int iRisk) {
    this.risk = iRisk;
  }
}

package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.Team;

import java.util.ArrayList;


public class Season {

  /**
   * The current race in the season.
   */
  private int currentRace;

  /**
   * List of drivers sorted by points.
   */
  private ArrayList<Team> constructorStandings;

  /**
   * Creates an object that represents a F1 season.
   *
   * @param currentRace          current race in the season
   * @param constructorStandings list of drivers sorted by points
   */
  public Season(int currentRace, ArrayList<Team> constructorStandings) {
    this.currentRace = currentRace;
    this.constructorStandings = constructorStandings;
  }

  public int getCurrentRace() {
    return currentRace;
  }

  public void setCurrentRace(int currentRace) {
    this.currentRace = currentRace;
  }

  public ArrayList<Team> getConstructorStandings() {
    return constructorStandings;
  }

  public void setConstructorStandings(ArrayList<Team> constructorStandings) {
    this.constructorStandings = constructorStandings;
  }
}

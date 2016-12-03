package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> AiTeamList;

  /**
   * constructor for AiTeamList
   */
  public AiTeamList() {
  }

  public ArrayList<AiTeam> getAiTeamList() {
    return AiTeamList;
  }

  public void setAiTeamList(ArrayList<AiTeam> aiTeamList) {
    this.AiTeamList = aiTeamList;
  }


}
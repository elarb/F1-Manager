package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams
 */
public class ComputerTeamList {

  /**
   * a list of ComputerTeams
   */
  private ArrayList<ComputerTeam> ComputerTeamList;

  /**
   * constructor for ComputerTeamList
   */
  public ComputerTeamList(){
  }
  
  public ArrayList<ComputerTeam> getComputerTeamList() {
    return ComputerTeamList;
  }

  public void setComputerTeamList(ArrayList<ComputerTeam> computerTeamList) {
    ComputerTeamList = computerTeamList;
  }


}
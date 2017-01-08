package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

/**
 * Class that represents a list of ComputerTeams.
 */
public class AiTeamList {

  /**
   * A list of ComputerTeams.
   */
  private ArrayList<AiTeam> aiTeamList;

  /**
   * Constructor for aiTeamList.
   */
  public AiTeamList() {
  }

  public void balanceDrivers() {
	  for (int i = 0; i < this.aiTeamList.size(); i++) {
		  if (aiTeamList.get(i).getDriverList().size() < 2) {
			  if (aiTeamList.get(i).getDriverList().size() < 1) {
				  this.buyLeftoverDriver(aiTeamList.get(i));
			  }
			  this.buyLeftoverDriver(aiTeamList.get(i));
		  }
	  }
  }
  
  public void buyLeftoverDriver(AiTeam aiTeam){
	  boolean bought = false;
	  for (int i = 0; i < this.aiTeamList.size(); i++) {
		  if (this.aiTeamList.get(i).getDriverList().size() > 2 && bought == false) {
			  aiTeam.buyDriver(this.aiTeamList.get(i).getDriverList().get(0));
			  bought = true;
		  }
		  
		  if (bought == false) {
			  aiTeam.buyDriver("REFEREER NAAR PLAYERTEAM".getDriverList().get(0));
			  bought = true;
		  }
		  
	  }
  }
  
  public ArrayList<AiTeam> getAiTeamList() {
    return aiTeamList;
  }

  public void setAiTeamList(ArrayList<AiTeam> aiTeamList) {
    this.aiTeamList = aiTeamList;
  }


}
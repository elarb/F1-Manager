package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
	  }
	  
	  if (bought == false) {
		  aiTeam.buyDriver("REFEREER NAAR PLAYERTEAM".getDriverList().get(0));
		  bought = true;
	  }
	  
  }
  
  public void buyRandomDriver() {
	  Random rand = new Random();
	  int r = rand.nextInt(Constants.MAX_BUYS);
	  for (int i = 0; i < r; i++) {
		  if (rand.nextInt(100) < Constants.BUYS_FROM_PLAYER) {
			  Driver buyingDriver = "REFEREER NAAR PLAYERTEAM".getDriverList().get("PLAYERTEAM".getDriverList().size());
			  this.aiTeamList.get(rand.nextInt(this.aiTeamList.size())).buyDriver(buyingDriver);;
		  } else {
			  List<Driver> buyingDriverList = this.getAiTeamList().get(rand.nextInt(this.getAiTeamList().size())).getDriverList();
			  Driver buyingDriver = buyingDriverList.get(rand.nextInt(buyingDriverList.size()));
			  AiTeam buyingTeam = this.getAiTeamList().get(rand.nextInt(this.getAiTeamList().size()));
			  buyingTeam.buyDriver(buyingDriver);
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
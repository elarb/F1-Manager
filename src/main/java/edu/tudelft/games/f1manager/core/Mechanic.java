package edu.tudelft.games.f1manager.core;

import com.google.common.base.Preconditions;

public class Mechanic {

  private int pitstopTime;
  private int upgradePrice;

  public Mechanic(int ipitstopTime) {
	Preconditions.checkArgument(ipitstopTime <= 8, "Pitstoptime above 8", ipitstopTime);
	Preconditions.checkArgument(ipitstopTime >= 2, "Pitstoptime below 2", ipitstopTime);
    this.pitstopTime = ipitstopTime;
  }

  public void improve(PlayerTeam iTeam) {
	if (this.pitstopTime > 2) {
		if (iTeam.getBudget() >= this.upgradePrice) {
		      iTeam.setBudget(iTeam.getBudget() - this.upgradePrice);
		      this.pitstopTime -= 1;
		      updateUpgradePrice();
		    }
	}
  }

  public void updateUpgradePrice() {
    this.upgradePrice = ((this.pitstopTime * -1 + 9) * Constants.BASE_PITSTOP_UP_PRICE);
  }

public int getPitstopTime() {
	return pitstopTime;
}

public int getUpgradePrice() {
	return upgradePrice;
}

}

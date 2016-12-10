package edu.tudelft.games.f1manager.core;

public class Mechanic {

	private int pitstopTime;
	private Tyres tyres;
	private int upgradePrice;
	
	public Mechanic(int ipitstopTime, Tyres ityres) {
		this.pitstopTime = ipitstopTime;
		this.tyres = ityres;
	}
	
	public void improve(PlayerTeam iTeam) {
		if (iTeam.getBudget() <= this.upgradePrice) {
			iTeam.setBudget(iTeam.getBudget() - this.upgradePrice);
			this.pitstopTime -= 1;
			updateUpgradePrice();
		}
	}
	
	public void updateUpgradePrice() {
		this.upgradePrice = ((this.pitstopTime * -1 + 9)*Constants.BASE_PITSTOP_UP_PRICE);
	}
	
	public void changeTyres(Car iCar, int iHardness) {
		iCar.getTyres().setHardness(iHardness);
	}

}

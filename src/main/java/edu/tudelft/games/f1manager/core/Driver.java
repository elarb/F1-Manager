package edu.tudelft.games.f1manager.core;

public class Driver {
	
	private String name;
	private Team team;
	
	private double speed;
	private double racecraft;
	private double strategyinsight;
	private int value;
	
	public Driver(String iname, Team iteam) {
		this.name = iname;
		this.team = iteam;
	}

	public void transfer(Team team){
    this.getTeam().getDriverList().remove(this);
    this.setTeam(team);
    team.getDriverList().add(this);
  }
	
	public void calcValue() {
		this.value = 123; // formula
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

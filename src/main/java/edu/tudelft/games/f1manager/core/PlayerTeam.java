package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.List;

public class PlayerTeam extends Team {

  /**
   * The budget a PlayerTeam has in Euro's. Is divisible by 100.   budget + (100 - (x % 100 ?: 100))
   */
  private int budget;

  /**
   * Is true if the team owns a software tester. If a team doesn't own a software-tester,
   * the chance for a crash increases significantly.
   */
  private boolean hasSoftwareTester;

  private Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .create();


  /**
   * Creates an object that represents the F1 Team of a player.
   *
   * @param drivers           list of drivers in the team
   * @param cars              list of cars owned by the team
   * @param strategist        strategist of the team
   * @param aerodynamicist    aerodynamicist of the team
   * @param mechanic          mechanic of the team
   * @param id                id of the team
   * @param hasSoftwareTester is true if the team owns a software tester
   */
  public PlayerTeam(List<Driver> drivers, List<Car> cars,
                    Strategist strategist, Aerodynamicist aerodynamicist,
                    Mechanic mechanic, int id, int budget, boolean hasSoftwareTester) {
    super(drivers, cars, strategist, aerodynamicist, mechanic, id);
    this.budget = budget;
    this.hasSoftwareTester = hasSoftwareTester;
  }

  /**
   * Buys the driver if the team has enough budget.
   *
   * @param driver the driver that gets bought
   */
  public void buyDriver(Driver driver) {

    if (this.getBudget() >= driver.getValue()) {
      driver.setTeamId(this.getId());
      this.getDriverList().add(driver);
      this.setBudget(this.getBudget() - driver.getValue());
    }
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public boolean getHasSoftwareTester() {
    return hasSoftwareTester;
  }

  public void setHasSoftwareTester(boolean hasSoftwareTester) {
    this.hasSoftwareTester = hasSoftwareTester;

  }

  public PlayerTeam read() {

    String fileName = "playerteams.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + fileName);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, PlayerTeam.class);

  }

  public void getJSON() {

    PlayerTeam newteam = read();
    this.budget = newteam.getBudget();
    this.hasSoftwareTester = newteam.getHasSoftwareTester();
    super.setAerodynamicist(newteam.getAerodynamicist());
    super.setCarList(newteam.getCarList());
    super.setDriverList(newteam.getDriverList());
    super.setMechanic(newteam.getMechanic());
    super.setStrategist(newteam.getStrategist());

  }


  public void updateJSON() throws IOException {

    String fileName = "playerteams.json";

    PlayerTeam team = new PlayerTeam(super.getDriverList(), super.getCarList(), super.getStrategist(), super.getAerodynamicist(), super.getMechanic(),
      super.getId(), this.budget, this.hasSoftwareTester);

    String json = gson.toJson(team);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }
}


package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.List;

public class PlayerTeam extends Team {

  /**
   * The budget a PlayerTeam has in Euro's. Is divisible by 100.   budget + (100 - (x % 100 ?: 100))
   */

  @Expose
  private int budget;

  /**
   * Is true if the team owns a software tester. If a team doesn't own a software-tester,
   * the chance for a crash increases significantly.
   */

  @Expose
  private boolean softwareTester;

  private static Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .excludeFieldsWithoutExposeAnnotation()
    .create();

  /**
   * Creates an object that represents the F1 Team of a player.
   *
   * @param drivers        list of drivers in the team
   * @param cars           list of cars owned by the team
   * @param strategist     strategist of the team
   * @param aerodynamicist aerodynamicist of the team
   * @param mechanic       mechanic of the team
   * @param points         points the team has
   * @param id             id of the team
   * @param budget         budget of the team
   * @param softwareTester true if the team has a software tester
   */
  public PlayerTeam(List<Driver> drivers, List<Car> cars,
                    Strategist strategist, Aerodynamicist aerodynamicist,
                    Mechanic mechanic, int points, int id, int budget, boolean softwareTester) {
    super(drivers, cars, strategist, aerodynamicist, mechanic, points, id);
    this.budget = budget;
    this.softwareTester = softwareTester;
  }

  /**
   * transfers the driver to this PlayerTeam if the PlayerTeam had enough budget
   * if succesful the value of the driver is removed from the PLayerTeam budget.
   *
   * @param driver - Driver
   */
  public void buyDriver(Driver driver) {
    for (int i = 0; i < this.getDriverList().size(); i++) {
      if (driver == this.getDriverList().get(i)) {
        return;
      }
    }
    if (this.getBudget() >= driver.getValue()) {
      driver.transfer(this);
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

  /**
   * Reads in playerteam.json returns a playerteam
   * object if the file is in the appropriate format.
   *
   * @return a playerteam
   */
  public static PlayerTeam read(String filename) {


    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, PlayerTeam.class);

  }


  /**
   * Write the playerteam to playerteam.json.
   *
   * @param playerteam the playerteam that gets written
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {

        String json = gson.toJson(this);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }
}


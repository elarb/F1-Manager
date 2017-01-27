package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javafx.scene.image.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.lang.reflect.Modifier;
import java.util.List;

public class PlayerTeam extends Team {

  private static Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
      .serializeNulls()
      .create();

  /**
   * The budget a PlayerTeam has in Euro's. Is divisible by 100.   budget + (100 - (x % 100 ?: 100))
   */
  private int budget;

  /**
   * Is true if the team owns a software tester. If a team doesn't own a software-tester,
   * the chance for a crash increases significantly.
   */
  private boolean softwareTester;


  /**
   * Creates a playerteam.
   *
   * @param name           name of the playerteam
   * @param id             id of the playerteam (usually 1)
   * @param strategist     strategist of the playerteam
   * @param aerodynamicist aerodynamicist of the playerteam
   * @param mechanic       mechanic of the playerteam
   * @param driverList     driverlist of the playerteam
   * @param car            car developed by the team
   * @param points         amount of points the team ah
   * @param budget         budget the team has
   * @param softwareTester true if team has a softwaretester
   */
  @SuppressWarnings("CheckStyle")
  public PlayerTeam(String name, int id, Strategist strategist, Aerodynamicist aerodynamicist,
                    Mechanic mechanic, List<Driver> driverList, Car car,
                    int points, int budget, boolean softwareTester) {
    super(name, id, strategist, aerodynamicist, mechanic, driverList, car, points);
    this.budget = budget;
    this.softwareTester = softwareTester;
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
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {
    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(gson.toJson(this).getBytes());
    outputStream.close();
  }

  public boolean enoughDrivers() {
    return this.getDriverList().size() >= 2;
  }

  public void addBudget(int num) {
    this.budget += num;
  }

  public void lowerBudget(int num) {
    this.budget -= num;
  }

  public Image getFirstDriverImg() {
    return new Image("/img/Drivers/" + super.getDriverList().get(0).getName() + ".png");
  }

  public Image getSecondDriverImg() {
    return new Image("/img/Drivers/" + super.getDriverList().get(1).getName() + ".png");
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public boolean hasSoftwareTester() {
    return softwareTester;
  }

  public void setSoftwareTester(boolean softwareTester) {
    this.softwareTester = softwareTester;
  }

  public static Gson getGson() {
    return gson;
  }

  public static void setGson(Gson gson) {
    PlayerTeam.gson = gson;
  }


}


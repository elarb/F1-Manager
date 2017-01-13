package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
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

    String json = gson.toJson(this);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public static Gson getGson() {
    return gson;
  }

  public static void setGson(Gson gson) {
    PlayerTeam.gson = gson;
  }
}


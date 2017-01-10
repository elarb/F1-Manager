package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
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
  private boolean softwareTester;

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
   * Reads in playerteam.json returns a playerteam
   * object if the file is in the appropriate format.
   *
   * @return a playerteam
   */
  public static PlayerTeam read(String filename) {
//    String filename = "playerteam.json";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    PlayerTeam playerteam = gson.fromJson(reader, PlayerTeam.class);

    return playerteam;

  }


  /**
   * Write the playerteam to playerteam.json.
   *
   * @param playerteam the playerteam that gets written
   * @throws IOException when the file doesn't exist
   */
  public static void write(PlayerTeam playerteam, String filename) throws IOException {
//    String filename = "playerteam.json";
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(playerteam);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
    System.out.println("This has been written to playerteam.json : " + json);
  }

  @Override
  public String toString() {
    return "PlayerTeam{"
      + "budget=" + budget
      + ", softwareTester="
      + softwareTester + "} " + super.toString();
  }

  public int getBudget() {
    return budget;
  }

  public void setBudget(int budget) {
    this.budget = budget;
  }

  public boolean getSoftwareTester() {
    return softwareTester;
  }

  public void setSoftwareTester(boolean softwareTester) {
    this.softwareTester = softwareTester;

  }
}


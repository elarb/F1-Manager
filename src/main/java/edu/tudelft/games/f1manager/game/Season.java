package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tudelft.games.f1manager.core.Team;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


public class Season {

  /**
   * The current race in the season.
   */
  private int currentRace;
  private Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .create();

  /**
   * List of drivers sorted by points.
   */
  private ArrayList<Team> constructorStandings;

  /**
   * Creates an object that represents a F1 season.
   *
   * @param currentRace          current race in the season
   * @param constructorStandings list of drivers sorted by points
   */
  public Season(int currentRace, ArrayList<Team> constructorStandings) {
    this.currentRace = currentRace;
    this.constructorStandings = constructorStandings;
  }

  public int getCurrentRace() {
    return currentRace;
  }

  public void setCurrentRace(int currentRace) {
    this.currentRace = currentRace;
  }

  public ArrayList<Team> getConstructorStandings() {
    return constructorStandings;
  }

  public void setConstructorStandings(ArrayList<Team> constructorStandings) {
    this.constructorStandings = constructorStandings;
  }


  /**
   * Reads a season from "season.json".
   *
   * @return a season
   */
  public Season read() {

    String fileName = "season.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + fileName);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, Season.class);

  }

  /**
   * Uses read() to initialize a season object.
   */
  public void getJson() {

    Season newseason = read();
    this.constructorStandings = newseason.getConstructorStandings();
    this.currentRace = newseason.getCurrentRace();


  }

  /**
   * Updates the "season.json" file with the changed fields
   *
   * @throws IOException throws an IO Exception
   */
  public void updateJson() throws IOException {

    String fileName = "season.json";

    Season season = new Season(this.getCurrentRace(), this.getConstructorStandings());

    String json = gson.toJson(season);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}

package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tudelft.games.f1manager.core.Team;

import javax.print.DocFlavor;
import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


public class Season {

  /**
   * The current race in the season.
   */
  private int currentRace;
  private static Gson gson = new GsonBuilder()
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
  public static Season read(String filename) {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, Season.class);

  }


  /**
   * Updates the "season.json" file with the changed fields
   *
   * @throws IOException throws an IO Exception
   */
  public void write(String filename) throws IOException {

    Season season = new Season(this.getCurrentRace(), this.getConstructorStandings());

    String json = gson.toJson(season);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }

}

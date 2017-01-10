package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.tudelft.games.f1manager.core.Team;

import java.io.*;
import java.util.ArrayList;


public class Season {

  /**
   * The current race in the season.
   */
  private int currentRace;

  /**
   * A list of races.
   */
  private ArrayList<Race> races;


  /**
   * List of teams sorted by points.
   */
  private ArrayList<Team> constructorStandings;

  /**
   * Creates an object that represents a F1 season.
   *
   * @param currentRace current race in the season
   * @param races       the races in a season
   * @param teams       list of teams
   */
  public Season(int currentRace, ArrayList<Race> races, ArrayList<Team> teams) {
    this.currentRace = currentRace;
    this.races = races;
    this.constructorStandings = teams;
  }

  /**
   * Returns the SVG Path of a circuit in the form of a String.
   *
   * @param raceName name of the circuit
   * @return the SVG Path of the circuit
   */
  public String getPathByRaceName(String raceName) {

    String res = null;
    for (Race race : this.getRaces()) {
      if (race.getName().equals(raceName)) {
        res = race.getCircuit().getPath();
      }
    }
    return res;
  }

  /**
   * Reads in season.json returns a season
   * object if the file is in the appropriate format.
   *
   * @return a season
   */
  public static Season read(String filename) {
//    String filename = "season.json";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    Season season = gson.fromJson(reader, Season.class);

    return season;

  }


  /**
   * Write the season to season.json.
   *
   * @param season the season that gets written
   * @throws IOException when the file doesn't exist
   */
  public static void write(Season season, String filename) throws IOException {
//    String filename = "season.json";
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(season);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
    System.out.println("This has been written to season.json : " + json);
  }

  public int getCurrentRace() {
    return currentRace;
  }

  public void setCurrentRace(int currentRace) {
    this.currentRace = currentRace;
  }

  public ArrayList<Race> getRaces() {
    return races;
  }

  public void setRaces(ArrayList<Race> races) {
    this.races = races;
  }

  public ArrayList<Team> getConstructorStandings() {
    return constructorStandings;
  }

  public void setConstructorStandings(ArrayList<Team> constructorStandings) {
    this.constructorStandings = constructorStandings;
  }

}

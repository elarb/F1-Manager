package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import com.google.gson.annotations.Expose;

import edu.tudelft.games.f1manager.core.Team;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.ArrayList;


public class Season {

  private static Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
      .excludeFieldsWithoutExposeAnnotation()
      .serializeNulls()
      .create();

  /**
   * The current race in the season.
   */
  @Expose
  private int currentRace;


  /**
   * A list of races.
   */
  @Expose
  private ArrayList<Race> races;

  private ArrayList<Team> standings;

  /**
   * Creates an object that represents a F1 season.
   *
   * @param currentRace current race in the season
   * @param races       the races in a season
   */
  public Season(int currentRace, ArrayList<Race> races) {
    this.currentRace = currentRace;
    this.races = races;
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

  public Race getCurrentRaceInstance() {
    return this.getRaces().get(this.currentRace);
  }

  public Race getPastRaceInstance() {
    return this.getRaces().get(this.currentRace - 1);
  }

  /**
   * Adds a race to the list of races of the current season.
   *
   * @param race the race that gets added
   */
  public void addRace(Race race) {
    this.races.add(race);
  }

  /**
   * Reads in season.json returns a season
   * object if the file is in the appropriate format.
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
    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(gson.toJson(this).getBytes());
    outputStream.close();
  }

  /**
   * Changes the current race to the next in queue.
   */
  public void nextRace() {
    this.currentRace++;
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

  public ArrayList<Team> getStandings() {
    return standings;
  }

  public void setStandings(ArrayList<Team> standings) {
    this.standings = standings;
  }

}

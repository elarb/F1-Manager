package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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
   * A list of races.
   */
  private ArrayList<Race> races;


  /**
   * List of teams sorted by points.
   */
//  private AbstractMap.SimpleEntry<Integer, Integer> constructorStandings;

  /**
   * Creates an object that represents a F1 season.
   *
   * @param currentRace current race in the season
   * @param races       the races in a season
   */
  public Season(int currentRace, ArrayList<Race> races) {
    this.currentRace = currentRace;
    this.races = races;
//    this.constructorStandings = teams;
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

  /**
   * Adds a race to the list of races of the current season
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

//    Season season = new Season(this.getCurrentRace(), this.getConstructorStandings());

    String json = gson.toJson(this);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }

  /**
   * Changes the current race to the next in queue
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


}

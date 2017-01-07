package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class RacesList {

  /**
   * A list of races.
   */
  private ArrayList<Race> races;

  /**
   * Creates an object that represents a list of races.
   *
   * @param races a list of races
   */
  public RacesList(ArrayList<Race> races) {
    this.races = races;
  }

  /**
   * Adds a race to the list of races.
   *
   * @param race the race that gets added
   */
  public void addRace(Race race) {


//    Season.getRaceList.get(currentRace).MethodToAddDriverResults();
//    currentRace++;

    this.races.add(race);

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
   * Reads a json file and creates a raceslist object from it.
   *
   * @param fileName the name of the json file (has to be in resources)
   * @return A Raceslist
   */
  public static RacesList fromJson(String fileName) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(fileName);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    RacesList races = gson.fromJson(reader, RacesList.class);
    return races;
  }

  public ArrayList<Race> getRaces() {
    return races;
  }

  public void setRaces(ArrayList<Race> races) {
    this.races = races;
  }
}

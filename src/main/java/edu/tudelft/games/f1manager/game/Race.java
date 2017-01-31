package edu.tudelft.games.f1manager.game;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;


public class Race {

  /**
   * Name of the race.
   */
  @Expose
  private String name;

  /**
   * Circuit where the race takes place.
   */
  @Expose
  private Circuit circuit;

  /**
   * List of results of the race.
   */
  @Expose
  private ArrayList<DriverResult> results;

  /**
   * The distance of the race in km.
   */
  @Expose
  private double distance;

  /**
   * Number of laps of the race.
   */
  @Expose
  private int laps;

  /**
   * Creates an object that represents a F1 Race.
   *
   * @param name     name of the race
   * @param circuit  circuit where the race takes place
   * @param results  results of the race
   * @param distance distance of the race in km
   * @param laps     number of laps in the race
   */
  public Race(String name, Circuit circuit, double distance,
              ArrayList<DriverResult> results, int laps) {
    this.name = name;
    this.circuit = circuit;
    this.results = results;
    this.distance = distance;
    this.laps = laps;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Circuit getCircuit() {
    return circuit;
  }

  public void setCircuit(Circuit circuit) {
    this.circuit = circuit;
  }

  public ArrayList<DriverResult> getResults() {
    return results;
  }

  public void setResults(ArrayList<DriverResult> results) {
    this.results = results;
  }

  public double getDistance() {
    return distance;
  }

  public void setDistance(double distance) {
    this.distance = distance;
  }

  public int getLaps() {
    return laps;
  }

  public void setLaps(int laps) {
    this.laps = laps;
  }
}

package edu.tudelft.games.f1manager.game;

import com.google.gson.annotations.Expose;

public class Circuit {

  /**
   * Base time of the circuit, based on results of season 2016.
   */
  @Expose
  private double raceTimeBase;

  /**
   * Number of turns in the Circuit.
   */
  @Expose
  private int turns;

  /**
   * The total length of the circuit in km.
   */
  @Expose
  private double length;

  /**
   * Country of the Circuit.
   */
  @Expose
  private String country;

  /**
   * SVG Path of the Circuit.
   */
  @Expose
  private String path;

  /**
   * Creates an object that represents a Circuit.
   *
   * @param raceTimeBase base time of the circuit
   * @param turns        number of turns in the circuit
   * @param length       length of the circuit
   * @param country      country of the circuit
   * @param path         svg path of the circuit
   */
  public Circuit(double raceTimeBase, int turns, double length, String country, String path) {
    this.raceTimeBase = raceTimeBase;
    this.turns = turns;
    this.length = length;
    this.country = country;
    this.path = path;
  }


  /**Returns the Base Time of the race.
   * @return a double
   */
  public double getRaceTimeBase() {
    return raceTimeBase;
  }

  public void setRaceTimeBase(double raceTimeBase) {
    this.raceTimeBase = raceTimeBase;
  }

  public int getTurns() {
    return turns;
  }

  public void setTurns(int turns) {
    this.turns = turns;
  }

  public double getLength() {
    return length;
  }

  public void setLength(double length) {
    this.length = length;
  }

  public String getCountry() {
    return country;
  }

  public void setCountry(String country) {
    this.country = country;
  }

  public String getPath() {
    return path;
  }

  public void setPath(String path) {
    this.path = path;
  }
}

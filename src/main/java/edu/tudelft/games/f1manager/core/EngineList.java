package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

public class EngineList {

  ArrayList<Engine> enginelist;

  /**
   * Creates an object that represents a list of engines.
   */
  public EngineList() {
    this.enginelist = new ArrayList<>();
  }

  public void addEngine(Engine engine) {
    enginelist.add(engine);
  }

  public ArrayList<Engine> getEnginelist() {
    return enginelist;
  }

  public void setEnginelist(ArrayList<Engine> enginelist) {
    this.enginelist = enginelist;
  }
}

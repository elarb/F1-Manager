package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class EngineList {

  private static Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
      .serializeNulls()
      .create();

  private ArrayList<Engine> enginelist;

  /**
   * Creates an object that represents a list of engines.
   */
  public EngineList() {
    this.enginelist = new ArrayList<>();
  }

  /**
   * Reads in engines.json returns a engines
   * object if the file is in the appropriate format.
   *
   * @return an enginelist
   */
  public static EngineList read(String filename) {
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, EngineList.class);
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

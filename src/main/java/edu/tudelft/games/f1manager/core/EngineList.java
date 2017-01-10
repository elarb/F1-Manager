package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

public class EngineList {

  ArrayList<Engine> enginelist;

  /**
   * Creates an object that represents a list of engines.
   */
  public EngineList() {
    this.enginelist = new ArrayList<>();
  }

  /**
   * Reads in drivers.json returns a enginelist
   * object if the file is in the appropriate format.
   *
   * @return a enginelist
   */
  public static EngineList read(String filename) {
//    String filename = "engines.json";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    EngineList enginelist = gson.fromJson(reader, EngineList.class);

    return enginelist;

  }


  /**
   * Write the enginelist to engines.json.
   *
   * @param enginelist the enginelist that gets written
   * @throws IOException when the file doesn't exist
   */
  public static void write(EngineList enginelist, String filename) throws IOException {
//    String filename = "engines.json";
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(enginelist);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
    System.out.println("This has been written to the file: " + json);
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

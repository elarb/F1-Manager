package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class GameEvents {

  /**
   * List of game-events
   */
  private ArrayList<GameEvent> events;

  private static Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .create();

  public GameEvents() {
    this.events = new ArrayList<>();
  }

  public void addEvent(GameEvent event) {
    this.events.add(event);
  }

  /**
   * Reads in events.json returns a list of game-events
   * object if the file is in the appropriate format.
   *
   * @return a game-events-list
   */
  public static GameEvents read(String filename) {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return gson.fromJson(reader, GameEvents.class);

  }


  /**
   * Write the events to events.json.
   *
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {

    String json = gson.toJson(this);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }

  public ArrayList<GameEvent> getEvents() {
    return events;
  }

  public void setEvents(ArrayList<GameEvent> events) {
    this.events = events;
  }

  public static Gson getGson() {
    return gson;
  }

  public static void setGson(Gson gson) {
    GameEvents.gson = gson;
  }
}

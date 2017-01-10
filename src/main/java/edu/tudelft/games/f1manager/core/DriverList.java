package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * This class represents a list of Drivers.
 */
public class DriverList {

  private static Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .registerTypeAdapter(Team.class, new TeamInstanceCreator())
    .excludeFieldsWithoutExposeAnnotation()
    .create();


  /**
   * A list of Drivers.
   */
  private ArrayList<Driver> driverList;

  /**
   * Creates an Object that represents a list of Drivers.
   *
   * @param driverList A list of drivers
   */
  public DriverList(ArrayList<Driver> driverList) {
    this.driverList = driverList;
  }

  public ArrayList<Driver> getDriverList() {
    return driverList;
  }

  public void setDriverList(ArrayList<Driver> driverList) {
    this.driverList = driverList;
  }

  /**
   * Reads a playerteam from "playerteam.json".
   *
   * @return a playerteam
   */
  public static DriverList read(String filename) {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return new DriverList(gson.fromJson(reader, new TypeToken<ArrayList<Driver>>(){}.getType()));

  }


  /**
   * Updates the "drivers.json" file with the changes
   *
   * @throws IOException throws an IO Exception
   */
  public void write(String filename) throws IOException {

    String json = gson.toJson(this.driverList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();

    System.out.println("Succesfully wrote to file");
    System.out.println(json);

  }

}

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

  private ArrayList<Driver> drivers;


  public DriverList() {
    this.drivers = new ArrayList<>();
  }

  /**
   * Adds the driver to the list of drivers.
   *
   * @param driver the driver that gets added
   */
  public void add(Driver driver) {
    this.drivers.add(driver);
  }

  /**
   * Reads in drivers.json returns a driverlist
   * object if the file is in the appropriate format.
   *
   * @return a driverlist
   */
  public static DriverList read(String filename) {

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream("JSON/" + filename);
    Reader reader = new InputStreamReader(is);

    return new DriverList(gson.fromJson(reader, new TypeToken<ArrayList<Driver>>(){}.getType()));

  }


  /**
   * Write the driverlist to drivers.json.
   *
   * @param driverlist the driverlist that gets written
   * @throws IOException when the file doesn't exist
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

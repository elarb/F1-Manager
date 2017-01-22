package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import java.lang.reflect.Modifier;
import java.util.ArrayList;

/**
 * This class represents a list of Drivers.
 */
public class DriverList {

  private static Gson gson = new GsonBuilder()
      .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
      .serializeNulls()
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

    return gson.fromJson(reader, DriverList.class);
  }

  /**
   * Write the driverlist to drivers.json.
   *
   * @throws IOException when the file doesn't exist
   */
  public void write(String filename) throws IOException {
    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(gson.toJson(this).getBytes());
    outputStream.close();
  }

  public ArrayList<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(ArrayList<Driver> drivers) {
    this.drivers = drivers;
  }
}

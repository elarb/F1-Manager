package edu.tudelft.games.f1manager.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

/**
 * This class represents a list of Drivers.
 */
public class DriverList {


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
  public static DriverList read() {
    String filename = "drivers.json";
    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(filename);
    Reader reader = new InputStreamReader(is);
    Gson gson = new GsonBuilder().create();
    DriverList driverList = gson.fromJson(reader, DriverList.class);

    return driverList;

  }


  /**
   * Write the driverlist to drivers.json.
   *
   * @param driverlist the driverlist that gets written
   * @throws IOException when the file doesn't exist
   */
  public static void write(DriverList driverlist) throws IOException {
    String filename = "drivers.json";
    Gson gson = new GsonBuilder().create();
    String json = gson.toJson(driverlist);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + filename);
    outputStream.write(json.getBytes());
    outputStream.close();
    System.out.println("This has been written to the file: " + json);
  }

  public ArrayList<Driver> getDrivers() {
    return drivers;
  }

  public void setDrivers(ArrayList<Driver> drivers) {
    this.drivers = drivers;
  }
}

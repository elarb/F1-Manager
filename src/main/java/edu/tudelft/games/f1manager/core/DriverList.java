package edu.tudelft.games.f1manager.core;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.*;
import java.util.ArrayList;

/**
 * This class represents a list of Drivers.
 */
public class DriverList {

  Gson gson = new Gson();

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

  public DriverList read() {

    String fileName = "drivers.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(fileName);
    Reader reader = new InputStreamReader(is);
    DriverList driverlist = gson.fromJson(reader, DriverList.class);

    return driverlist;

  }

  public void getJSON() {

    DriverList newdriverlist = read();
    this.driverList = newdriverlist.getDriverList();

  }


  public void updateJSON() throws IOException {

    String fileName = "drivers.json";

    String json = gson.toJson(this.driverList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}

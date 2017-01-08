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

  private Gson gson = new GsonBuilder()
    .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
    .serializeNulls()
    .registerTypeAdapter(Team.class, new TeamInstanceCreator())
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

  public DriverList read() {

    String fileName = "JSON/drivers.json";

    ClassLoader classloader = Thread.currentThread().getContextClassLoader();
    InputStream is = classloader.getResourceAsStream(fileName);
    Reader reader = new InputStreamReader(is);

    return new DriverList(gson.fromJson(reader, new TypeToken<ArrayList<Driver>>(){}.getType()));

  }

  public void getJSON() {

    DriverList newdriverlist = read();
    this.driverList = newdriverlist.getDriverList();

  }


  public void updateJSON() throws IOException {

    String fileName = "drivers.json";

    String json = gson.toJson(this.driverList);

    FileOutputStream outputStream = new FileOutputStream("src/main/resources/JSON/" + fileName);
    outputStream.write(json.getBytes());
    outputStream.close();

  }

}

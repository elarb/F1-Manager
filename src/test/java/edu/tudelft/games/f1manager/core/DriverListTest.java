package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.DriverResult;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;


public class DriverListTest {

  public DriverResult result;


  @Before
  public void setUp() throws Exception {

    Driver driver1 = new Driver("Mighty John", 4);
    Driver driver2 = new Driver("Pieter", 5);

    DriverList driverList = new DriverList();
    driverList.add(driver1);
    driverList.add(driver2);

    result = new DriverResult(driver1, 6000);

    driverList.write("TESTS/drivers.json");
  }

  @Test
  public void read_and_write() throws IOException, InterruptedException {
    DriverList driverList = DriverList.read("TESTS/drivers.json");
    assertEquals("Mighty John", driverList.getDrivers().get(0).getName());

  }

  @Test
  public void gettimestringtest() {

    assertEquals("02:40:00", result.getTimeString());

  }


}
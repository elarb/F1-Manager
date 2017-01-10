package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class DriverListTest {

  Driver driver1;
  Driver driver2;
  ArrayList<Driver> driverArrayList;
  DriverList driverList;




  @Before
  public void setUp() throws Exception {

    driver1 = new Driver("Mighty Join", mock(Team.class));
    driver2 = new Driver("Pieter", mock(Team.class));
    driverArrayList = new ArrayList<Driver>();
    driverArrayList.add(driver1);
    driverArrayList.add(driver2);

    driverList = new DriverList(driverArrayList);

  }

  @Test
  public void readtest() throws IOException {

    DriverList driverList = DriverList.read("TESTS/testdrivers.json");
    assertEquals("Mighty Join", driverList.getDriverList().get(0).getName());

  }

  @Test
  public void writetest() throws IOException {

    driverList.write("TESTS/testdrivers.json");



  }

}
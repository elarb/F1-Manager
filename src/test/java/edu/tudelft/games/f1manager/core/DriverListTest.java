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
  PlayerTeam team;
  ArrayList<Driver> driverArrayList;
  DriverList driverList;


  @Before
  public void setUp() throws Exception {
    team = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      100, true);

    driver1 = new Driver("test testson", team);
    driver2 = new Driver("Tester", team);
    driverArrayList = new ArrayList<Driver>();
    driverArrayList.add(driver1);
    driverArrayList.add(driver2);


    driverList = new DriverList(driverArrayList);

  }


  @Test
  public void updatejsontest() throws IOException {

    driverList.updateJson();


  }

  @Test
  public void getjsontest() throws IOException {

    driverList.getJson();
    assertTrue(driverList.getDriverList().size() > 0);

  }

}
package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class PlayerTeamTest {

  Driver driver;
  PlayerTeam playerTeam;
  PlayerTeam playerTeam2;

  @Before
  public void setUp() throws Exception {
    playerTeam = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      200, true);

    playerTeam2 = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      200, true);

    driver = new Driver("test testson", playerTeam);
    driver.setValue(100);
    playerTeam.getDriverList().add(driver);
  }


  @Test
  public void testBuyDriver_Works() throws Exception {
    playerTeam2.buyDriver(driver);

    assertEquals(playerTeam2.getBudget(), 100);
  }

  @Test
  public void testBuyDriver_HasDriver() throws Exception {
    playerTeam.buyDriver(driver);

    assertEquals(playerTeam.getBudget(), 200);
  }

  @Test
  public void testBuyDriver_NoBudget() throws Exception {
    driver.setValue(201);
    playerTeam2.buyDriver(driver);

    assertEquals(playerTeam2.getBudget(), 200);
  }

  @Test
  public void updatejsontest() throws IOException {

    playerTeam.updateJson();

  }

  @Test
  public void getjsontest() throws IOException {

    playerTeam.getJson();
    System.out.println(playerTeam.getStrategist().getStrategy());
    assertEquals(0, playerTeam.getStrategist().getRating());

  }
}
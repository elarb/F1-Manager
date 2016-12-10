package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

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
    playerTeam2.buyDriver(driver, 101);

    assertEquals(playerTeam2.getBudget(), 99);
  }

  @Test
  public void testBuyDriver_HasDriver() throws Exception {
    playerTeam.buyDriver(driver, 101);

    assertEquals(playerTeam.getBudget(), 200);
  }

  @Test
  public void testBuyDriver_NoBudget() throws Exception {
    playerTeam2.buyDriver(driver, 201);

    assertEquals(playerTeam2.getBudget(), 200);
  }

  @Test
  public void testBuyDriver_NotAccepted() throws Exception {
    playerTeam2.buyDriver(driver, 50);

    assertEquals(playerTeam2.getBudget(), 200);
  }


}
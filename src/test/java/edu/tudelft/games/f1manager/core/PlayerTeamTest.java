package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerTeamTest {

  private PlayerTeam playerTeam;
  private PlayerTeam playerTeam2;

  @Before
  public void setUp() throws Exception {
    playerTeam = new PlayerTeam(new ArrayList<Driver>(), mock(Car.class),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 2,
      200, true);

    playerTeam2 = new PlayerTeam(new ArrayList<Driver>(), mock(Car.class),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 5, 3,
      200, true);

    Driver driver = new Driver("test testson", 1);
    driver.setValue(100);
    playerTeam.getDriverList().add(driver);

    playerTeam.write("TESTS/playerteam.json");
  }


//  @Test
//  public void testBuyDriver_Works() throws Exception {
//    playerTeam2.buyDriver(driver);
//
//    assertEquals(playerTeam2.getBudget(), 100);
//  }
//
//  @Test
//  public void testBuyDriver_HasDriver() throws Exception {
//    playerTeam.buyDriver(driver);
//
//    assertEquals(playerTeam.getBudget(), 200);
//  }
//
//  @Test
//  public void testBuyDriver_NoBudget() throws Exception {
//    driver.setValue(201);
//    playerTeam2.buyDriver(driver);
//
//    assertEquals(playerTeam2.getBudget(), 200);
//  }

  @Test
  public void read_and_write() throws IOException {

    PlayerTeam playerTeam = PlayerTeam.read("TESTS/playerteam.json");
    assertEquals(0, playerTeam.getStrategist().getRating());

  }

}
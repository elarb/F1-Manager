package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class PlayerTeamTest {

  private PlayerTeam playerTeam;

  @Before
  public void setUp() throws Exception {

    Engine engine = new Engine("Ferrari", 821, 8.3, 9.8);
    double body = 6.8;
    Tyres tyres = new Tyres(9);
    Car car = new Car(engine, body, tyres);

    Aerodynamicist aerodynamicist = new Aerodynamicist(8);
    Strategist strategist = new Strategist(Strategist.Risk.MEDIUM, 80);
    Mechanic mechanic = new Mechanic(4);

    playerTeam = new PlayerTeam("PlayerTeam", 1, strategist,
      aerodynamicist, mechanic, new ArrayList<>(), car, 100, 300000, true);

    Driver driver = new Driver("test testson", 1);
    playerTeam.addDriver(driver);

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
    assertEquals(80, playerTeam.getStrategist().getRating());
  }

}
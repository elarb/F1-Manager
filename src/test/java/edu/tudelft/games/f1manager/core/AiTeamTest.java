package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AiTeamTest {

  AiTeam aiTeam;
  AiTeam aiTeam2;
  Driver driver;
  PlayerTeam playerTeam;

  @Before
  public void setUp() throws Exception {
    aiTeam = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));

    aiTeam2 = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));

    playerTeam = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      200, true);



  }

  @Test
  public void testBuyDriver_FromPlayerTeam() throws Exception {
    driver = new Driver("test testson", playerTeam);
    driver.setValue(100);

    aiTeam.buyDriver(driver);

    assertEquals(aiTeam, driver.getTeam());
    assertEquals(playerTeam.getBudget(), 300);
  }

  @Test
  public void testBuyDriver_FromAiTeam() throws Exception {
    driver = new Driver("test testson", aiTeam2);

    aiTeam.buyDriver(driver);

    assertEquals(aiTeam, driver.getTeam());
  }


}
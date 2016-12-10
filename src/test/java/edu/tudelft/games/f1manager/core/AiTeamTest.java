package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class AiTeamTest {

  AiTeam aiTeam;
  Driver driver;
  PlayerTeam playerTeam;

  @Before
  public void setUp() throws Exception {
    aiTeam = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));

    playerTeam = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      200, true);



  }

  @Test
  public void testBuyDriver_FromPlayerTeam() throws Exception {
    driver = new Driver("test testson", playerTeam);

    aiTeam.buyDriver(driver);

    assertEquals(aiTeam, driver.getTeam());
  }

}
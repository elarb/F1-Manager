package edu.tudelft.games.f1manager.core;

import org.junit.Before;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;

public class AiTeamTest {

  private AiTeam aiTeam;
  private Driver driver;
  private PlayerTeam playerTeam;

  @Before
  public void setUp() throws Exception {
    aiTeam = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 10, 1);

    playerTeam = new PlayerTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 100, 2,
      200, true);
  }

//  @Test
//  public void testBuyDriver_FromPlayerTeam() throws Exception {
//    driver = new Driver("test testson", 1);
//
//    aiTeam.buyDriver(driver);
//
//    assertEquals(aiTeam, driver.getTeam());
//  }


}
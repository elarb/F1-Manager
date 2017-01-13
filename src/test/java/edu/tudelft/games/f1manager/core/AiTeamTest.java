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
    AiTeam aiTeam = new AiTeam("Ai Team", 2, mock(Strategist.class),
      mock(Aerodynamicist.class), mock(Mechanic.class), new ArrayList<>(), mock(Car.class), 100);

    AiTeam aiTeam2 = new AiTeam("Ai Team2", 3, mock(Strategist.class),
      mock(Aerodynamicist.class), mock(Mechanic.class), new ArrayList<>(), mock(Car.class), 100);
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
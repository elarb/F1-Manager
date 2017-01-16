package edu.tudelft.games.f1manager.core;

import org.junit.Before;


public class DriverTest {

  private Driver driver;
  private PlayerTeam team;

  @Before
  public void setUp() throws Exception {

    driver = new Driver("Rob Robsson", 3);
    team.getDriverList().add(driver);
  }


//  @Test
//  public void testTransfer() throws Exception {
//    AiTeam team2 = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
//      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));
//    driver.transfer(team2);
//
//    assertEquals(team2, driver.getTeam());
//    assertEquals(driver, team2.getDriverList().get(0));
//    assertEquals(new ArrayList<Driver>(), team.getDriverList());
//  }

}
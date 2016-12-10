package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class DriverTest {

  Driver driver;
  PlayerTeam team;

  @Before
  public void setUp() throws Exception {
    team = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
      100, true);

    driver = new Driver("test testson", team);
    team.getDriverList().add(driver);
  }


  @Test
  public void testTransfer() throws Exception {
    AiTeam team2 = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));
    driver.transfer(team2);

    assertEquals(team2, driver.getTeam());
    assertEquals(driver, team2.getDriverList().get(0));
    assertEquals(new ArrayList<Driver>(), team.getDriverList());
  }

}
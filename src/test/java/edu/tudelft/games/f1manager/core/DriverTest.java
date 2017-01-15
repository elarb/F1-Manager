package edu.tudelft.games.f1manager.core;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class DriverTest {

  private Driver driver;

  @Before
  public void setUp() throws Exception {

    driver = new Driver("test testson", 3);
    driver.setRacecraft(8);
    driver.setSpeed(7);
    driver.setStrategyinsight(8);
    driver.determineValue();
  }

  @Test
  public void testSpeedUp() throws Exception {
	  double oldSpeed = driver.getSpeed();
	  driver.upgrade(0);
      assertTrue("Speed upgrades", oldSpeed < driver.getSpeed());
  }
  
  @Test
  public void testSpeedMaxUp() throws Exception {
	  driver.setSpeed(9.999999);
	  driver.upgrade(0);
      assertTrue("Speed doesn't pass 10", driver.getSpeed() == 10);
  }
  
  @Test
  public void testRacecraftUp() throws Exception {
	  double oldRacecraft = driver.getRacecraft();
	  driver.upgrade(1);
      assertTrue("Racecraft upgrades", oldRacecraft < driver.getRacecraft());
  }
  
  @Test
  public void testRacecraftMaxUp() throws Exception {
	  driver.setRacecraft(9.999999);
	  driver.upgrade(1);
      assertTrue("Racecraft doesn't pass 10", driver.getRacecraft() == 10);
  }
  
  @Test
  public void testStrategyUp() throws Exception {
	  double oldStrategy = driver.getStrategyinsight();
	  driver.upgrade(2);
      assertTrue("Strategy upgrades", oldStrategy < driver.getStrategyinsight());
  }
  
  @Test
  public void testStrategyMaxUp() throws Exception {
	  driver.setStrategyinsight(9.999999);
	  driver.upgrade(2);
      assertTrue("Strategy doesn't pass 10", driver.getStrategyinsight() == 10);
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

  @Test
  public void valuetest() {

    assertEquals(68544000 ,driver.getValue());

  }

}
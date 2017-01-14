package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MechanicTest {

  Mechanic mechanic;

  @Before
  public void setUp() throws Exception {
    mechanic = new Mechanic(8);
   // mechanic.updateUpgradePrice();
  }

  @Test
  public void testPrice() throws Exception {
    assertEquals("UpgradePrice is set correctly", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testUpperBound() throws Exception {
    Mechanic mechanic = new Mechanic(9);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testLowerBound() throws Exception {
    Mechanic mechanic = new Mechanic(1);
  }

//  @Test
//  public void testImprove() throws Exception {
//
//    PlayerTeam playerTeam = new PlayerTeam(new ArrayList<>(), mock(Car.class),
//      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 2,
//      2000000, true);
//    mechanic.improve(playerTeam);
//    mechanic.updateUpgradePrice();
//    assertEquals("Improve works properly", mechanic.getPitstopTime(), 7);
//    assertEquals("Improve adjusts cost", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE * 2);
//  }
//
//  @Test
//  public void testImproveFail() throws Exception {
//
//    PlayerTeam playerTeam = new PlayerTeam(new ArrayList<>(), mock(Car.class),
//      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 1,
//      1999999, true);
//    mechanic.improve(playerTeam);
//    mechanic.updateUpgradePrice();
//    assertEquals("Improve didn't do anything", mechanic.getPitstopTime(), 8);
//    assertEquals("Costs the same", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE);
//  }

}
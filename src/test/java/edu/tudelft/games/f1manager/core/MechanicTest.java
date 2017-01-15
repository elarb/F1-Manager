package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class MechanicTest {

  Mechanic mechanic;

  @Before
  public void setUp() throws Exception {
    mechanic = new Mechanic(8);
    mechanic.updateUpgradePrice();
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

  @Test
  public void testImprove() throws Exception {
    mechanic.upgrade();
    mechanic.updateUpgradePrice();
    assertEquals("Improve works properly", mechanic.getPitstopTime(), 7);
    assertEquals("Improve adjusts cost", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE * 2);
  }

  @Test
  public void testImproveFail() throws Exception {
    for (int i = 0; i < 7; i++) {
    	mechanic.upgrade();
    }
    mechanic.updateUpgradePrice();
    assertEquals("Improve didn't do anything the last time", mechanic.getPitstopTime(), 2);
  }

}
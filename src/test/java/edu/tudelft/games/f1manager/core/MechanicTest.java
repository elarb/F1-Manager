package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class MechanicTest {

  Mechanic mechanic;

  @Before
  public void setUp() throws Exception {
    mechanic = new Mechanic(8, mock(Tyres.class));
    mechanic.updateUpgradePrice();
  }

  @Test
  public void testPrice() throws Exception {
	  assertEquals("UpgradePrice is set correctly", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE);
  }
  
  @Test
  public void testImprove() throws Exception {
	  
	  PlayerTeam playerTeam = new PlayerTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
		      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class),
		      2000000, true);
	  mechanic.improve(playerTeam);
	  mechanic.updateUpgradePrice();
	  assertEquals("Improve works properly", mechanic.getPitstopTime(), 7);
	  assertEquals("Improve adjusts cost", mechanic.getUpgradePrice(), Constants.BASE_PITSTOP_UP_PRICE *2);
  }

}
package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.core.Driver;
import edu.tudelft.games.f1manager.game.Game;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DriverResultTest {
	
	private DriverResult driverResult1;
	private DriverResult driverResult2;
	private Driver driver1;
	private Driver driver2;
	
	@Before
	public void setUp() {
		driver1 = new Driver("Mighty John", 4);
		driver2 = new Driver("Pieter", 5);
		driverResult1 = new DriverResult(driver1, 1.5);
		driverResult2 = new DriverResult(driver2, 3);
	}
	
	@Test
	public void testToString() {
		assertEquals("DriverResult{driver=Driver{name='Mighty John', speed=0.0, racecraft=0.0, strategyinsight=0.0, value=0, teamId=4}, time=1.5}", driverResult1.toString());
	}
}
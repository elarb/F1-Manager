package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EngineTest {

  private Engine engine;

  @Before
  public void setUp() throws Exception {

    engine = new Engine("Ferrari", 1.2, 1.4, 1.3);

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void pricetest(){

    assertEquals(0, engine.getPrice(), 0);
  }

  @Test
  public void getbrandtest() {

    assertEquals("Ferrari", engine.getBrand());

  }

  @Test
  public void getpowertest() {

    assertEquals(1.2, engine.getPower(), 0);

  }

  @Test
  public void getdrivabilitytest() {

    assertEquals(1.4, engine.getDrivability(), 0);
  }

  @Test
  public void getfueltest() {

    assertEquals(1.3, engine.getFuelEfficiency(), 0);
  }

  @Test
  public void setbrandtest() {

    engine.setBrand("Mercedes");
    assertEquals("Mercedes", engine.getBrand());

  }

  @Test
  public void setpowertest() {

    engine.setPower(1.8);
    assertEquals(1.8, engine.getPower(), 0);

  }

  @Test
  public void setdrivabilitytest() {

    engine.setDrivability(1.7);
    assertEquals(1.7, engine.getDrivability(), 0);

  }

  @Test
  public void setfuelefficiencytest() {

    engine.setFuelEfficiency(1.5);
    assertEquals(1.5, engine.getFuelEfficiency(), 0);

  }

}
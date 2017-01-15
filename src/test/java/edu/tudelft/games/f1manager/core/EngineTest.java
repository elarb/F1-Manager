package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class EngineTest {

  private Engine engine;

  @Before
  public void setUp() throws Exception {
    
    engine = new Engine("Ferrari", 800, 5, 5);

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void test_engine_price() {

    assertEquals(22680000 ,engine.getPrice(), 0);

  }

}
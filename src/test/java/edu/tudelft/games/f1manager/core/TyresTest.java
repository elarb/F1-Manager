package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TyresTest {

  private Tyres tyre;

  @Before
  public void setUp() throws Exception {

    tyre = new Tyres(4);

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test

  public void getdurabilitytest() {

    assertEquals(80, tyre.getDurability());

  }

  @Test

  public void getgriptest() {
    assertEquals(20, tyre.getGrip());
  }

}
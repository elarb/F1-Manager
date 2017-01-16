package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;


public class AerodynamicistTest {

  private Aerodynamicist dummyAerodynamicist;

  @Before
  public void setUp() throws Exception {
    dummyAerodynamicist = new Aerodynamicist(75);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void test_upgrade_70s() {
    for (int i = 70; i < 79; i++) {
      dummyAerodynamicist.setExpertise(i);
      int expertise = dummyAerodynamicist.getExpertise();
      dummyAerodynamicist.upgrade(1);
      int newExpertise = dummyAerodynamicist.getExpertise();
      assertTrue(newExpertise > expertise && newExpertise <= (expertise + 5));
    }

  }

  @Test
  public void test_upgrade_80s() {
    for (int i = 80; i < 89; i++) {
      dummyAerodynamicist.setExpertise(i);
      int expertise = dummyAerodynamicist.getExpertise();
      dummyAerodynamicist.upgrade(1);
      int newExpertise = dummyAerodynamicist.getExpertise();
      assertTrue(newExpertise > expertise && newExpertise <= (expertise + 4));
    }

  }

  @Test
  public void test_upgrade_90s() {
    for (int i = 90; i < 94; i++) {
      dummyAerodynamicist.setExpertise(i);
      int expertise = dummyAerodynamicist.getExpertise();
      dummyAerodynamicist.upgrade(1);
      int newExpertise = dummyAerodynamicist.getExpertise();
      assertTrue(newExpertise > expertise && newExpertise <= (expertise + 3));
    }

  }

  @Test
  public void test_upgrade_95s() {

    for (int i = 96; i < 99; i++) {
      dummyAerodynamicist.setExpertise(i);
      int expertise = dummyAerodynamicist.getExpertise();
      dummyAerodynamicist.upgrade(1);
      int newExpertise = dummyAerodynamicist.getExpertise();
      assertTrue(expertise + 1 == newExpertise || newExpertise == 99);
    }

  }


}
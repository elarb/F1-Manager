package edu.tudelft.games.f1manager.core;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class StrategistTest {


  private Strategist dummyStrategist;


  @Before
  public void setUp() throws Exception {
    dummyStrategist = new Strategist(Strategist.Risk.MEDIUM, 75);

  }

  @Test(expected = IllegalArgumentException.class)
  public void test_rating_not_under_65() throws Exception {
    dummyStrategist = new Strategist(Strategist.Risk.HIGH, 55);
  }

  @Test(expected = IllegalArgumentException.class)
  public void test_rating_not_above_99() throws Exception {
    dummyStrategist = new Strategist(Strategist.Risk.HIGH, 102);
  }

  @Test
  public void test_upgrade_70s() {
    for (int i = 70; i < 79; i++) {
      dummyStrategist.setRating(i);
      int rating = dummyStrategist.getRating();
      dummyStrategist.upgrade();
      int newRating = dummyStrategist.getRating();
      assertTrue(newRating > rating && newRating <= (rating + 5));
    }

  }

  @Test
  public void test_upgrade_80s() {

    for (int i = 80; i <= 89; i++) {
      dummyStrategist.setRating(i);
      int rating = dummyStrategist.getRating();
      dummyStrategist.upgrade();
      int newRating = dummyStrategist.getRating();
      assertTrue(newRating > rating && newRating <= (rating + 4));
    }

  }

  @Test
  public void test_upgrade_90s() {

    for (int i = 90; i < 95; i++) {
      dummyStrategist.setRating(i);
      int rating = dummyStrategist.getRating();
      dummyStrategist.upgrade();
      int newRating = dummyStrategist.getRating();
      assertTrue(newRating > rating && newRating <= (rating + 3));
    }

  }

  @Test
  public void test_upgrade_95s() {

    for (int i = 96; i < 99; i++) {
      dummyStrategist.setRating(i);
      int rating = dummyStrategist.getRating();
      dummyStrategist.upgrade();
      int newRating = dummyStrategist.getRating();
      assertTrue(rating + 1 == newRating || newRating == 99);
    }

  }


  @Test
  public void test_does_not_upgrade_beyond_99() {
    dummyStrategist.setRating(99);
    dummyStrategist.upgrade();
    assertTrue(dummyStrategist.getRating() <= 99);
  }

  @Test
  public void test_is_max_rated() {
    dummyStrategist.setRating(99);
    assertTrue(dummyStrategist.isMaxRated());
    dummyStrategist.setRating(98);
    assertFalse(dummyStrategist.isMaxRated());
  }


  @Test
  public void test_increased_crash_chance_low_risk() {
    dummyStrategist.setStrategy(Strategist.Risk.LOW);

    assertEquals(dummyStrategist.getIncreasedCrashChance(), 3, 0.25);

    dummyStrategist.setRating(85);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), -3, 0.25);

    dummyStrategist.setRating(99);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), -8, 0.25);
  }

  @Test
  public void test_increased_crash_chance_medium_risk() {
    assertEquals(dummyStrategist.getIncreasedCrashChance(), 7, 0.25);

    dummyStrategist.setRating(85);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), 1, 0.25);

    dummyStrategist.setRating(99);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), -4, 0.25);
  }

  @Test
  public void test_increased_crash_chance_high_risk() {
    dummyStrategist.setStrategy(Strategist.Risk.HIGH);

    assertEquals(dummyStrategist.getIncreasedCrashChance(), 11, 0.25);

    dummyStrategist.setRating(85);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), 5, 0.25);

    dummyStrategist.setRating(99);
    assertEquals(dummyStrategist.getIncreasedCrashChance(), 0, 0.25);
  }

  @Test(expected = NullPointerException.class)
  public void test_increased_no_risk_set() {

    dummyStrategist.setStrategy(null);
    dummyStrategist.getIncreasedCrashChance();
  }


  @After
  public void tearDown() throws Exception {
    dummyStrategist = null;
    assertNull(dummyStrategist);
  }

}
package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.Season;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Timpelser on 07/01/2017.
 */
public class SeasonTest {

  private Season season;

  @Before
  public void setUp() throws Exception {

    season = new Season(12, new ArrayList<Team>());

  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void readtest() throws IOException {

    Season season = Season.read("TESTS/seasontest.json");
    assertEquals(12, season.getCurrentRace());
  }

  @Test
  public void writetest() throws IOException {

    season.write("TESTS/seasontest.json");

  }

}

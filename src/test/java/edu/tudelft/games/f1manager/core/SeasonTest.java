package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.Race;
import edu.tudelft.games.f1manager.game.Season;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SeasonTest {

  private Season season;

  @Before
  public void setUp() throws Exception {

    season = new Season(12, new ArrayList<Race>());
    season.write("TESTS/season.json");
  }


  @Test
  public void read_and_write() throws IOException {

    Season season = Season.read("TESTS/season.json");
    assertEquals(12, season.getCurrentRace());
  }


}

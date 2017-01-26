package edu.tudelft.games.f1manager.game;

import edu.tudelft.games.f1manager.game.Race;
import edu.tudelft.games.f1manager.game.Season;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class SeasonTest {

  private Season season;

  @Before
  public void setUp() throws Exception {
    season = new Season(0, new ArrayList<Race>());
    season.write("TESTS/season.json");
  }


  @Test
  public void read_and_write() throws IOException {
    Season season = Season.read("TESTS/season.json");
    assertEquals(0, season.getCurrentRace());
  }
  
  @Test
  public void testAddRace(){
	  Race race = Mockito.mock(Race.class);
	  int len = season.getRaces().size();
	  season.addRace(race);
	  assertEquals(len+1, season.getRaces().size());
  }
  
  @Test
  public void testCurrentRace(){
	  Race race = Mockito.mock(Race.class);
	  season.addRace(race);
	  assertEquals(season.getCurrentRaceInstance(), race);
  }
  
  @Test
  public void testPreviousRace(){
	  Race race = Mockito.mock(Race.class);
	  Race race2 = Mockito.mock(Race.class);
	  season.addRace(race);
	  season.addRace(race2);
	  season.setCurrentRace(1);
	  assertEquals(season.getPastRaceInstance(), race);
  }
  
  @Test
  public void testNextRae(){
	  season.nextRace();
	  assertEquals(1, season.getCurrentRace());
  }


}

package edu.tudelft.games.f1manager.game;


import edu.tudelft.games.f1manager.core.*;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class GameTest {

  private Game game;


  @Before
  public void setUp() {

    game = Game.newGame();

  }

  @Test
  public void newgametest() {

   assertEquals(33, game.getDrivers().size());

  }


  @Test
  public void savegametest() throws IOException {

    game.savegame("testsave");

  }

  @Test
  public void loadgametest() {

    Game game = Game.loadgame("testsave");

    assertEquals(2, game.getPlayerteam().getDriverList().size());

  }

  @Test
  public void addDriverResult1test() {

    game.addDriverResults(game.getPlayerteam());
    double result = game.getSeason().getCurrentRaceInstance().getResults().get(0).getTime();
    System.out.println(result);
    assertEquals(8117, result, result * 1.06);


  }

  @Test
  public void addDriverResult2test() {

    game.addDriverResults(game.getPlayerteam());
    double result = game.getSeason().getCurrentRaceInstance().getResults().get(1).getTime();
    assertEquals(8151, result, result * 1.06);

  }

  @Test
  public void race() {

    game.race();

  }

  @Test
  public void attributepointsandbudgettest_Aiteams() {

    game.race();
    assertTrue(game.getAiteams().get(0).getPoints() >= 1 && game.getAiteams().get(0).getPoints() <= 43);

  }

  @Test
  public void attributepointsandbudgettest_Playerteam() {
    game.race();
    assertEquals(0, game.getPlayerteam().getPoints(), 0);
  }

  @Test
  public void updatestandingstest() {
    game.race();
    ArrayList<Team> standings = game.getSeason().getStandings();
    assertEquals(11, standings.size());
  }

  @Test
  public void gameEvent(){
    game.race();
    assertTrue(game.getEvents().getEvents().size() > 0);
  }

  @Test
  public void addDriversID() throws IOException {

    game.race();
    game.savegame("drivers");

  }
}

package edu.tudelft.games.f1manager.core;


import edu.tudelft.games.f1manager.game.Game;
import edu.tudelft.games.f1manager.game.Race;
import edu.tudelft.games.f1manager.game.Season;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;

public class GameTest {

  private DriverList driverList;
  private AiTeamList aiTeamList;
  private PlayerTeam playerTeam;
  private Season season;
  private Game game;


  @Before
  public void setUp() {

//    Driver driver1 = new Driver("Mighty Join", 1);
//    Driver driver2 = new Driver("Pieter", 2);
//    driverList = new DriverList();
//    driverList.add(driver1);
//    driverList.add(driver2);
//
//    AiTeam aiTeam = new AiTeam("Ai Team", 2, mock(Strategist.class),
//      mock(Aerodynamicist.class), mock(Mechanic.class), new ArrayList<>(), mock(Car.class), 100);
//
//    AiTeam aiTeam2 = new AiTeam("Ai Team2", 3, mock(Strategist.class),
//      mock(Aerodynamicist.class), mock(Mechanic.class), new ArrayList<>(), mock(Car.class), 100);
//
//    aiTeamList = new AiTeamList();
//    aiTeamList.add(aiTeam);
//    aiTeamList.add(aiTeam2);
//
//    playerTeam = new PlayerTeam("PlayerTeam", 1, mock(Strategist.class),
//      mock(Aerodynamicist.class), mock(Mechanic.class), new ArrayList<>(), mock(Car.class), 100, 300000, true);

    season = new Season(0, new ArrayList<Race>());

    game = Game.newGame();

  }

  @Test
  public void newgametest() {

    assertEquals(20, game.getDrivers().size());

  }


  @Test
  public void savegametest() throws IOException {

    game.savegame("testsave");

  }

  @Test
  public void loadgametest() {

    Game game = Game.loadgame("testsave");
    assertEquals(0, game.getPlayerteam().getStrategist().getRating());

  }

}

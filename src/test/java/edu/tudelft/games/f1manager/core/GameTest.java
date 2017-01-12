package edu.tudelft.games.f1manager.core;


import edu.tudelft.games.f1manager.game.Race;
import edu.tudelft.games.f1manager.game.Season;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class GameTest {

  private DriverList driverList;
  private AiTeamList aiTeamList;
  private PlayerTeam playerTeam;
  private Season season;
  private Game game;


  @Before
  public void setUp(){

    Driver driver1 = new Driver("Mighty Join", 1);
    Driver driver2 = new Driver("Pieter", 2);
    driverList = new DriverList();
    driverList.add(driver1);
    driverList.add(driver2);

    AiTeam aiTeam = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 10, 1);

    AiTeam aiTeam2 = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 100, 2);

    aiTeamList = new AiTeamList();
    aiTeamList.add(aiTeam);
    aiTeamList.add(aiTeam2);

    playerTeam = new PlayerTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 2,
      200, true);
    season = new Season(0, new ArrayList<Race>());

    game = Game.newgame();

  }

  @Test
  public void newgametest(){

    assertEquals(2, game.getDriverList().getDrivers().size());

  }


  @Test
  public void savegametest() throws IOException {

    game.save("testsave");

  }

  @Test
  public void loadgametest(){

    Game game = Game.loadgame("testsave");
    assertEquals(0, game.getPlayerTeam().getStrategist().getRating());

  }

}

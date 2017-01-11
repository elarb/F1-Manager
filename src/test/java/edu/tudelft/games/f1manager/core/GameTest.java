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

  public DriverList driverList;
  public AiTeamList aiTeamList;
  public PlayerTeam playerTeam;
  public Season season;


  @Before
  public void setUp(){

    Driver driver1 = new Driver("Mighty Join", 1);
    Driver driver2 = new Driver("Pieter", 2);
    ArrayList<Driver> driverArrayList = new ArrayList<Driver>();
    driverArrayList.add(driver1);
    driverArrayList.add(driver2);
    driverList = new DriverList();
    driverList.setDrivers(driverArrayList);

    AiTeam aiTeam = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 10, 1);

    AiTeam aiTeam2 = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 100, 2);

    ArrayList<AiTeam> array = new ArrayList<AiTeam>();
    array.add(aiTeam);
    array.add(aiTeam2);
    aiTeamList = new AiTeamList();
    aiTeamList.setTeams(array);

    playerTeam = new PlayerTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 2,
      200, true);
    season = new Season(0, new ArrayList<Race>());

  }

  @Test
  public void newgametest(){

    Game.newgame();
    assertEquals(2, driverList.getDrivers().size());

  }


  @Test
  public void savegametest() throws IOException {

    Game.savegame("testsave", driverList, aiTeamList, playerTeam, season);

  }

  @Test
  public void loadgametest(){

    Game game = Game.loadgame("testsave");
    assertEquals(0, game.getPlayerTeam().getStrategist().getRating());

  }

}

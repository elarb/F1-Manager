package edu.tudelft.games.f1manager.game;


import edu.tudelft.games.f1manager.core.*;
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

    Engine engine = new Engine("Ferrari", 821, 8.3, 9.8);
    double body = 6.8;
    Tyres tyres = new Tyres(9);
    Car car = new Car(engine, body, tyres);

    Aerodynamicist aerodynamicist = new Aerodynamicist(8);
    Strategist strategist = new Strategist(Strategist.Risk.MEDIUM, 80);
    Mechanic mechanic = new Mechanic(4);

    playerTeam = new PlayerTeam("PlayerTeam", 1, strategist,
      aerodynamicist, mechanic, new ArrayList<>(), car, 100, 300000, true);

    Driver driver = new Driver("test testson", 1);
    driver.setValue(74450000);
    playerTeam.addDriver(driver);


    Driver driver2 = new Driver("test testy", 1);
    driver2.setValue(64450000);
    playerTeam.addDriver(driver2);



    season = new Season(0, new ArrayList<Race>());


    game = Game.newGame();
    game.setPlayerteam(playerTeam);

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

    assertEquals(2, game.getPlayerteam().getDriverList().size());

  }

  @Test
  public void addDriverResult1test() {

    Game game = Game.loadgame("testsave2");
    game.addDriverResults(playerTeam);
    assertEquals(4154.9 ,game.getSeason().getCurrentRaceInstance().getResults().get(0).getTime(), 0.1);

  }

  @Test
  public void addDriverResult2test() {

    Game game = Game.loadgame("testsave2");
    game.addDriverResults(playerTeam);
    assertEquals(4526.1 ,game.getSeason().getCurrentRaceInstance().getResults().get(1).getTime(), 0.1);

  }


}

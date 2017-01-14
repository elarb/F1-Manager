package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;


public class TeamTest {

  private Team playerTeam;

  @Before
  //TODO: mock doesn't work for equals
  public void setUp() throws Exception {

    //playerTeam = Game.newGame().getPlayerteam();

  }



//  @Test
//  public void testEquals_notEqual(){
//    playerTeam.getDriverList().add(new Driver("test testson", playerTeam));
//    assertFalse(playerTeam.equals(playerTeam2));
//  }

//  @Test
//  public void testEquals_EqualSame() {
//    assertTrue(playerTeam.equals(playerTeam));
//  }


//  @Test
//  public void testEquals_EqualDifferent() {
//    assertTrue(playerTeam.equals(playerTeam2));
//  }


  @Test
  public void driver1resultstest() {

    PlayerTeam playerTeam = PlayerTeam.read("Examplesave/playerteam.json");
    System.out.println("Driverresult is:" + playerTeam.getResultsDriver1());

  }


}
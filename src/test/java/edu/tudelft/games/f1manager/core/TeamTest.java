package edu.tudelft.games.f1manager.core;

import edu.tudelft.games.f1manager.game.Game;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;


public class TeamTest {

  private Team playerTeam;

  @Before
  //TODO: mock doesn't work for equals
  public void setUp() throws Exception {

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

    double result = playerTeam.getResultsDriver1();
    System.out.println(result);
    assertEquals(result, 7.76, 7.76*1.05);

  }

  @Test
  public void driver2resultstest() {

    double result = playerTeam.getResultsDriver2();
    System.out.println(result);
    assertEquals(result, 7.56, 7.56*1.05);

  }


}
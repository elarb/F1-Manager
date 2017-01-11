package edu.tudelft.games.f1manager.core;

import org.junit.Before;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;


public class TeamTest {

  private Team playerTeam;
  private Team playerTeam2;

  @Before
  //TODO: mock doesn't work for equals
  public void setUp() throws Exception {
    playerTeam = new PlayerTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 1, 4,
      200, true);

    playerTeam2 = playerTeam;
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

}
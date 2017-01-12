package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AiTeamListTest {


  private AiTeamList aiTeamList;

  @Before
  public void setUp() throws Exception {

    AiTeam aiTeam = new AiTeam(new ArrayList<Driver>(), mock(Car.class),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 0, 1);

    AiTeam aiTeam2 = new AiTeam(new ArrayList<Driver>(), mock(Car.class),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class), 10, 2);

    aiTeamList = new AiTeamList();

    aiTeamList.add(aiTeam);
    aiTeamList.add(aiTeam2);

    aiTeamList.write("TESTS/aiteams.json");
  }

  @Test
  public void read_and_write() throws IOException {

    AiTeamList aiTeamList = AiTeamList.read("TESTS/aiteams.json");
    assertEquals(0, aiTeamList.getTeams().get(0).getStrategist().getRating());


  }


}
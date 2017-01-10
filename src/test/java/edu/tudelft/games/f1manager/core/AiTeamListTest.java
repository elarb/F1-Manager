package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class AiTeamListTest {

  AiTeam aiTeam;
  AiTeam aiTeam2;
  ArrayList<AiTeam> array;
  AiTeamList aiTeamList;

  @Before
  public void setUp() throws Exception {

    aiTeam = new AiTeam(new ArrayList<Driver>(), new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));

    aiTeam2 = new AiTeam(new ArrayList<Driver>(),new ArrayList<Car>(),
      mock(Strategist.class), mock(Aerodynamicist.class), mock(Mechanic.class));

    array = new ArrayList<AiTeam>();

    array.add(aiTeam);
    array.add(aiTeam2);

    aiTeamList = new AiTeamList();

    aiTeamList.setAiTeamList(array);

  }

  @Test
  public void readtest() throws IOException {

    AiTeamList aiTeamList = AiTeamList.read("TESTS/aiTeamListtest.json");
    assertEquals(0, aiTeamList.getAiTeamList().get(0).getStrategist().getRating());


  }

  @Test
  public void write() throws IOException {

    aiTeamList.write("TESTS/aiTeamListtest.json");

  }

}
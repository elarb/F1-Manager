package edu.tudelft.games.f1manager.core;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
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
  public void updatejsontest() throws IOException {

    System.out.println(aiTeamList.getAiTeamList());

    aiTeamList.updateJSON();

  }

  @Test
  public void getjsontest() throws IOException {

    aiTeamList.getJSON();
    assertTrue(aiTeamList.getAiTeamList().size() > 0);



  }

}
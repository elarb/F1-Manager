package edu.tudelft.games.f1manager.game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.assertTrue;

public class GameEventsTest {

  private GameEvents events;

  @Before
  public void setUp() throws Exception {
    GameEvent event1 = new GameEvent("Driver has been transfered!", GameEvent.Type.TRANSFER);
    GameEvent event2 = new GameEvent("Mechanic has been upgraded to 95!", GameEvent.Type.UPGRADE);
    events = new GameEvents();
    events.addEvent(event1);
    events.addEvent(event2);
  }

  @After
  public void tearDown() throws Exception {

  }

  @Test
  public void read_and_write() throws IOException {
    events.write("TESTS/events.json");
    GameEvents events = GameEvents.read("TESTS/events.json");
    assertTrue(events.getEvents().get(0).getType() == GameEvent.Type.TRANSFER);
  }

  @Test
  public void addEvent() throws Exception {
    assertTrue(events.getEvents().size() != 0);
  }


}
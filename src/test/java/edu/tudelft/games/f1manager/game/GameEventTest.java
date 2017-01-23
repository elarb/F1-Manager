package edu.tudelft.games.f1manager.game;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GameEventTest {

  private static Gson gson = new GsonBuilder().serializeNulls().create();
  private GameEvent event;

  @Before
  public void setUp() throws Exception {
    String eventJson = "{\"currentDateTime\":\"22-01-2017 10:56\",\"message\":\"Driver has been transfered\",\"type\":\"1\",\"success\":false}";
    event = gson.fromJson(eventJson, GameEvent.class);
  }

  @Test
  public void test_json() {
    assertEquals(event.getType(), GameEvent.Type.TRANSFER);
    assertEquals(event.getMessage(),"Driver has been transfered");
    assertEquals(event.getCurrentDateTime(), "22-01-2017 10:56");
  }

}
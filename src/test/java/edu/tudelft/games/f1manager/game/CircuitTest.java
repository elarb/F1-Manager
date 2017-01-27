package edu.tudelft.games.f1manager.game;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CircuitTest {

  @Test
  public void testConstructor(){
    Circuit circuit = new Circuit(1.5, 5, 1.3, "country", "path");

    assertEquals(circuit.getCountry(), "country");
    assertEquals(circuit.getRaceTimeBase(), 1.5, 0.1);
    assertEquals(circuit.getTurns(), 5);
    assertEquals(circuit.getLength(), 1.3, 0.1);
    assertEquals(circuit.getPath(), "path");
  }
}
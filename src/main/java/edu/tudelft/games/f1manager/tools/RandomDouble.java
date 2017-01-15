package edu.tudelft.games.f1manager.tools;

import java.util.Random;

public class RandomDouble {

  public static double generate(double minvalue, double maxvalue) {
    Random r = new Random();
    return minvalue + (maxvalue - minvalue) * r.nextDouble();
  }

  public static double generatePercentage() {
    return RandomDouble.generate(0, 100);
  }

}

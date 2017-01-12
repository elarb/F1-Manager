package edu.tudelft.games.f1manager.tools;

import java.util.Random;

/**
 * Created by Timpelser on 12/01/2017.
 */
public class RandomDouble {

  public static double Generate(double minvalue, double maxvalue){

    Random r = new Random();
    return minvalue + (maxvalue - minvalue) * r.nextDouble();

  }

}

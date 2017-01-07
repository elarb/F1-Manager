package edu.tudelft.games.f1manager.tools;

import java.util.Random;

/**
 * Created by Timpelser on 21/12/2016.
 */
public class RandomDouble {

  public static void main(String args[]) {

    Random r = new Random();
    double randomvalue = 500 + 350 * r.nextDouble();
    double randomvalue2 = 4 + 4 * r.nextDouble();
    double randomvalue3 = 4 + 4 * r.nextDouble();

    System.out.println(randomvalue);
    System.out.println(randomvalue2);
    System.out.println(randomvalue3);

  }

}


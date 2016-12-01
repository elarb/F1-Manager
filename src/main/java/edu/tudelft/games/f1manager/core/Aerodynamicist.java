package edu.tudelft.games.f1manager.core;

/**
 * This class represents an Aerodynamicist.
 */
public class Aerodynamicist {

  /**
   * Expertise the Aerodynamicist has, expressed as an integer from 60-99.
   */
  private int expertise;

  /**
   * Creates an object that represents an Aerodynamicist of a F1 team.
   *
   * @param expertise
   */
  public Aerodynamicist(int expertise) {
    this.expertise = expertise;
  }

  /**
   * Suppose x = expertise.
   * If 60<x<69, then expertise can increase with 4, 5 or 6
   * If 70<x<79, then expertise can increase with 3, 4 or 5
   * If 80<x<89, then expertise can increase with 2, 3 or 4
   * If 90<x<99, then expertise can increase with 1, 2 or 3
   */
  public void upgrade() {

    if (this.expertise >= 60 && this.expertise <= 69) {

      double d = Math.random();

      if (d < 0.3) {
        //30% chance
        this.expertise = this.expertise + 4;
      } else if (d < 0.7) {
        //40% chance
        this.expertise = this.expertise + 5;
      } else {
        //30% chance
        this.expertise = this.expertise + 6;
      }
    } else if (this.expertise >= 70 && this.expertise <= 79) {
      double d = Math.random();

      if (d < 0.3) {
        //30% chance
        this.expertise = this.expertise + 3;
      } else if (d < 0.7) {
        //40% chance
        this.expertise = this.expertise + 4;
      } else {
        //30% chance
        this.expertise = this.expertise + 5;
      }
    } else if (this.expertise >= 80 && this.expertise <= 89) {
      double d = Math.random();

      if (d < 0.3) {
        //30% chance
        this.expertise = this.expertise + 2;
      } else if (d < 0.7) {
        //40% chance
        this.expertise = this.expertise + 3;
      } else {
        //30% chance
        this.expertise = this.expertise + 4;
      }
    } else if (this.expertise >= 90 && this.expertise <= 99) {
      double d = Math.random();

      if (d < 0.3) {
        //30% chance
        this.expertise = this.expertise + 1;
      } else if (d < 0.7) {
        //40% chance
        this.expertise = this.expertise + 2;
      } else {
        //30% chance
        this.expertise = this.expertise + 3;
      }
    }


  }

}

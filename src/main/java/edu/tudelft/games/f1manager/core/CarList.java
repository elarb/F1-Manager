package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

public class CarList {

  private ArrayList<Car> carList;


  public CarList() {
    this.carList = new ArrayList<>();
  }

  /**
   * Adds a car to the Carlist.
   *
   * @param car the car that gets added to the carlist
   */
  public void addCar(Car car) {

    this.carList.add(car);

  }


  public ArrayList<Car> getcarList() {
    return carList;
  }

  public void setcarList(ArrayList<Car> carList) {
    this.carList = carList;
  }
}
package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

public class CarList {

  private ArrayList<Car> cars;


  public CarList() {
    this.cars = new ArrayList<>();
  }

  /**
   * Adds a car to the Carlist.
   *
   * @param car the car that gets added to the carlist
   */
  public void addCar(Car car) {
    this.cars.add(car);
  }


  public ArrayList<Car> getcarList() {
    return cars;
  }

  public void setcarList(ArrayList<Car> carList) {
    this.cars = carList;
  }
}
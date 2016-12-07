package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

public class CarList {

  private ArrayList<Car> carList;

  public void addCar(Car car) {

    this.carList.add(car);

  }

  public CarList(ArrayList<Car> carList) {
    this.carList = carList;
  }

  public ArrayList<Car> getcarList() {
    return carList;
  }

  public void setcarList(ArrayList<Car> carList) {
    this.carList = carList;
  }
}
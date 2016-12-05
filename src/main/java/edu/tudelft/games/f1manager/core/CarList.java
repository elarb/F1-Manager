package edu.tudelft.games.f1manager.core;

import java.util.ArrayList;

public class CarList{

private ArrayList<Car> carList;


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
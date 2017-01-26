package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableEngine extends RecursiveTreeObject<TableEngine> {

  StringProperty brand;
  DoubleProperty power;
  DoubleProperty drivability;
  DoubleProperty fuelEfficiency;
  StringProperty value;

  TableEngine(String brand, double power, double drivability, double fuelEfficiency, int price) {
    this.brand = new SimpleStringProperty(brand);
    this.power = new SimpleDoubleProperty(power);
    this.drivability = new SimpleDoubleProperty(drivability);
    this.fuelEfficiency = new SimpleDoubleProperty(fuelEfficiency);

    String tempPrice = "";
    int amount = 0;
    char[] chars = Integer.toString(price).toCharArray();
    for (char ch: chars) {

      if (Integer.toString(price).toCharArray().length - amount == 6) {
        tempPrice = tempPrice + ".";
      } else if (amount > 5) {
        break;
      }
      tempPrice = tempPrice + ch;
      amount++;
    }
    tempPrice = tempPrice + " M";
    this.value = new SimpleStringProperty(tempPrice);
  }



}

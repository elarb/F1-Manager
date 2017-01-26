package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.*;

class TableDriver extends RecursiveTreeObject<TableDriver> {

  StringProperty name;
  DoubleProperty speed;
  DoubleProperty racecraft;
  DoubleProperty strategyinsight;
  StringProperty value;

  TableDriver(String name, double speed, double racecraft, double strategyinsight, int value) {
    this.name = new SimpleStringProperty(name);
    this.speed = new SimpleDoubleProperty(speed);
    this.racecraft = new SimpleDoubleProperty(racecraft);
    this.strategyinsight = new SimpleDoubleProperty(strategyinsight);

    String tempValue = "";
    int amount = 0;
    for (char ch: Integer.toString(value).toCharArray()) {

      if (amount == 2) {
        tempValue = tempValue + ".";
      } else if (amount > 5) {
        break;
      }
      tempValue = tempValue + ch;
      amount++;
    }
    tempValue = tempValue + " M";
    this.value = new SimpleStringProperty(tempValue);
  }
}
package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableDriver extends RecursiveTreeObject<TableDriver> {

  StringProperty name;
  StringProperty value;

  TableDriver(String name, int value) {
    this.name = new SimpleStringProperty(name);
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
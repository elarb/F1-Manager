package edu.tudelft.games.f1manager;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableEngine extends RecursiveTreeObject<TableEngine> {

  StringProperty brand;
  StringProperty value;

  TableEngine(String brand, int value) {
    this.brand = new SimpleStringProperty(brand);
    String tempValue = "";
    int amount = 0;
    for (char ch: Integer.toString(value).toCharArray()) {

      if (Integer.toString(value).toCharArray().length - amount == 6) {
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

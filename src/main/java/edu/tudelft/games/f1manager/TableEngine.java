package edu.tudelft.games.f1manager;


import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableEngine extends RecursiveTreeObject<TableEngine> {

  StringProperty brand;
  StringProperty value;

  TableEngine(String brand, int price) {
    this.brand = new SimpleStringProperty(brand);
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

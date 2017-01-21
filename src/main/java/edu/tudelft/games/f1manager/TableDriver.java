package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableDriver extends RecursiveTreeObject<TableDriver> {

  StringProperty name;
  StringProperty time;
  StringProperty team;

  TableDriver(String name, double time, String team) {
    this.name = new SimpleStringProperty(name);
    this.time = new SimpleStringProperty(Double.toString(time));
    this.team = new SimpleStringProperty(team);
  }
}

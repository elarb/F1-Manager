package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableDriverResult extends RecursiveTreeObject<TableDriverResult> {

  StringProperty name;
  StringProperty time;
  StringProperty team;

  TableDriverResult(String name, double time, String team) {
    this.name = new SimpleStringProperty(name);
    this.time = new SimpleStringProperty(Double.toString(time));
    this.team = new SimpleStringProperty(team);
  }
}

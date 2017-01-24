package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

class TableDriverResult extends RecursiveTreeObject<TableDriverResult> {

  StringProperty position;
  StringProperty name;
  StringProperty time;
  StringProperty team;

  TableDriverResult(String position, String name, String time, String team) {
    this.position = new SimpleStringProperty(position);
    this.name = new SimpleStringProperty(name);
    this.time = new SimpleStringProperty(time);
    this.team = new SimpleStringProperty(team);
  }
}

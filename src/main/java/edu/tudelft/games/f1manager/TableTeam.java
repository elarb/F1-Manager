package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableTeam  extends RecursiveTreeObject<TableTeam> implements Comparable<TableTeam> {

  StringProperty name;
  IntegerProperty points;

  TableTeam(String name, int points) {
    this.name = new SimpleStringProperty(name);
    this.points = new SimpleIntegerProperty(points);
  }

  public int compareTo(TableTeam other) {
    return other.points.toString().compareTo(this.points.toString());
  }
}

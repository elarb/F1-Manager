package edu.tudelft.games.f1manager;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.game.GameEvent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class TableGameEvent  extends RecursiveTreeObject<TableGameEvent>{

  StringProperty type;
  StringProperty message;
  StringProperty time;


  TableGameEvent(GameEvent.Type type, String message, String time) {
    this.type = new SimpleStringProperty(type.toString());
    this.message = new SimpleStringProperty(message);
    this.time = new SimpleStringProperty(time);
  }
}

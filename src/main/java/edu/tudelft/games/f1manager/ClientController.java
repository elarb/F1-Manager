package edu.tudelft.games.f1manager;


import edu.tudelft.games.f1manager.core.DriverList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.event.ActionEvent;
import java.io.IOException;

public class ClientController {

  @FXML
  private AnchorPane midAnchorPane;

  @FXML
  private Pane content;

  @FXML
  private ListView<String> buyableDrivers;

  @FXML
  public void handleButtonClick_Configuration() throws IOException {
    System.out.println("Click! configuration");
    content.getChildren().clear();
    content.getChildren().add(FXMLLoader.load(getClass().getResource("/fxml/Configuration.fxml")));
  }

  public void fillBuyableDrivers(){

    ObservableList<String> data = FXCollections.observableArrayList("driver drive", "max verstappen", "bob bobson");
    buyableDrivers.setItems(data);
  }

  public void buyDriver(){
    String driverName = buyableDrivers.getSelectionModel().getSelectedItem();
    System.out.println(driverName);
  }
}

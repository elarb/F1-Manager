package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CrewTabController {

  private ClientController clientController;

  @FXML
  private Label firstDriverLabel, secondDriverLabel, valueFirstDriver, valueSecondDriver;


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {

  }

  public void initData() {
    firstDriverLabel.setText(clientController.getGame().getPlayerteam().getDriverList().get(0).getName());
    secondDriverLabel.setText(clientController.getGame().getPlayerteam().getDriverList().get(1).getName());
    valueFirstDriver.setText(Integer.toString(clientController.getGame().getPlayerteam().getDriverList().get(0).getValue()));
    valueSecondDriver.setText(Integer.toString(clientController.getGame().getPlayerteam().getDriverList().get(1).getValue()));
  }
}

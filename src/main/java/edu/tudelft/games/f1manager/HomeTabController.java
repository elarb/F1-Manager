package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class HomeTabController {
  @FXML
  private AnchorPane homeContent;

  private ClientController clientController;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {


  }

  public AnchorPane getHomeContent() {
    return homeContent;
  }
}

package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {


  @FXML
  private AnchorPane mainScreen;

  public void handleButtonClick_StartGame() throws IOException {
    mainScreen.getChildren().clear();
    mainScreen.getChildren().add(FXMLLoader.load(getClass().getResource("/fxml/main menu.fxml")));
  }


  public void handleButtonClick_newGame() throws IOException{

  }

  public void handleButtonClick_LoadGame() throws IOException{

  }

  public void handleButtonClick_ExitGame() throws IOException {
    System.exit(0);
  }
}

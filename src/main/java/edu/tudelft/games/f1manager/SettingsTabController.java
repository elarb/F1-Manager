package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.IOException;

public class SettingsTabController {

  private ClientController clientController;

  @FXML
  private Pane saveGamePane;

  @FXML
  JFXTextField saveNameField;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void handleButtonClick_SaveGame(){
    saveGamePane.setVisible(true);
    saveGamePane.setDisable(false);
  }

  public void saveGame() throws IOException {
    String saveName = saveNameField.getText();
    if(!saveName.equals("")){
      clientController.getGame().savegame(saveName);
      saveGamePane.setDisable(true);
      saveGamePane.setVisible(false);
    }else{
      saveNameField.setStyle("-fx-background-color: red");
    }
  }
}

package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import edu.tudelft.games.f1manager.game.Game;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

import java.io.File;
import java.io.IOException;

public class SettingsTabController {

  private ClientController clientController;

  @FXML
  private JFXListView saveGameList;

  @FXML
  private Pane saveGamePane;

  @FXML
  private JFXTextField saveNameField;

  @FXML
  private JFXListView loadGameList;

  @FXML
  private Pane loadGamePane;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void handleButtonClick_SaveGame(){
    updateSaveGameList();
    saveGamePane.setVisible(true);
    saveGamePane.setDisable(false);
  }

  public void saveGame() throws IOException {

    String saveName = saveNameField.getText();
    if(!saveName.equals("")){
      clientController.getGame().savegame(saveName);
      saveGamePane.setDisable(true);
      saveGamePane.setVisible(false);
      saveNameField.setStyle("-fx-background-color: white");
    }else{
      saveName = (String) saveGameList.getSelectionModel().getSelectedItem();
      if(saveName != null){
        clientController.getGame().savegame(saveName);
        saveGamePane.setDisable(true);
        saveGamePane.setVisible(false);
        saveNameField.setStyle("-fx-background-color: white");
      }else{
        saveNameField.setStyle("-fx-background-color: red");
      }

    }
  }

  public void handleButtonClick_LoadGame(){
    System.out.println("load");
    updateLoadGameList();
    loadGamePane.setVisible(true);
    loadGamePane.setDisable(false);
  }

  public void loadGame(){
    String saveName = (String) loadGameList.getSelectionModel().getSelectedItem();

    if(saveName != null){
      clientController.setGame(Game.loadgame(saveName));
      loadGamePane.setDisable(true);
      loadGamePane.setVisible(false);
    }

  }

  private void updateSaveGameList(){
    saveGameList.setItems(findSaveGames());
  }

  private void updateLoadGameList(){
    loadGameList.setItems(findSaveGames());
  }

  private ObservableList<String> findSaveGames(){
    ObservableList<String> savedGames = FXCollections.observableArrayList();

    File file = new File("src/main/resources/JSON/");
    String[] saves = file.list((dir, name) -> new File(dir, name).isDirectory());

    for (String name : saves) {
      savedGames.add(name);
    }
    return savedGames;
  }
}


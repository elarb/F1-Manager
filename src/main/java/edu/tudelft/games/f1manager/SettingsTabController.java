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
import java.util.Collections;

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

  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  /**opens the saveGame pane.
   *
   */
  public void handleButtonClick_SaveGame() {
    updateSaveGameList();
    saveGamePane.setVisible(true);
    saveGamePane.setDisable(false);
  }

  /**saves the game with the name providen by typing or selecting a existing save.
   *
   * @throws IOException error
   */
  public void saveGame() throws IOException {

    String saveName = saveNameField.getText();
    if (!saveName.equals("")) {
      App.game.savegame(saveName);
      saveGamePane.setDisable(true);
      saveGamePane.setVisible(false);
      saveNameField.setStyle("-fx-background-color: white");
    } else {
      saveName = (String) saveGameList.getSelectionModel().getSelectedItem();
      if (saveName != null) {
        App.game.savegame(saveName);
        saveGamePane.setDisable(true);
        saveGamePane.setVisible(false);
        saveNameField.setStyle("-fx-background-color: white");
      } else {
        saveNameField.setStyle("-fx-background-color: red");
      }

    }
  }

  /**opens the loadGame pane.
   *
   */
  public void handleButtonClick_LoadGame() {
    updateLoadGameList();
    loadGamePane.setVisible(true);
    loadGamePane.setDisable(false);
  }

  /**loads the selected game from the list of saves.
   *
   */
  public void loadGame() {
    String saveName = (String) loadGameList.getSelectionModel().getSelectedItem();

    if (saveName != null) {
      App.game = Game.loadgame(saveName);
      loadGamePane.setDisable(true);
      loadGamePane.setVisible(false);
    }

  }

  private void updateSaveGameList() {
    saveGameList.setItems(findSaveGames());
  }

  private void updateLoadGameList() {
    loadGameList.setItems(findSaveGames());
  }

  private ObservableList<String> findSaveGames() {
    ObservableList<String> savedGames = FXCollections.observableArrayList();

    File file = new File("src/main/resources/JSON/");
    String[] saves = file.list((dir, name) -> new File(dir, name).isDirectory());

    if (saves != null) {
      Collections.addAll(savedGames, saves);
    }
    return savedGames;
  }
}


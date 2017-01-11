package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {

  @FXML
  private AnchorPane mainScreen;

  /**method that is called by the start game button.
   * changes the content of the screen to the main menu
   * @throws IOException error
   */
  public void handleButtonClick_StartGame() throws IOException {
    mainScreen.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main menu.fxml"));
    loader.setController(this);
    mainScreen.getChildren().add(loader.load());
  }

  public void handleButtonClick_newGame() throws IOException {

  }

  /**method that is called by the load game button.
   * changes the content of the screen to the main(client.fxml) screen
   * @throws IOException error
   */
  public void handleButtonClick_LoadGame() throws IOException {
    mainScreen.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client.fxml"));
    loader.setController(this);
    mainScreen.getChildren().add(loader.load());
  }

  public void handleButtonClick_ExitGame() throws IOException {
    System.exit(0);
  }
}

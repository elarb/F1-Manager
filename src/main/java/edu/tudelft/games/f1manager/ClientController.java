package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.core.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {

  @FXML
  private AnchorPane mainScreen;

  @FXML
  private TextField loadgameText;

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

  /**method that is called by the start game button.
   * calls gotoClientUi and game.newGame()
   * @throws IOException error
   */
  public void handleButtonClick_newGame() throws IOException {
    Game game = Game.newgame();

    if (game != null) {
      gotoClientUi();
    }
  }


  /**method that is called by the load game button.
   * calls gotoClientUi and game.loadGame()
   * @throws IOException error
   */
  public void handleButtonClick_LoadGame() throws IOException {
    Game game = Game.loadgame(loadgameText.getText());

    if (game != null) {
      gotoClientUi();
    }
  }

  public void handleButtonClick_ExitGame() {
    System.exit(0);
  }

  /**changes the content of the screen to the main(client.fxml) screen
   * @throws IOException error
   */
  private void gotoClientUi() throws IOException {
    mainScreen.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/Client.fxml"));
    loader.setController(this);
    mainScreen.getChildren().add(loader.load());
  }
}

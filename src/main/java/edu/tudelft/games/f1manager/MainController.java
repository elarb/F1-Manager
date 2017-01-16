package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Game;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class MainController {

  private Game game;

  @FXML
  private ClientController clientController;

  @FXML
  private AnchorPane mainScreen;

//  /**
//   * method that is called by the start game button.
//   * changes the content of the screen to the main menu
//   *
//   * @throws IOException error
//   */
//  public void startGame() throws IOException {
//    mainScreen.getChildren().clear();
//    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MainMenu.fxml"));
//    loader.setController(mainMenuController);
//    mainScreen.getChildren().add(loader.load());
//  }
//
//
//  @FXML
//  private void initialize() {
//    mainMenuController.injectMainController(this);
//  }

}
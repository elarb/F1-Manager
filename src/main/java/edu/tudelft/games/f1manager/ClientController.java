package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Game;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ClientController {



  private Game game;

  private String saveName;

  @FXML
  private TextField newgameText;

  @FXML
  private AnchorPane mainScreen;

  @FXML
  private TextField loadgameText;

  /**method that is called by the start game button.
   * changes the content of the screen to the main menu
   * @throws IOException error
   */
  public void handleButtonClick_StartGame() throws IOException {
    gotoUi("/fxml/main menu.fxml");
  }

  /**method that is called by the start game button.
   * calls gotoClientUi and game.newGame()
   * @throws IOException error
   */
//  public void handleButtonClick_newGame() throws IOException {
//    if (!newgameText.getText().equals("")) {
//      game = Game.newgame();
//      saveName = newgameText.getText();
//      Game.savegame(saveName, game.getDriverList(),
//          game.getAiTeamList(), game.getPlayerTeam(), game.getSeason());
//
//      if (game != null) {
//        gotoUi("/fxml/Client.fxml");
//      }
//    }
//  }


  /**method that is called by the load game button.
   * calls gotoClientUi and game.loadGame()
   * @throws IOException error
   */
  public void handleButtonClick_LoadGame() throws IOException {
    String fileName = loadgameText.getText();
    game = Game.loadgame(fileName);

    if (game != null) {
      gotoUi("/fxml/Client.fxml");
      saveName = fileName;
    }
  }

  /**saves game and changes ui to main menu.
   * @throws IOException error
   */
//  public void handleButtonClick_MainMenu() throws IOException {
//    Game.savegame(saveName, game.getDriverList(),
//        game.getAiTeamList(), game.getPlayerTeam(), game.getSeason());
//    gotoUi("/fxml/main menu.fxml");
//  }

  public void handleButtonClick_ExitGame() {
    System.exit(0);
  }

  /**changes the content of the screen to the main(client.fxml) screen
   * @throws IOException error
   */
  private void gotoUi(String path) throws IOException {
    mainScreen.getChildren().clear();
    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
    loader.setController(this);
    mainScreen.getChildren().add(loader.load());
  }

  public Game getGame() {
    return game;
  }





}

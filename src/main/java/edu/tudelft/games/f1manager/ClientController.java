package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class ClientController {

  private Game game;

  @FXML
  private ConfigurationTabController configurationTabController;
  @FXML
  private CrewTabController crewTabController;
  @FXML
  private HomeTabController homeTabController;
  @FXML
  private SettingsTabController settingsTabController;

  @FXML
  private Label teamNameLabel;

  @FXML
  NextRaceTabController nextRaceTabController;

  public AnchorPane getHomeContent() {
    return homeTabController.getHomeContent();
  }


  @FXML
  private void initialize() {
    game = Game.newGame();
    teamNameLabel.setText(game.getPlayerteam().getName());
    configurationTabController.injectMainController(this);
    crewTabController.injectMainController(this);
    homeTabController.injectMainController(this);
    settingsTabController.injectMainController(this);
    nextRaceTabController.injectMainController(this);
    crewTabController.initData();
  }

  public void updateConfigurationTab() {
    configurationTabController.populateBuyDriverList();
  }

  private void updateHomeTab() {
    homeTabController.populateRaceResultList();
  }

  @FXML
  private void handleButtonClick_Race() {
    game.race();
    updateHomeTab();
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }
}

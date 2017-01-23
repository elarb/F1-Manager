package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;

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
  NextRaceTabController nextRaceTabController;

  @FXML
  private Label teamNameLabel, cashLabel, raceLabel, pointsLabel;

  public AnchorPane getHomeContent() {
    return homeTabController.getHomeContent();
  }


  @FXML
  private void initialize() {
    game = Game.newGame();
    loadData();
    configurationTabController.injectMainController(this);
    crewTabController.injectMainController(this);
    homeTabController.injectMainController(this);
    settingsTabController.injectMainController(this);
    nextRaceTabController.injectMainController(this);
    crewTabController.loadData();
  }

  public void loadData() {
    teamNameLabel.setText(game.getPlayerteam().getName());
    DecimalFormat formatter = new DecimalFormat("#,###.00");
    cashLabel.setText("$" + formatter.format(game.getPlayerteam().getBudget()));
    pointsLabel.setText("Points: " + game.getPlayerteam().getPoints());
    raceLabel.setText("Race: " + game.getCurrentRace() + "/20");
  }

  public void updateConfigurationTab() {
    configurationTabController.populateBuyDriverList();
  }

  void updateHomeTab() {
    homeTabController.populateGameEventList();
  }

  @FXML
  private void handleButtonClick_Race() {
    game.race();
    homeTabController.populateRaceResultList();
    homeTabController.populatePointsList();
    updateHomeTab();
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }
}

package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ClientController {

  @FXML
  private MarketPlaceTabController marketPlaceTabController;
  @FXML
  private CrewTabController crewTabController;
  @FXML
  private HomeTabController homeTabController;
  @FXML
  private SettingsTabController settingsTabController;
  @FXML
  private JFXButton raceButton;
  @FXML
  NextRaceTabController nextRaceTabController;

  @FXML
  private Label teamNameLabel, cashLabel, raceLabel, pointsLabel;

  @FXML
  private JFXTabPane tabPane;

  public AnchorPane getHomeContent() {
    return homeTabController.getHomeContent();
  }


  @FXML
  private void initialize() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            loadMenuData();
          }
        });
      }
    }, 0, 2000);

    marketPlaceTabController.injectMainController(this);
    crewTabController.injectMainController(this);
    homeTabController.injectMainController(this);
    settingsTabController.injectMainController(this);
    nextRaceTabController.injectMainController(this);
    marketPlaceTabController.init();
    crewTabController.init();
    nextRaceTabController.init();
  }

  public void loadMenuData() {
    teamNameLabel.setText(App.game.getPlayerteam().getName());
    DecimalFormat formatter = new DecimalFormat("#,###");
    cashLabel.setText("$" + formatter.format(App.game.getPlayerteam().getBudget()));
    pointsLabel.setText("Points: " + App.game.getPlayerteam().getPoints());
    raceLabel.setText("Race: " + App.game.getCurrentRace() + "/20");
  }

  @FXML
  /**
   * Run race, play sound and temporary disable race button.
   */
  public void race() {
    App.game.race();
    App.playSound("Race");
    new Thread(() -> {
      Platform.runLater(() -> raceButton.setDisable(true));
      try {
        Thread.sleep(400);
      } catch (InterruptedException ex) {
      }
      Platform.runLater(() -> raceButton.setDisable(false));
    }).start();

    SingleSelectionModel<Tab> selectionModel = tabPane.getSelectionModel();
    selectionModel.select(0);
    homeTabController.populateRaceResultList();
    homeTabController.populatePointsList();
    homeTabController.populateGameEventList();
    nextRaceTabController.update();
  }

  public void updateConfigurationTab() {
    marketPlaceTabController.populateBuyDriverList();
  }

  public HomeTabController getHomeTabController() {
    return homeTabController;
  }
}

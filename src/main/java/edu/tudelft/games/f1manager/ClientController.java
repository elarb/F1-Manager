package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTabPane;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;

public class ClientController {

  @FXML
  NextRaceTabController nextRaceTabController;
  private double xOffset = 0;
  private double yOffset = 0;
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
  private Label teamNameLabel, cashLabel, raceLabel, pointsLabel;

  @FXML
  private JFXTabPane tabPane;

  @FXML
  private JFXButton closeButton, minimizeButton;

  @FXML
  private AnchorPane mainPane;

  public AnchorPane getHomeContent() {
    return homeTabController.getHomeContent();
  }


  @FXML
  private void initialize() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> loadMenuData());
      }
    }, 0, 2000);

    mainPane.setOnMousePressed(event -> {
      xOffset = event.getSceneX();
      yOffset = event.getSceneY();
    });
    mainPane.setOnMouseDragged(event -> {
      App.mainStage.setX(event.getScreenX() - xOffset);
      App.mainStage.setY(event.getScreenY() - yOffset);
    });
    initMenuButtons();
    marketPlaceTabController.injectMainController(this);
    crewTabController.injectMainController(this);
    homeTabController.injectMainController(this);
    settingsTabController.injectMainController(this);
    nextRaceTabController.injectMainController(this);
    homeTabController.init();
    marketPlaceTabController.init();
    crewTabController.init();
    nextRaceTabController.init();
  }

  /**
   * Loads the data that gets shown on the menu.
   */
  public void loadMenuData() {
    teamNameLabel.setText(App.game.getPlayerteam().getName());
    DecimalFormat formatter = new DecimalFormat("#,###");
    cashLabel.setText("$" + formatter.format(App.game.getPlayerteam().getBudget()));
    pointsLabel.setText("Points: " + App.game.getPlayerteam().getPoints());
    raceLabel.setText("Raced: " + App.game.getCurrentRace() + "/20");
  }

  public void initMenuButtons() {
    Image imageDecline = new Image("/img/close.png");
    ImageView imageView = new ImageView(imageDecline);
    imageView.setFitWidth(30);
    imageView.setFitHeight(30);
    closeButton.setGraphic(imageView);

    Image imageMinimize = new Image("/img/minimize.png");
    ImageView imageView2 = new ImageView(imageMinimize);
    imageView2.setFitWidth(30);
    imageView2.setFitHeight(30);
    minimizeButton.setGraphic(imageView2);

    //FADE OUT MAIN STAGE ON CLOSE
    closeButton.setOnMouseClicked(event -> {
      Timeline timeline = new Timeline();
      KeyFrame key = new KeyFrame(Duration.millis(100),
        new KeyValue(App.mainStage.getScene().getRoot().opacityProperty(), 0.2));
      timeline.getKeyFrames().add(key);
      timeline.setOnFinished((ae) -> System.exit(1));
      timeline.play();
    });
    //MINIMIZE STAGE ON CLICK
    minimizeButton.setOnMouseClicked(event -> App.mainStage.setIconified(true));
  }

  /**
   * Run race, play sound and temporary disable race button.
   */
  @FXML
  public void race() {
    App.game.race();
    App.playSound("Race");
    new Thread(() -> {
      Platform.runLater(() -> raceButton.setDisable(true));
      try {
        Thread.sleep(4000);
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
    marketPlaceTabController.populateBuyEngineList();
  }

  public HomeTabController getHomeTabController() {
    return homeTabController;
  }
}

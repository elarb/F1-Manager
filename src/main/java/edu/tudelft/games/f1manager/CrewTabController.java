package edu.tudelft.games.f1manager;

import com.jfoenix.controls.*;
import edu.tudelft.games.f1manager.core.Driver;
import edu.tudelft.games.f1manager.core.Strategist;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class CrewTabController {

  private static int selectedDriver;

  private ClientController clientController;

  private JFXPopup selectedDriversPopup;

  private Stage driverUpgradeStage;


  @FXML
  private Label firstDriverLabel, secondDriverLabel, driverRating1,
    driverRating2, driverValue1, driverValue2;

  @FXML
  private ProgressBar strategyinsight1, strategyinsight2, racecraft1, racecraft2, speed1, speed2;

  @FXML
  private ProgressBar aeroProgress, strategistProgress, mechanicProgress;

  @FXML
  private ListView selectDriverList;

  @FXML
  private ImageView firstDriverImg, secondDriverImg;

  @FXML
  private JFXRadioButton lowRiskRadio, mediumRiskRadio, highRiskRadio;

  @FXML
  private JFXToggleButton softwareTesterToggle;

  @FXML
  private JFXButton upgradeDriver1, upgradeDriver2;

  @FXML
  private JFXButton upgradeSpeedButton, upgradeRaceCraftButton, upgradeInsightButton;

  @FXML
  private AnchorPane crewTab;

  @FXML
  private JFXSlider tireSlider;


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void init() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(() -> update());
      }
    }, 0, 2000);

    initDriversPopup();
    try {
      initUpgradeStage();
    } catch (IOException e) {
      e.printStackTrace();
    }
    initTiresSlider();
    initCrewData();
    handleRisk();
    handleSoftwareToggle();
  }

  public void update() {
    loadDriverData();
    populateSelectDriverList();
  }

  public void initCrewData() {
    aeroProgress.setProgress((double) App.game.getPlayerteam().getAerodynamicist().getExpertise() / 100);
    //TODO Temporary dirty way to get mechanic rating
    mechanicProgress.setProgress((8.5 - App.game.getPlayerteam().getMechanic().getPitstopTime()) / 6);
    strategistProgress.setProgress((double) App.game.getPlayerteam().getStrategist().getRating() / 100);
  }

  @FXML
  public void loadDriverData() {

    firstDriverLabel.setText(App.game.getFirstDriver().getName());
    secondDriverLabel.setText(App.game.getSecondDriver().getName());

    String rating1 = "Rating: " + App.game.getFirstDriver().getRating() + "/100";
    String rating2 = "Rating: " + App.game.getSecondDriver().getRating() + "/100";
    driverRating1.setText(rating1);
    driverRating2.setText(rating2);

    DecimalFormat formatter = new DecimalFormat("#,###");
    String value1 = "$" + formatter.format(App.game.getFirstDriver().getValue());
    String value2 = "$" + formatter.format(App.game.getSecondDriver().getValue());
    driverValue1.setText(value1);
    driverValue2.setText(value2);

    strategyinsight1.setProgress(App.game.getFirstDriver().getStrategyinsight() / 10);
    strategyinsight2.setProgress(App.game.getSecondDriver().getStrategyinsight() / 10);

    racecraft1.setProgress(App.game.getFirstDriver().getRacecraft() / 10);
    racecraft2.setProgress(App.game.getSecondDriver().getRacecraft() / 10);

    speed1.setProgress(App.game.getFirstDriver().getSpeed() / 10);
    speed2.setProgress(App.game.getSecondDriver().getSpeed() / 10);

    firstDriverImg.setImage(App.game.getPlayerteam().getFirstDriverImg());
    secondDriverImg.setImage(App.game.getPlayerteam().getSecondDriverImg());
  }

  public void swapWithDriver(int num) {
    String driverName = (String) selectDriverList.getSelectionModel().getSelectedItem();
    List<Driver> list = App.game.getPlayerteam().getDriverList();

    if (num == 1) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getName().equals(driverName)) {
          Collections.swap(list, 0, i);
        }
      }
    } else if (num == 2) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getName().equals(driverName)) {
          Collections.swap(list, 1, i);
        }
      }
    }
    update();
  }

  public void populateSelectDriverList() {
    List<Driver> drivers = App.game.getPlayerteam().getDriverList();
    ObservableList<String> driverNames = FXCollections.observableArrayList();
    for (Driver driver : drivers) {
      driverNames.add(driver.getName());
    }
    selectDriverList.setItems(driverNames);
  }

  @FXML
  public void initDriversPopup() {
    selectedDriversPopup = new JFXPopup();
    JFXButton switchTo1 = new JFXButton();
    switchTo1.setText("Swap with first driver");
    switchTo1.setMinSize(100, 30);
    switchTo1.setOnMouseClicked(event -> {
      swapWithDriver(1);
      selectedDriversPopup.close();
    });

    JFXButton switchTo2 = new JFXButton();
    switchTo2.setText("Swap with second driver");
    switchTo2.setMinSize(100, 30);
    switchTo2.setOnMouseClicked(event -> {
      swapWithDriver(2);
      selectedDriversPopup.close();
    });

    VBox vbox = new VBox(switchTo1, switchTo2);
    vbox.setMinSize(100, 30);
    selectedDriversPopup.setContent(vbox);
    selectedDriversPopup.setMinSize(100, 120);
    selectedDriversPopup.setSource(selectDriverList);
    selectDriverList.setOnMouseClicked((event) -> selectedDriversPopup.show(JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY()));
  }

  public void initUpgradeStage() throws IOException {
    driverUpgradeStage = new Stage();

    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/DriverUpgradePopup.fxml"));
    AnchorPane pane = loader.load();
    driverUpgradeStage.setScene(new Scene(pane));
    driverUpgradeStage.setResizable(false);
    driverUpgradeStage.initStyle(StageStyle.UNDECORATED);
    driverUpgradeStage.initOwner(App.mainStage);
    driverUpgradeStage.initModality(Modality.APPLICATION_MODAL);

    pane.setOnKeyPressed((KeyEvent event) -> {
      if (KeyCode.ESCAPE == event.getCode()) {
        driverUpgradeStage.close();
      }
    });

    upgradeDriver1.setOnMouseClicked((event) -> {
      selectedDriver = 1;
      driverUpgradeStage.show();
    });
    upgradeDriver2.setOnMouseClicked((event) -> {
      selectedDriver = 2;
      driverUpgradeStage.show();
    });
  }

  @FXML
  public void upgradeSpeed() {
    upgradeDriver(0);
  }

  @FXML
  public void upgradeRaceCraft() {
    upgradeDriver(1);
  }

  @FXML
  public void upgradeInsight() {
    upgradeDriver(2);
  }

  public void upgradeDriver(int num) {
    List<Driver> drivers = App.game.getPlayerteam().getDriverList();
    if (selectedDriver == 1) {
      App.game.getEvents().addEvent(drivers.get(0).upgrade(num));
    } else if (selectedDriver == 2) {
      App.game.getEvents().addEvent(drivers.get(1).upgrade(num));
    }
    Stage stage = (Stage) upgradeSpeedButton.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void upgradeAerodynamicist() {
    boolean success = App.game.upgradeAeorodynamicist();
    if (success) {
      App.playSound("UpgradeAero");
      aeroProgress.setProgress((double) App.game.getPlayerteam().getAerodynamicist().getExpertise() / 100);
    } else {
      //TODO: show has failed Popup
    }
  }


  @FXML
  public void upgradeMechanic() {
    boolean success = App.game.upgradeMechanic();
    if (success) {
      App.playSound("UpgradeMech");
      mechanicProgress.setProgress((8.5 - App.game.getPlayerteam().getMechanic().getPitstopTime()) / 6);
    } else {
      //TODO: show has failed Popup
    }
  }

  @FXML
  public void upgradeStrategist() {
    boolean success = App.game.upgradeStrategist();
    if (success) {
      App.playSound("UpgradeMech");
      strategistProgress.setProgress((double) App.game.getPlayerteam().getStrategist().getRating() / 100);
    } else {
      //TODO: show has failed Popup
    }
  }

  @FXML
  public void handleRisk() {
    ToggleGroup risk = new ToggleGroup();
    lowRiskRadio.setToggleGroup(risk);
    mediumRiskRadio.setToggleGroup(risk);
    highRiskRadio.setToggleGroup(risk);

    risk.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
      @Override
      public void changed(ObservableValue<? extends Toggle> ov, Toggle t, Toggle t1) {
        lowRiskRadio = (JFXRadioButton) t1.getToggleGroup().getSelectedToggle();
        switch (lowRiskRadio.getText()) {
          case "Low Risk":
            App.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.LOW);
            break;
          case "Medium Risk":
            App.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.MEDIUM);
            break;
          case "High Risk":
            App.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.HIGH);
            break;
        }
      }
    });
  }

  public void handleSoftwareToggle() {
    softwareTesterToggle.setOnAction(event -> {
      if (softwareTesterToggle.isSelected()) {
        App.game.getPlayerteam().setSoftwareTester(true);
      } else {
        App.game.getPlayerteam().setSoftwareTester(false);
      }
    });
  }

  public void initTiresSlider() {
    tireSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue.intValue() != newValue.intValue()) {
        App.game.getPlayerteam().getCar().getTyres()
          .setHardness(newValue.intValue());
      }
    });
  }


}

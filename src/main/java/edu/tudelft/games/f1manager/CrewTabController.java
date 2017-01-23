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
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
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
  private JFXListView selectDriverList;

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


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void init() {
    Timer timer = new Timer();
    timer.scheduleAtFixedRate(new TimerTask() {
      @Override
      public void run() {
        Platform.runLater(new Runnable() {
          @Override
          public void run() {
            update();
          }
        });
      }
    }, 0, 2000);

    initDriversPopup();
    try {
      initUpgradeStage();
    } catch (IOException e) {
      e.printStackTrace();
    }
    handleRisk();
    handleSoftwareToggle();
  }

  public void update() {
    loadDriverData();
    populateSelectDriverList();
  }

  @FXML
  public void loadDriverData() {

    firstDriverLabel.setText(Main.game.getFirstDriver().getName());
    secondDriverLabel.setText(Main.game.getSecondDriver().getName());

    String rating1 = "Rating: " + Main.game.getFirstDriver().getRating() + "/100";
    String rating2 = "Rating: " + Main.game.getSecondDriver().getRating() + "/100";
    driverRating1.setText(rating1);
    driverRating2.setText(rating2);

    DecimalFormat formatter = new DecimalFormat("#,###");
    String value1 = "$" + formatter.format(Main.game.getFirstDriver().getValue());
    String value2 = "$" + formatter.format(Main.game.getSecondDriver().getValue());
    driverValue1.setText(value1);
    driverValue2.setText(value2);

    strategyinsight1.setProgress(Main.game.getFirstDriver().getStrategyinsight() / 10);
    strategyinsight2.setProgress(Main.game.getSecondDriver().getStrategyinsight() / 10);

    racecraft1.setProgress(Main.game.getFirstDriver().getRacecraft() / 10);
    racecraft2.setProgress(Main.game.getSecondDriver().getRacecraft() / 10);

    speed1.setProgress(Main.game.getFirstDriver().getSpeed() / 10);
    speed2.setProgress(Main.game.getSecondDriver().getSpeed() / 10);

    firstDriverImg.setImage(Main.game.getPlayerteam().getFirstDriverImg());
    secondDriverImg.setImage(Main.game.getPlayerteam().getSecondDriverImg());
  }

  public void swapWithDriver(int num) {
    String driverName = (String) selectDriverList.getSelectionModel().getSelectedItem();
    List<Driver> list = Main.game.getPlayerteam().getDriverList();

    if (num == 1) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getName().equals(driverName)) {
          Collections.swap(list, 0, i);
          System.out.println(list.get(i) + " moved to place 1 from " + (i + 1));
        }
      }
    } else if (num == 2) {
      for (int i = 0; i < list.size(); i++) {
        if (list.get(i).getName().equals(driverName)) {
          Collections.swap(list, 1, i);
          System.out.println(list.get(i) + " moved to place 2 from " + (i + 1));
        }
      }
    }
    update();
  }

  public void populateSelectDriverList() {
    List<Driver> drivers = Main.game.getPlayerteam().getDriverList();
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
      System.out.println("RUNNING EVENT 1");
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
    driverUpgradeStage.initOwner(FadeApp.mainStage);
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
    List<Driver> drivers = Main.game.getPlayerteam().getDriverList();
    if (selectedDriver == 1) {
      Main.game.getEvents().addEvent(drivers.get(0).upgrade(num));
    } else if (selectedDriver == 2) {
      Main.game.getEvents().addEvent(drivers.get(1).upgrade(num));
    }
    Stage stage = (Stage) upgradeSpeedButton.getScene().getWindow();
    stage.close();
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
            Main.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.LOW);
            break;
          case "Medium Risk":
            Main.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.MEDIUM);
            break;
          case "High Risk":
            Main.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.HIGH);
            break;
        }
      }
    });
  }

  public void handleSoftwareToggle() {
    softwareTesterToggle.setOnAction(event -> {
      if (softwareTesterToggle.isSelected()) {
        Main.game.getPlayerteam().setSoftwareTester(true);
      } else {
        Main.game.getPlayerteam().setSoftwareTester(false);
      }
    });
  }

  @FXML
  public void showDialog() {
    System.out.println("CLICKED");

  }

}

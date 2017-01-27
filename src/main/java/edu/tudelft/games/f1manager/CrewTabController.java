package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import edu.tudelft.games.f1manager.core.Driver;
import edu.tudelft.games.f1manager.core.Strategist;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
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
  private Label firstDriverLabel;
  @FXML
  private Label secondDriverLabel;
  @FXML
  private Label driverRating1;
  @FXML
  private Label driverRating2;
  @FXML
  private Label driverValue1;
  @FXML
  private Label driverValue2;

  @FXML
  private ProgressBar strategyinsight1;
  @FXML
  private ProgressBar strategyinsight2;
  @FXML
  private ProgressBar racecraft1;
  @FXML
  private ProgressBar racecraft2;
  @FXML
  private ProgressBar speed1;
  @FXML
  private ProgressBar speed2;

  @FXML
  private ProgressBar aeroProgress;
  @FXML
  private ProgressBar strategistProgress;
  @FXML
  private ProgressBar mechanicProgress;

  @FXML
  private ListView selectDriverList;

  @FXML
  private ImageView firstDriverImg;
  @FXML
  private ImageView secondDriverImg;

  @FXML
  private JFXRadioButton lowRiskRadio;
  @FXML
  private JFXRadioButton mediumRiskRadio;
  @FXML
  private JFXRadioButton highRiskRadio;

  @FXML
  private JFXToggleButton softwareTesterToggle;

  @FXML
  private JFXButton upgradeDriver1;
  @FXML
  private JFXButton upgradeDriver2;

  @FXML
  private JFXButton upgradeSpeedButton;
  @FXML
  private JFXButton upgradeRaceCraftButton;
  @FXML
  private JFXButton upgradeInsightButton;

  @FXML
  private JFXButton upgradeStrategistButton;
  @FXML
  private JFXButton mechanicButton;

  @FXML
  private AnchorPane crewTab;

  @FXML
  private JFXSlider tireSlider;

  @FXML
  private Label engineBrand;

  @FXML
  private Label enginePower;

  @FXML
  private Label engineDrive;

  @FXML
  private Label engineFuel;


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  /**initialises crewTabController.
   * creates timer to update label values.
   */
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
    loadEngine();
  }

  public void update() {
    loadDriverData();
    populateSelectDriverList();
  }

  /**sets the label values for the rating of the crew.
   *
   */
  public void initCrewData() {
    aeroProgress.setProgress((double) App.game.getPlayerteam().getAerodynamicist().getExpertise()
        / 100);
    //TODO Temporary dirty way to get mechanic rating
    mechanicProgress.setProgress((8.5 - App.game.getPlayerteam().getMechanic().getPitstopTime())
        / 6);
    strategistProgress.setProgress((double) App.game.getPlayerteam().getStrategist().getRating()
        / 100);
  }

  /**loads the labels for the drivers currently selected.
   *
   */
  @FXML
  public void loadDriverData() {
    DecimalFormat formatter = new DecimalFormat("#,###");

    if (App.game.getPlayerteam().getDriverList().size() != 0) {
      firstDriverLabel.setText(App.game.getFirstDriver().getName());

      String rating1 = "Rating: " + App.game.getFirstDriver().getRating() + "/100";
      driverRating1.setText(rating1);

      String value1 = "$" + formatter.format(App.game.getFirstDriver().getValue());
      driverValue1.setText(value1);

      strategyinsight1.setProgress(App.game.getFirstDriver().getStrategyinsight() / 10);
      racecraft1.setProgress(App.game.getFirstDriver().getRacecraft() / 10);
      speed1.setProgress(App.game.getFirstDriver().getSpeed() / 10);

      firstDriverImg.setImage(App.game.getPlayerteam().getFirstDriverImg());

    }
    if (App.game.getPlayerteam().getDriverList().size() > 1) {

      secondDriverLabel.setText(App.game.getSecondDriver().getName());

      String rating2 = "Rating: " + App.game.getSecondDriver().getRating() + "/100";
      driverRating2.setText(rating2);

      String value2 = "$" + formatter.format(App.game.getSecondDriver().getValue());
      driverValue2.setText(value2);

      strategyinsight2.setProgress(App.game.getSecondDriver().getStrategyinsight() / 10);
      racecraft2.setProgress(App.game.getSecondDriver().getRacecraft() / 10);
      speed2.setProgress(App.game.getSecondDriver().getSpeed() / 10);

      secondDriverImg.setImage(App.game.getPlayerteam().getSecondDriverImg());
    }

  }

  /**
   * Loads the engine labels with the current engine properties.
   */
  public void loadEngine() {
    engineBrand.setText("Brand: " + App.game.getPlayerteam().getCar().getEngine().getBrand());
    engineDrive.setText("Driveability: " + App.game.getPlayerteam().getCar().getEngine()
        .getDrivability());
    engineFuel.setText("Fuel-Efficiency: " + App.game.getPlayerteam().getCar().getEngine()
        .getFuelEfficiency());
    enginePower.setText("Power: " + App.game.getPlayerteam().getCar().getEngine().getPower());

  }

  /**swaps a driver with a driver currently selected to drive.
   * @param num the driver (1 or 2) to switch place with.
   */
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

  /**populates the list with all drivers you currently own.
   *
   */
  public void populateSelectDriverList() {
    List<Driver> drivers = App.game.getPlayerteam().getDriverList();
    ObservableList<String> driverNames = FXCollections.observableArrayList();
    for (Driver driver : drivers) {
      driverNames.add(driver.getName());
    }
    selectDriverList.setItems(driverNames);
  }

  /**initialises the popup in witch you can choose with which driver to swap the selected driver.
   *
   */
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
    selectedDriversPopup.setPopupContainer(crewTab);
    selectDriverList.setOnMouseClicked((event) -> selectedDriversPopup.show(
        JFXPopup.PopupVPosition.TOP, JFXPopup.PopupHPosition.LEFT, event.getX(), event.getY()));
  }

  /**initialises and shows the sceen to upgrade your drivers.
   * @throws IOException error
   */
  public void initUpgradeStage() throws IOException {
    driverUpgradeStage = new Stage();

    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource(
        "fxml/DriverUpgradePopup.fxml"));
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

  /**upgrades the driver at place num.
   * @param num (1 or 2) selects witch driver to upgrade
   */
  public void upgradeDriver(int num) {
    List<Driver> drivers = App.game.getPlayerteam().getDriverList();
    if (selectedDriver == 1) {
      int costs = App.game.getFirstDriver().getValue() / 10;
      if (App.game.getPlayerteam().getBudget() > costs) {
        App.game.getPlayerteam().lowerBudget(costs);
        App.game.getEvents().addEvent(drivers.get(0).upgrade(num));
        App.playSound("Wroom");
      } else {
        App.playSound("Negative");
      }
    } else if (selectedDriver == 2) {
      int costs = App.game.getFirstDriver().getValue() / 10;
      if (App.game.getPlayerteam().getBudget() > costs) {
        App.game.getPlayerteam().lowerBudget(costs);
        App.game.getEvents().addEvent(drivers.get(1).upgrade(num));
        App.playSound("Wroom");
      } else {
        App.playSound("Negative");
      }
    }
    Stage stage = (Stage) upgradeSpeedButton.getScene().getWindow();
    stage.close();
  }

  /**upgrades the Aereodynamicist.
   *
   */
  @FXML
  public void upgradeAerodynamicist() {
    boolean success = App.game.upgradeAeorodynamicist();
    if (success) {
      App.playSound("Wroom");
      aeroProgress.setProgress(
          (double) App.game.getPlayerteam().getAerodynamicist().getExpertise() / 100);
    } else {
      App.playSound("Negative");
    }
  }


  /**upgrades the mechanic.
   *
   */
  @FXML
  public void upgradeMechanic() {
    boolean success = App.game.upgradeMechanic();
    if (success) {
      App.playSound("UpgradeMech");
      mechanicProgress.setProgress(
          (8.5 - App.game.getPlayerteam().getMechanic().getPitstopTime()) / 6);
    } else {
      App.playSound("Negative");
    }
    if (App.game.getPlayerteam().getMechanic().getPitstopTime() == 2) {
      mechanicButton.setDisable(true);
    }
  }


  /**upgrades the strategist.
   *
   */
  @FXML
  public void upgradeStrategist() {
    boolean success = App.game.upgradeStrategist();
    if (success) {
      App.playSound("Wroom");
      strategistProgress.setProgress(
          (double) App.game.getPlayerteam().getStrategist().getRating() / 100);
    } else {
      App.playSound("Negative");
    }
    if (App.game.getPlayerteam().getStrategist().isMaxRated()) {
      upgradeStrategistButton.setDisable(true);
    }
  }

  /**initialises a listener that changes the risk when its changed in the ui.
   *
   */
  @FXML
  public void handleRisk() {
    ToggleGroup risk = new ToggleGroup();
    lowRiskRadio.setToggleGroup(risk);
    mediumRiskRadio.setToggleGroup(risk);
    highRiskRadio.setToggleGroup(risk);

    risk.selectedToggleProperty().addListener((ov, changeListener, t1) -> {
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
        default:
          App.game.getPlayerteam().getStrategist().setStrategy(Strategist.Risk.MEDIUM);
          break;
      }
    });
  }

  /**changes the hasSoftwareTester value when it is changed in the ui.
   *
   */
  public void handleSoftwareToggle() {
    softwareTesterToggle.setOnAction(event -> {
      if (softwareTesterToggle.isSelected()) {
        App.game.getPlayerteam().setSoftwareTester(true);
      } else {
        App.game.getPlayerteam().setSoftwareTester(false);
      }
    });
  }

  /**changes the tires hardness value when it is changed in the ui.
   *
   */
  public void initTiresSlider() {
    tireSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue.intValue() != newValue.intValue()) {
        App.game.getPlayerteam().getCar().getTyres()
          .setHardness(newValue.intValue());
      }
    });
  }
}

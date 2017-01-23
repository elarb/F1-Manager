package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXSlider;
import edu.tudelft.games.f1manager.core.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;

import java.io.IOException;
import java.util.ArrayList;

public class ConfigurationTabController {

  private ClientController clientController;

  @FXML
  private JFXListView buyDriverList;


  @FXML
  private JFXSlider tireSlider;


  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void init() {
    tireSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue.intValue() != newValue.intValue()) {
        App.game.getPlayerteam().getCar().getTyres().setHardness(newValue.intValue());
      }
    });
    update();
  }

  public void update() {
    populateBuyDriverList();
  }

  /**
   * looks at the selected name and buys the driver that has that name.
   *
   * @throws IOException error
   */
  public void handleButtonClick_buyDriver() throws IOException {
    String driverName = (String) buyDriverList.getSelectionModel().getSelectedItem();

    for (Driver driver : App.game.getDrivers()) {
      if (driver.getName().equals(driverName)) {
        App.game.driverBuy(driver);
        System.out.println("driver bought");
        System.out.println(App.game.getPlayerteam().getDriverList());
        populateBuyDriverList();
        clientController.loadMenuData();
        break;
      }
    }
  }

  /**
   * goes through the list of all the drivers, when a driver is not already in your team it gets added to the list.
   */
  public void populateBuyDriverList() {
    System.out.println("populating list");
    ArrayList<Driver> drivers = App.game.getDrivers();
    ObservableList<String> driverNames = FXCollections.observableArrayList();

    for (Driver driver : drivers) {
      boolean inList = true;
      for (Driver owned : App.game.getPlayerteam().getDriverList()) {
        if (owned.equals(driver)) {
          inList = false;
        }
      }
      if (inList) {
        driverNames.add(driver.getName());
      }
    }
    buyDriverList.setItems(driverNames);
    clientController.getCrewTabController().update();
  }

  public ClientController getClientController() {
    return clientController;
  }

}

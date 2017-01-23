package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.core.Driver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;

import java.io.IOException;


public class MarketPlaceTabController {
  @FXML
  private JFXTreeTableView buyDriverList;

  @FXML
  private JFXSlider tireSlider;

  private ClientController clientController;

  public ClientController getClientController() {
    return clientController;
  }


  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  @FXML
  public void init() {
    tireSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue.intValue() != newValue.intValue()) {
        App.game.getPlayerteam().getCar().getTyres()
          .setHardness(newValue.intValue());
      }
    });
  }

  /**
   * looks at the selected name and buys the driver that has that name.
   *
   * @throws IOException error
   */
  public void handleButtonClick_buyDriver() throws IOException {
    RecursiveTreeItem<TableDriver> driverItem =
      (RecursiveTreeItem<TableDriver>) buyDriverList.getSelectionModel().getSelectedItem();
    String driverName = driverItem.getValue().name.getValue();

    for (Driver driver : App.game.getDrivers()) {
      if (driver.getName().equals(driverName)) {
        App.game.driverBuy(driver);
        System.out.println("driver bought");
        System.out.println(App.game.getPlayerteam().getDriverList());
        clientController.loadMenuData();
        break;
      }
    }
    clientController.getHomeTabController().populateGameEventList();
    populateBuyDriverList();
  }

  /**
   * goes through the list of all the drivers, when a driver is not
   * already in your team it gets added to the list.
   */
  void populateBuyDriverList() {
    buyDriverList.setRoot(null);

    TreeTableColumn<TableDriver, String> driverColumn = new TreeTableColumn<>("Driver");
    driverColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableDriver, String> timeColumn = new TreeTableColumn<>("Value");
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().value);

    ObservableList<TableDriver> tableDrivers = FXCollections.observableArrayList();

    for (Driver driver : App.game.getDrivers()) {
      boolean inList = true;
      for (Driver owned : App.game.getPlayerteam().getDriverList()) {
        if (owned.getName().equals(driver.getName())) {
          inList = false;
          break;
        }

      }
      if (inList) {
        tableDrivers.add(new TableDriver(driver.getName(), driver.getValue()));
      }
    }

    TreeItem<TableDriver> root =
      new RecursiveTreeItem<>(tableDrivers, RecursiveTreeObject::getChildren);

    buyDriverList.setRoot(root);
    buyDriverList.setShowRoot(false);
    buyDriverList.getColumns().setAll(driverColumn, timeColumn);

  }
}

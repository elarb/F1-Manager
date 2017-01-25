package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.core.Driver;
import edu.tudelft.games.f1manager.core.Engine;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.util.Callback;

import java.io.IOException;


public class MarketPlaceTabController {
  @FXML
  private JFXTreeTableView buyDriverList, buyEngineList;

  private ClientController clientController;

  public ClientController getClientController() {
    return clientController;
  }

  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  @FXML
  public void init() {

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
        clientController.loadMenuData();
        break;
      }
    }
    clientController.getHomeTabController().populateGameEventList();
    populateBuyDriverList();
  }

  public void handleButtonClick_buyEngine(){
    RecursiveTreeItem<TableEngine> engineItem =
      (RecursiveTreeItem<TableEngine>) buyEngineList.getSelectionModel().getSelectedItem();
    String engineBrand = engineItem.getValue().brand.getValue();

    for (Engine engine : App.game.getEngines()) {
      if (engine.getBrand().equals(engineBrand)) {
        App.game.;
        clientController.loadMenuData();
        break;
      }
    }
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

  void populateBuyEngineList() {
    System.out.println("populating buyEngineList");
    buyEngineList.setRoot(null);

    TreeTableColumn<TableEngine, String> brandColumn = new TreeTableColumn<>("Brand");
    brandColumn.setCellValueFactory(param -> param.getValue().getValue().brand);

    TreeTableColumn<TableEngine, String> valueColumn = new TreeTableColumn<>("Value");
    valueColumn.setCellValueFactory(param -> param.getValue().getValue().value);

    ObservableList<TableEngine> tableEngines = FXCollections.observableArrayList();

    for (Engine engine: App.game.getEngines()) {
      if (!App.game.getPlayerteam().getCar().getEngine().getBrand().equals(engine.getBrand())) {
        tableEngines.add(new TableEngine(engine.getBrand(), engine.getPrice()));
      }
    }

    TreeItem<TableEngine> root =
      new RecursiveTreeItem<>(tableEngines, RecursiveTreeObject::getChildren);

    buyEngineList.setRoot(root);
    buyEngineList.setShowRoot(false);
    buyEngineList.getColumns().setAll(brandColumn, valueColumn);
  }

}

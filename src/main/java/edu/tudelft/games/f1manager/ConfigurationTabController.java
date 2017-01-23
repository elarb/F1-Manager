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


public class ConfigurationTabController {
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
  private void initialize() {
    tireSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
      if (oldValue.intValue() != newValue.intValue()) {
        clientController.getGame().getPlayerteam().getCar().getTyres()
          .setHardness(newValue.intValue());
      }
    });
  }

  /**looks at the selected name and buys the driver that has that name.
   *
   * @throws IOException error
   */
  public void handleButtonClick_buyDriver() throws IOException {
    RecursiveTreeItem<TableDriver> driverItem =
        (RecursiveTreeItem<TableDriver>) buyDriverList.getSelectionModel().getSelectedItem();
    String driverName = driverItem.getValue().name.getValue();

    for (Driver driver : clientController.getGame().getDrivers()) {
      if (driver.getName().equals(driverName)) {
        clientController.getGame().driverBuy(driver);
        System.out.println("driver bought");
        System.out.println(clientController.getGame().getPlayerteam().getDriverList());
        populateBuyDriverList();
        clientController.loadData();
        break;
      }
    }
    clientController.updateHomeTab();
    populateBuyDriverList();
  }

  /**goes through the list of all the drivers, when a driver is not
   * already in your team it gets added to the list.
   */
  void populateBuyDriverList() {
    buyDriverList.setRoot(null);

    TreeTableColumn<TableDriver, String> driverColumn = new TreeTableColumn<>("Driver");
    driverColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableDriver, String> timeColumn = new TreeTableColumn<>("Value");
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().value);

    ObservableList<TableDriver> tableDrivers = FXCollections.observableArrayList();

    for (Driver driver: clientController.getGame().getDrivers()) {
      System.out.println("looking at: " + driver.getName());
      boolean inList = true;
      for (Driver owned : clientController.getGame().getPlayerteam().getDriverList()) {
        if (owned.getName().equals(driver.getName())) {
          System.out.println("not adding: " + driver.getName());
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

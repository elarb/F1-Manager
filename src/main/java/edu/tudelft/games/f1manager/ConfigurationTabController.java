package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXListView;
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

  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  /**looks at the selected name and buys the driver that has that name.
   *
   * @throws IOException error
   */
  public void handleButtonClick_buyDriver() throws IOException {
    String driverName = (String)buyDriverList.getSelectionModel().getSelectedItem();

    for ( Driver driver : clientController.getGame().getDrivers()) {
      if (driver.getName().equals(driverName)){
        if(clientController.getGame().driverBuy(driver)){
          System.out.println("driver bought");
          populateBuyDriverList();
        }
        break;
      }
    }
  }

  /**goes through the list of all the drivers, when a driver is not already in your team it gets added to the list.
   *
   */
  void populateBuyDriverList(){
    System.out.println("populating list");
    ArrayList<Driver> drivers = clientController.getGame().getDrivers();
    ObservableList<String> driverNames = FXCollections.observableArrayList();

    for (Driver driver : drivers){
      boolean inList = true;
      for (Driver owned : clientController.getGame().getPlayerteam().getDriverList()){
        if(owned.equals(driver)){
          inList = false;
        }
      }
      if(inList){
        driverNames.add(driver.getName());
      }
    }
    buyDriverList.setItems(driverNames);
  }

}

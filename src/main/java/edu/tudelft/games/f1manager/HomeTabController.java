package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.core.Team;
import edu.tudelft.games.f1manager.game.DriverResult;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;


public class HomeTabController {
  @FXML
  private AnchorPane homeContent;

  @FXML
  private JFXTreeTableView<TableDriver> raceResultList;

  private ClientController clientController;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {
  }

  public void populateRaceResultList() {

//    TreeTableColumn<String, String> raceColumn = new TreeTableColumn<>("Race");
//    raceColumn.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue().getValue()));

    TreeTableColumn<TableDriver, String> driverColumn = new TreeTableColumn<>("Driver");
    driverColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableDriver, String> timeColumn = new TreeTableColumn<>("Time");
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().time);

    TreeTableColumn<TableDriver, String> teamColumn = new TreeTableColumn<>("Team");
    teamColumn.setCellValueFactory(param -> param.getValue().getValue().team);

    ObservableList<TableDriver> tableDrivers = FXCollections.observableArrayList();

    for (DriverResult result : clientController.getGame().getSeason().getPastRaceInstance().getResults()) {
      String teamName = "";
      if (result.getDriver().getTeamId() == 1) {
        teamName = clientController.getGame().getPlayerteam().getName();
      } else {
        for (Team team: clientController.getGame().getAiteams()) {
          if (result.getDriver().getTeamId() == team.getId()) {
            teamName = team.getName();
            break;
          }
        }
      }

      tableDrivers.add(new TableDriver(result.getDriver().getName(), result.getTime(), teamName));
    }


    TreeItem<TableDriver> root =
      new RecursiveTreeItem<>(tableDrivers, RecursiveTreeObject::getChildren);

    raceResultList.setRoot(root);
    raceResultList.setShowRoot(false);
    raceResultList.getColumns().setAll(driverColumn, timeColumn, teamColumn);



  }

  AnchorPane getHomeContent() {
    return homeContent;
  }
}



//class TableRace extends RecursiveTreeObject<TableDriver> {
//
//  StringProperty name;
//
//
//  public tableRace (Race race){
//    name = new SimpleStringProperty(race.getName());
//    teams =race.getResults()
//  }
//}


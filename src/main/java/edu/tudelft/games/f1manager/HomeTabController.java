package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.core.AiTeam;
import edu.tudelft.games.f1manager.core.Team;
import edu.tudelft.games.f1manager.game.DriverResult;
import edu.tudelft.games.f1manager.game.GameEvent;
import javafx.beans.property.IntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.Collections;


public class HomeTabController {
  @FXML
  private AnchorPane homeContent;

  @FXML
  private JFXTreeTableView<TableDriver> raceResultList;

  @FXML
  private JFXTreeTableView<TableGameEvent> gameEventList;

  @FXML
  private JFXTreeTableView<TableTeam> pointList;

  private ClientController clientController;

  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {
  }


  /**fills the resultList with columns and data from the last race.
   *
   */
  void populateRaceResultList() {

    //    TreeTableColumn<String, String> raceColumn = new TreeTableColumn<>("Race");
    //    raceColumn.setCellValueFactory(param ->
    // new ReadOnlyStringWrapper(param.getValue().getValue()));

    TreeTableColumn<TableDriver, String> driverColumn = new TreeTableColumn<>("Driver");
    driverColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableDriver, String> timeColumn = new TreeTableColumn<>("Time");
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().time);

    TreeTableColumn<TableDriver, String> teamColumn = new TreeTableColumn<>("Team");
    teamColumn.setCellValueFactory(param -> param.getValue().getValue().team);

    ObservableList<TableDriver> tableDrivers = FXCollections.observableArrayList();

    for (DriverResult result
        : clientController.getGame().getSeason().getPastRaceInstance().getResults()) {
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

  void populateGameEventList() {
    TreeTableColumn<TableGameEvent, String> typeColumn = new TreeTableColumn<>("Type");
    typeColumn.setCellValueFactory(param -> param.getValue().getValue().type);

    TreeTableColumn<TableGameEvent, String> messageColumn = new TreeTableColumn<>("Message");
    messageColumn.setCellValueFactory(param -> param.getValue().getValue().message);

    TreeTableColumn<TableGameEvent, String> timeColumn = new TreeTableColumn<>("Time");
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().time);

    ObservableList<TableGameEvent> tableGameEvents = FXCollections.observableArrayList();

    ArrayList<GameEvent> events = clientController.getGame().getEvents().getEvents();
    for (int i = events.size() - 1; i >= 0; i--) {
      if(i < events.size() - 100) {
        break;
      }
      GameEvent event = events.get(i);
      tableGameEvents.add(new TableGameEvent(event.getType(), event.getMessage(), event.getCurrentDateTime()));

    }

    TreeItem<TableGameEvent> root = new RecursiveTreeItem<>(tableGameEvents, RecursiveTreeObject::getChildren);

    gameEventList.setRoot(root);
    gameEventList.setShowRoot(false);
    gameEventList.getColumns().setAll(typeColumn, messageColumn, timeColumn);
  }

  void populatePointsList() {
    TreeTableColumn<TableTeam, String> nameColumn = new TreeTableColumn<>("Type");
    nameColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableTeam, Number> pointsColumn = new TreeTableColumn<>("Points");
    pointsColumn.setCellValueFactory(param -> param.getValue().getValue().points);

    ObservableList<TableTeam> tableTeams = FXCollections.observableArrayList();

    for (AiTeam team: clientController.getGame().getAiteams()) {
      tableTeams.add(new TableTeam(team.getName(), team.getPoints()));
    }
    tableTeams.add(new TableTeam(clientController.getGame().getPlayerteam().getName(), clientController.getGame().getPlayerteam().getPoints()));

    //Collections.sort(tableTeams);

    TreeItem<TableTeam> root = new RecursiveTreeItem<>(tableTeams, RecursiveTreeObject::getChildren);
    pointList.setRoot(root);
    pointList.setShowRoot(false);
    pointList.getColumns().setAll(nameColumn, pointsColumn);
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


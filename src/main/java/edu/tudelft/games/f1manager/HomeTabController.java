package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import edu.tudelft.games.f1manager.core.AiTeam;
import edu.tudelft.games.f1manager.core.Team;
import edu.tudelft.games.f1manager.game.DriverResult;
import edu.tudelft.games.f1manager.game.GameEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.util.ArrayList;


public class HomeTabController {
  @FXML
  private AnchorPane homeContent;

  @FXML
  private WebView webView;

  @FXML
  private JFXTreeTableView<TableDriverResult> raceResultList;

  @FXML
  private JFXTreeTableView<TableGameEvent> gameEventList;

  @FXML
  private JFXTreeTableView<TableTeam> pointList;

  private ClientController clientController;

  void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void init() {
    WebEngine webEngine = webView.getEngine();
    webEngine.load("https://www.formula1.com/");
  }


  /**
   * fills the resultList with columns and data from the last race.
   */
  void populateRaceResultList() {
    raceResultList.setRoot(null);

    //    TreeTableColumn<String, String> raceColumn = new TreeTableColumn<>("Race");
    //    raceColumn.setCellValueFactory(param ->
    // new ReadOnlyStringWrapper(param.getValue().getValue()));

    TreeTableColumn<TableDriverResult, String> positionCol = new TreeTableColumn<>("#");
    positionCol.setMinWidth(20);
    positionCol.setCellValueFactory(param -> param.getValue().getValue().position);

    TreeTableColumn<TableDriverResult, String> driverColumn = new TreeTableColumn<>("Driver");
    driverColumn.setMinWidth(160);
    driverColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableDriverResult, String> timeColumn = new TreeTableColumn<>("Time");
    timeColumn.setMinWidth(100);
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().time);

    TreeTableColumn<TableDriverResult, String> teamColumn = new TreeTableColumn<>("Team");
    teamColumn.setMinWidth(160);
    teamColumn.setCellValueFactory(param -> param.getValue().getValue().team);

    ObservableList<TableDriverResult> tableDriverResults = FXCollections.observableArrayList();
    ArrayList<DriverResult> results = App.game.getSeason().getPastRaceInstance().getResults();

    for (int i = 0; i < results.size(); i++) {
      String teamName = "";
      if (results.get(i).getDriver().getTeamId() == 1) {
        teamName = App.game.getPlayerteam().getName();
      } else {
        for (Team team : App.game.getAiteams()) {
          if (results.get(i).getDriver().getTeamId() == team.getId()) {
            teamName = team.getName();
            break;
          }
        }
      }
      tableDriverResults.add(new TableDriverResult(Integer.toString(i + 1), results.get(i).getDriver().getName(),
        results.get(i).getTimeString(), teamName));
    }


    TreeItem<TableDriverResult> root =
      new RecursiveTreeItem<>(tableDriverResults, RecursiveTreeObject::getChildren);

    raceResultList.setRoot(root);
    raceResultList.setShowRoot(false);
    raceResultList.getColumns().setAll(positionCol, driverColumn, timeColumn, teamColumn);

  }

  /**
   * populates the gameEventList.
   */

  void populateGameEventList() {
    gameEventList.setRoot(null);

    TreeTableColumn<TableGameEvent, String> typeColumn = new TreeTableColumn<>("Type");
    typeColumn.setMinWidth(90);
    typeColumn.setCellValueFactory(param -> param.getValue().getValue().type);

    TreeTableColumn<TableGameEvent, String> messageColumn = new TreeTableColumn<>("Message");
    messageColumn.setMinWidth(520);
    messageColumn.setCellValueFactory(param -> param.getValue().getValue().message);

    TreeTableColumn<TableGameEvent, String> timeColumn = new TreeTableColumn<>("Time");
    timeColumn.setMinWidth(150);
    timeColumn.setCellValueFactory(param -> param.getValue().getValue().time);

    ObservableList<TableGameEvent> tableGameEvents = FXCollections.observableArrayList();

    ArrayList<GameEvent> events = App.game.getEvents().getEvents();
    for (int i = events.size() - 1; i >= 0; i--) {
      if (i < events.size() - 100) {
        break;
      }
      GameEvent event = events.get(i);
      tableGameEvents.add(new TableGameEvent(event.getType(), event.getMessage(),
        event.getCurrentDateTime()));

    }

    TreeItem<TableGameEvent> root = new RecursiveTreeItem<>(tableGameEvents,
      RecursiveTreeObject::getChildren);

    gameEventList.setRoot(root);
    gameEventList.setShowRoot(false);
    gameEventList.getColumns().setAll(typeColumn, messageColumn, timeColumn);
  }

  /**
   * populates the Pointslist.
   */
  void populatePointsList() {
    pointList.setRoot(null);
    TreeTableColumn<TableTeam, String> teamColumn = new TreeTableColumn<>("Team");
    teamColumn.setMinWidth(120);
    teamColumn.setCellValueFactory(param -> param.getValue().getValue().name);

    TreeTableColumn<TableTeam, Number> pointsColumn = new TreeTableColumn<>("Points");
    pointsColumn.setCellValueFactory(param -> param.getValue().getValue().points);

    ObservableList<TableTeam> tableTeams = FXCollections.observableArrayList();

    for (AiTeam team : App.game.getAiteams()) {
      tableTeams.add(new TableTeam(team.getName(), team.getPoints()));
    }
    tableTeams.add(new TableTeam(App.game.getPlayerteam().getName(),
      App.game.getPlayerteam().getPoints()));

    //Collections.sort(tableTeams);

    TreeItem<TableTeam> root = new RecursiveTreeItem<>(tableTeams,
      RecursiveTreeObject::getChildren);
    pointList.setRoot(root);
    pointList.setShowRoot(false);
    pointList.getColumns().setAll(teamColumn, pointsColumn);
  }


  AnchorPane getHomeContent() {
    return homeContent;
  }
}


//class TableRace extends RecursiveTreeObject<TableDriverResult> {
//
//  StringProperty name;
//
//
//  public tableRace (Race race){
//    name = new SimpleStringProperty(race.getName());
//    teams =race.getResults()
//  }
//}


package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Race;
import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;

import java.time.LocalTime;

import static javafx.scene.paint.Color.rgb;

public class NextRaceTabController {

  private ClientController clientController;

  @FXML
  private AnchorPane nextRacePane;

  @FXML
  private Label raceName, lapsLabel, durationLabel, turnsLabel, countryLabel, distanceLabel;

  private SVGPath path;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void init() {
    update();
  }

  public void update() {
    String racename = App.game.getSeason().getCurrentRaceInstance().getName();
    raceName.setText(racename);
    path = new SVGPath();
    path.setFill(null);
    path.setStroke(rgb(97, 97, 97));
    path.setStrokeWidth(5);
    path.setContent(App.game.getSeason().getPathByRaceName(racename));
    DropShadow dropShadow = new DropShadow();
    dropShadow.setOffsetX(6.0);
    dropShadow.setOffsetY(4.0);
    Circle circle = new Circle(10, rgb(30, 214, 96));
    circle.setEffect(dropShadow);
    nextRacePane.getChildren().clear();
    nextRacePane.getChildren().addAll(path, circle);
    PathTransition transition = new PathTransition(javafx.util.Duration.seconds(10), path, circle);
    transition.setCycleCount(Animation.INDEFINITE);
    transition.setInterpolator(Interpolator.LINEAR);
    transition.play();

    Race race = App.game.getSeason().getCurrentRaceInstance();
    distanceLabel.setText(race.getDistance() + " KM");
    countryLabel.setText(race.getCircuit().getCountry());
    turnsLabel.setText(race.getCircuit().getTurns() + " Turns");

    LocalTime duration = LocalTime.ofSecondOfDay((long) race.getCircuit().getRaceTimeBase());
    durationLabel.setText(duration + " (Fastest)");
    lapsLabel.setText(race.getLaps() + " Laps");
  }


}

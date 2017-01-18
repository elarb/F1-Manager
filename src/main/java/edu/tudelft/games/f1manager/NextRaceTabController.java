package edu.tudelft.games.f1manager;

import javafx.animation.Animation;
import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.SVGPath;
import javafx.util.Duration;

public class NextRaceTabController {

  private ClientController clientController;

  @FXML
  private AnchorPane nextRacePane;

  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {
    SVGPath path = new SVGPath();

    path.setContent("M 568.17667,510.62185 386.07251,461.14133 296.85968,434.3688 c -13.35943,-7.41705 -8.72534,-21.46329 -10.72044,-31.72142 0,0 -2.13608,-12.36832 -15.94234,-14.67656 0,0 -60.34391,-11.70081 -105.73924,-30.00562 0,0 -40.5249,-21.1571 -73.350655,-44.31428 0,0 -18.971642,-4.95456 0.0066,-18.37302 l 21.060775,-17.11488 c 0,0 9.95793,-6.8337 -6.52803,-25.95823 l -24.792744,-41.1777 c 0,0 -6.269206,-7.54198 4.1392,-25.56486 L 109.24739,138.0059 c 0,0 10.47699,-15.66854 20.33319,-25.14538 0,0 15.10798,-10.38385 18.08034,-26.446407 0,0 0.56445,-16.808059 16.02723,-13.394092 0,0 22.19414,11.732899 36.9651,1.199755 0,0 37.47284,-15.936528 47.9883,-15.724444 0,0 10.54836,-3.511901 27.78038,12.103496 0,0 26.42541,23.83042 57.75615,62.362892 l 16.37671,21.20499 c 0,0 3.74363,1.34817 -4.79963,10.59826 0,0 -13.99801,12.89637 -10.10118,23.71626 0,0 13.2714,59.22881 31.09409,82.1788 0,0 20.37366,37.7206 51.86613,47.8309 l 73.24983,15.06751 c 0,0 14.19463,1.94286 29.30262,-8.441 0,0 17.09841,-16.47406 38.65141,-11.06967 l 89.09754,18.75303 c 0,0 14.07215,9.06256 22.00928,17.83258 l 67.24211,64.44289 c 0,0 11.4976,9.2127 -0.20275,21.71225 l -43.81814,54.17182 c 0,0 -11.19015,15.45367 -30.65125,2.58943 l -51.70239,-29.2677 c -6.83502,-1.03814 -11.92091,-6.39191 -14.83643,9.99893 0,0 -0.80059,47.60926 -28.77936,36.34085 z");
    path.setFill(null);
    path.setStroke(Color.RED);
    path.setStrokeWidth(5);

    Circle circle = new Circle(10, Color.BLUE);
    nextRacePane.getChildren().addAll(path, circle);

    PathTransition transition = new PathTransition(Duration.seconds(10), path, circle);

    transition.setCycleCount(Animation.INDEFINITE);
    transition.setInterpolator(Interpolator.LINEAR);
    transition.play();
  }


}

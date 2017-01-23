package edu.tudelft.games.f1manager;

import edu.tudelft.games.f1manager.game.Game;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class CrewTabController {

  private ClientController clientController;

  @FXML
  private Label firstDriverLabel, secondDriverLabel, driverRating1,
    driverRating2, driverValue1, driverValue2;

  @FXML
  private Pane driver1Pane;

  @FXML
  private ProgressBar strategyinsight1, strategyinsight2, racecraft1, racecraft2, speed1, speed2;


  @FXML
  private ImageView firstDriverImg, secondDriverImg;


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {

  }

  public void loadData() {
    Game game = clientController.getGame();
    firstDriverLabel.setText(game.getFirstDriver().getName());
    secondDriverLabel.setText(game.getSecondDriver().getName());

    String rating1 = "Rating: " + game.getFirstDriver().getRating() + "/100";
    String rating2 = "Rating: " + game.getSecondDriver().getRating() + "/100";
    driverRating1.setText(rating1);
    driverRating2.setText(rating2);

    strategyinsight1.setProgress(game.getFirstDriver().getStrategyinsight() / 10);
    strategyinsight2.setProgress(game.getSecondDriver().getStrategyinsight() / 10);

    racecraft1.setProgress(game.getFirstDriver().getRacecraft() / 10);
    racecraft2.setProgress(game.getSecondDriver().getRacecraft() / 10);

    speed1.setProgress(game.getFirstDriver().getSpeed() / 10);
    speed2.setProgress(game.getSecondDriver().getSpeed() / 10);

    //firstDriverImg.setImage(game.getPlayerteam().getFirstDriverImg());
    //secondDriverImg.setImage(game.getPlayerteam().getSecondDriverImg());

//    Image image1 = new Image(String.valueOf(this.getClass().getResource("/img/Drivers/" + clientController.getGame().getFirstDriver().getName())));
//    firstDriverImg.setImage(image1);
//
//    Image image2 = new Image(String.valueOf(this.getClass().getResource("/img/Drivers/" + clientController.getGame().getSecondDriver().getName())));
//    secondDriverImg.setImage(image2);


  }

  @FXML
  public void upgradeDriver() {
//    Region region = new Region();
//    region.setPrefSize(400, 400);
//    JFXPopup popup = new JFXPopup(driver1Pane, region);
//    Stage stage = new Stage();
//    stage.setHeight(500);
//    stage.setTitle("Upgrade Driver");
//    popup.show(0);
//    stage.setScene(popup);
//    stage.initModality(Modality.WINDOW_MODAL);
//    stage.initOwner(Main.primaryStage);
//    stage.sizeToScene();
//    stage.show();
  }
}

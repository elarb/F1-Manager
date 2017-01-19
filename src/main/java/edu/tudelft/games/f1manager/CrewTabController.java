package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
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
  private javafx.scene.image.ImageView firstDriverImg, secondDriverImg;


  public void injectMainController(ClientController clientController) {
    this.clientController = clientController;
  }

  public void initialize() {

  }

  public void loadData() {
    firstDriverLabel.setText(clientController.getGame().getFirstDriver().getName());
    secondDriverLabel.setText(clientController.getGame().getSecondDriver().getName());

    String rating1 = "Rating: " + clientController.getGame().getFirstDriver().getRating() + "/100";
    String rating2 = "Rating: " + clientController.getGame().getSecondDriver().getRating() + "/100";
    driverRating1.setText(rating1);
    driverRating2.setText(rating2);

    strategyinsight1.setProgress(clientController.getGame().getFirstDriver().getStrategyinsight() / 10);
    strategyinsight2.setProgress(clientController.getGame().getSecondDriver().getStrategyinsight() / 10);

    racecraft1.setProgress(clientController.getGame().getFirstDriver().getRacecraft() / 10);
    racecraft2.setProgress(clientController.getGame().getSecondDriver().getRacecraft() / 10);

    speed1.setProgress(clientController.getGame().getFirstDriver().getSpeed() / 10);
    speed2.setProgress(clientController.getGame().getSecondDriver().getSpeed() / 10);

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

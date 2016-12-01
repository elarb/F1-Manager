package edu.tudelft.games.f1manager;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

  @FXML
  private Button button;

  @FXML
  private Label label;

  @FXML
  private TextField textfield;


  public void handleButtonClick() {
    String username = textfield.getText();
    textfield.setText("");
    label.setText(username);
    Stage primStage = (Stage) label.getScene().getWindow();
    primStage.setTitle("F1 Manager - " + username);


  }

}

package edu.tudelft.games.f1manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("GUI.fxml"));
    primaryStage.setTitle("F1 Manager - User");
    primaryStage.setScene(new Scene(root, 1000, 600));
    primaryStage.setResizable(false);
    primaryStage.show();
  }


  public static void main(String[] args) {
    launch(args);
  }

}

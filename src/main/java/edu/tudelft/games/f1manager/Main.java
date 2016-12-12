package edu.tudelft.games.f1manager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class Main extends Application {

  @Override
  public void start(Stage primaryStage) throws Exception {

    Font.loadFont(getClass().getClassLoader().getResource("fonts/FuturaLT-Bold.ttf").toExternalForm(), 10);
    Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/Client.fxml"));
    primaryStage.getIcons().addAll(new Image("img/F1_logo.png"));
    primaryStage.setTitle("F1 Manager");
    primaryStage.setScene(new Scene(root, 1280, 800));
    primaryStage.setResizable(false);
    primaryStage.show();

  }

  public static void main(String[] args) {
    launch(args);
  }
}

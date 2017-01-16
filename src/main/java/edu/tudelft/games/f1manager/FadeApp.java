package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXProgressBar;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;

public class FadeApp extends Application {


  private Pane splashLayout;
  private JFXProgressBar loadProgress;
  private Label progressText;
  private Stage mainStage;
  private static final int SPLASH_WIDTH = 676;
  private static final int SPLASH_HEIGHT = 227;

  public static void main(String[] args) throws Exception {
    launch(args);
  }

  @Override
  public void init() {
    ImageView splash = new ImageView(new Image("img/F1_logo.png"));
    splash.setFitHeight(100);
    splash.setFitWidth(100);
    loadProgress = new JFXProgressBar();
    loadProgress.setPrefWidth(SPLASH_WIDTH - 20);
    progressText = new Label("Loading the game . . .");
    splashLayout = new VBox();
    splashLayout.getChildren().addAll(splash, loadProgress, progressText);
    progressText.setAlignment(Pos.CENTER);
    splashLayout.setStyle(
      "-fx-padding: 5; " +
        "-fx-background-color: cornsilk; " +
        "-fx-border-width:5; " +
        "-fx-border-color: " +
        "linear-gradient(" +
        "to bottom, " +
        "chocolate, " +
        "derive(chocolate, 50%)" +
        ");"
    );
    splashLayout.setEffect(new DropShadow());
  }

  @Override
  public void start(final Stage initStage) throws Exception {
    final Task<ObservableList<String>> driverTask = new Task<ObservableList<String>>() {
      @Override
      protected ObservableList<String> call() throws InterruptedException {
        ObservableList<String> foundDrivers =
          FXCollections.<String>observableArrayList();
        ObservableList<String> availableDrivers =
          FXCollections.observableArrayList(
            "Max", "Bob", "Bill", "Gloin", "Thorin",
            "Dwalin", "Balin", "Bifur", "Bofur",
            "Bombur", "Dori", "Nori", "Ori"
          );

        updateMessage("Loading Drivers . . .");
        for (int i = 0; i < availableDrivers.size(); i++) {
          Thread.sleep(400);
          updateProgress(i + 1, availableDrivers.size());
          String nextFriend = availableDrivers.get(i);
          foundDrivers.add(nextFriend);
          updateMessage("Finding drivers . . . found " + nextFriend);
        }
        Thread.sleep(400);
        updateMessage("All drivers found.");

        return foundDrivers;
      }
    };

    showSplash(initStage, driverTask, () -> {
      try {
        showMainStage();
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
    new Thread(driverTask).start();
  }

  private void showMainStage() throws IOException {
    Font.loadFont(getClass().getClassLoader()
      .getResource("fonts/FuturaLT-Bold.ttf").toExternalForm(), 10);

    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Client.fxml"));

    Parent root = loader.load();

    mainStage = new Stage();
    mainStage.getIcons().addAll(new Image("img/F1_logo.png"));
    mainStage.setTitle("F1 Manager");
    mainStage.setScene(new Scene(root, 1280, 800));
    mainStage.setResizable(false);
    mainStage.show();
  }

  private void showSplash(final Stage initStage, Task<?> task, InitCompletionHandler initCompletionHandler) {
    progressText.textProperty().bind(task.messageProperty());
    loadProgress.progressProperty().bind(task.progressProperty());
    task.stateProperty().addListener((observableValue, oldState, newState) -> {
      if (newState == Worker.State.SUCCEEDED) {
        loadProgress.progressProperty().unbind();
        loadProgress.setProgress(1);
        initStage.toFront();
        FadeTransition fadeSplash = new FadeTransition(Duration.seconds(1.2), splashLayout);
        fadeSplash.setFromValue(1.0);
        fadeSplash.setToValue(0.0);
        fadeSplash.setOnFinished(actionEvent -> initStage.hide());
        fadeSplash.play();

        initCompletionHandler.complete();
      }
    });

    Scene splashScene = new Scene(splashLayout, Color.TRANSPARENT);
    final Rectangle2D bounds = Screen.getPrimary().getBounds();
    initStage.setScene(splashScene);
    initStage.setX(bounds.getMinX() + bounds.getWidth() / 2 - SPLASH_WIDTH / 2);
    initStage.setY(bounds.getMinY() + bounds.getHeight() / 2 - SPLASH_HEIGHT / 2);
    initStage.initStyle(StageStyle.TRANSPARENT);
    initStage.setAlwaysOnTop(true);
    initStage.show();
  }

  public interface InitCompletionHandler {
    void complete();
  }
}

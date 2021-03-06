package edu.tudelft.games.f1manager;

import com.jfoenix.controls.JFXProgressBar;
import edu.tudelft.games.f1manager.game.Game;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;


public class App extends Application {

  static Stage mainStage;
  static Game game;
  private static final int SPLASH_WIDTH = 1280;
  private static final int SPLASH_HEIGHT = 750;
  private Pane splashLayout;
  private JFXProgressBar loadProgress;
  private Label progressText;


  public static void main(String[] args) {
    game = Game.newGame();
    launch(args);
  }

  @Override
  public void init() {
    Font.loadFont(getClass().getClassLoader()
        .getResource("fonts/Montserrat-Bold.ttf").toExternalForm(), 14);
    ImageView splash = new ImageView(new Image("img/splash.png"));
    splash.setFitHeight(SPLASH_HEIGHT);
    splash.setFitWidth(SPLASH_WIDTH);
    loadProgress = new JFXProgressBar();
    loadProgress.setPrefWidth(SPLASH_WIDTH);
    loadProgress.setMinHeight(20);
    progressText = new Label("Loading the game . . .");
    splashLayout = new VBox();
    splashLayout.setPrefSize(SPLASH_WIDTH, SPLASH_HEIGHT);
    splashLayout.getChildren().addAll(splash, loadProgress, progressText);
    progressText.setAlignment(Pos.BOTTOM_CENTER);
    splashLayout.setEffect(new DropShadow());
  }

  @Override
  public void start(Stage initStage) throws Exception {
    final Task<ObservableList<String>> driverTask = new Task<ObservableList<String>>() {
      @Override
      protected ObservableList<String> call() throws InterruptedException {
        ObservableList<String> foundDrivers =
            FXCollections.observableArrayList();
        ObservableList<String> availableDrivers =
            FXCollections.observableArrayList(
            "Daniel Ricciardo", "Daniil Kvyat", "Felipe Massa", "Felipe Nasr", "Fernando Alonso",
            "Jenson Button", "Jolyon Palmer", "Kevin Magnussen", "Kimi Räikkönen",
            "Lewis Hamilton", "Max Verstappen", "Nico Hulkenberg", "Nico Rosberg",
            "Pascal Wehrlein", "Romain Grosjean", "Sebastian Vettel", "Sebastien Loeb"
          );
        updateMessage("Loading Drivers . . .");
        for (int i = 0; i < availableDrivers.size(); i++) {
          Thread.sleep(300);
          updateProgress(i + 1, availableDrivers.size());
          String nextFriend = availableDrivers.get(i);
          foundDrivers.add(nextFriend);
          updateMessage("Finding drivers . . . found " + nextFriend);
        }
        Thread.sleep(300);
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
    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/Client.fxml"));

    mainStage = new Stage();
    Parent root = loader.load();
    mainStage.getIcons().addAll(new Image("img/F1_logo.png"));
    mainStage.setTitle("F1 Manager");
    mainStage.setScene(new Scene(root, 1280, 800));
    mainStage.setResizable(false);
    mainStage.initStyle(StageStyle.UNDECORATED);
    mainStage.setOnCloseRequest(event -> {
      //TODO: save stuff
      System.exit(1);
    });
    mainStage.show();
  }

  private void showSplash(final Stage initStage, Task<?> task,
                          App.InitCompletionHandler initCompletionHandler) {
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
    initStage.setScene(splashScene);
    initStage.initStyle(StageStyle.TRANSPARENT);
    initStage.setAlwaysOnTop(true);
    initStage.show();
  }

  /**plays the sound that has the filename that is given.
   * @param filename the name of the sound file
   */
  public static void playSound(String filename) {
    Media media = new Media(App.class.getClassLoader().getResource("Sounds/"
        + filename + ".mp3").toString());
    MediaPlayer mediaPlayer = new MediaPlayer(media);
    mediaPlayer.play();
  }

  public interface InitCompletionHandler {
    void complete();
  }

}

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class TestApplication extends Application {

    private Button button;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        //een window is een "stage" in javafx
        primaryStage.setTitle("F1 Manager - User");

        //een nieuwe button
        button = new Button();
        //text voor de button
        button.setText("Click to pick engine");

        //door lambda expression kan dit compact in een line:
        button.setOnAction(e -> System.out.println("You clicked on the button"));

        //een manier om een layout te maken, niet de enige manier
        StackPane layout = new StackPane();

        //de button toevoegen aan de layout
        layout.getChildren().add(button);

        //scene is alles in de window zelf
        Scene scene = new Scene(layout, 600, 350);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}

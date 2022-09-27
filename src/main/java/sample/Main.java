package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        /*Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/table.fxml")));
        primaryStage.setTitle("TestApp");
        primaryStage.setScene(new Scene(root, 520, 420));
        primaryStage.setResizable(false);
        primaryStage.show();*/

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/sample.fxml")));
        primaryStage.setTitle("TestApp");
        primaryStage.setScene(new Scene(root, 220, 320));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

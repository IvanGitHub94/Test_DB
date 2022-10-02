package testdb;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import testdb.service.FileService;

import java.util.Objects;

import static testdb.controller.TableController.writeToFile;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        FileService.createFileIfNotExists(FileService.createDir("/.test"), "/fromCode.json");
        FileService.createDir("/.test/users_data");

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/start-window.fxml")));
        primaryStage.setTitle("TestApp");
        primaryStage.setScene(new Scene(root, 220, 320));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() {
        writeToFile();
    }
}

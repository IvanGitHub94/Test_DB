package testdb.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import testdb.model.User;
import testdb.service.FileService;
import testdb.service.UserService;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class StartController {

    private UserService userService = new UserService();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button signInButton;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Label labelAlert;

    @FXML
    public void initialize() {
        User userWithBigFile;
        if (!userService.loginExists("user")) {
            userWithBigFile = new User("user", "123");
            userService.addNewUser(userWithBigFile.getLogin(), userWithBigFile.getPass());
        } else {
            userWithBigFile = userService.findByLoginAndPassword("user", "123");
        }
        String userRecordingsFileName = userWithBigFile.getLogin() + "_" + userWithBigFile.getRegistrationTime() + ".json";

        if (!Files.exists(Paths.get(System.getProperty("user.home"), ".test", "users_data", userRecordingsFileName))) {
            Path userRecordingsFilePath = FileService.createFileIfNotExists(Paths.get(System.getProperty("user.home"), ".test", "users_data").toString(), userRecordingsFileName);
            try {
                ClassLoader loader = Thread.currentThread().getContextClassLoader();
                InputStream stream = loader.getResourceAsStream("fillTableFile.json");

                OutputStream outStream = new FileOutputStream(userRecordingsFilePath.toFile());

                byte[] buffer = new byte[8 * 1024];
                int bytesRead;
                while ((bytesRead = stream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
                stream.close();
                outStream.close();

//                Files.copy(Paths.get("./fillTableFile.json"), userRecordingsFilePath, REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    private void singIn() {

        String login = loginField.getText().trim();
        String password = passField.getText().trim();

        if (!login.equals("") && !password.equals("")) {
            if (userService.userExists(login, password)) {
                UserService.setActiveUser(userService.findByLoginAndPassword(login, password));
                signInButton.getScene().getWindow().hide();
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/table.fxml"));

                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setResizable(false);
                stage.setTitle("DB Table");
                stage.showAndWait();
            } else {
                labelAlert.setText("???????????????????????? ?????????? ?????? ????????????.");
            }
        } else {
            labelAlert.setText("???????? ???? ?????????? ???????? ??????????????.");
        }
    }

    @FXML
    private void signUp() {
        String login = loginField.getText().trim();
        String password = passField.getText().trim();

        if (!login.equals("") && !password.equals("")) {
            if (!userService.loginExists(login)) {
                labelAlert.setText("?????????????????????? ??????????????????.");
                userService.addNewUser(login, password);
            } else {
                labelAlert.setText("?????????? ???????????????????????? ?????? ????????.");
            }
        } else {
            labelAlert.setText("???????? ???? ?????????? ???????? ??????????????.");
        }
    }

}


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

import java.io.IOException;
import java.net.URL;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

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
        if (!userService.loginExists("user")) {
            User userWithBigFile = new User("user", "123");
            userService.addNewUser(userWithBigFile.getLogin(), userWithBigFile.getPass());
            String userRecordingsFileName = userWithBigFile.getLogin() + "_" + userWithBigFile.getRegistrationTime() + ".json";
            Path userRecordingsFilePath = FileService.createFileIfNotExists(Paths.get(System.getProperty("user.home"), ".test",  "users_data").toString(), userRecordingsFileName);
            try {
                Files.copy(Paths.get("src\\main\\resources\\fillTableFile.json"), userRecordingsFilePath, REPLACE_EXISTING);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    @FXML
    private void singIn(){

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
            }
            else {
                labelAlert.setText("Неправильный логин или пароль.");
            }
        }
        else {
            labelAlert.setText("Поля не могут быть пустыми.");
        }
    }

    @FXML
    private void signUp(){
        String login = loginField.getText().trim();
        String password = passField.getText().trim();

        if (!login.equals("") && !password.equals("")) {
            if (!userService.loginExists(login)) {
                labelAlert.setText("Регистрация завершена.");
                userService.addNewUser(login, password);
            }
            else {
                labelAlert.setText("Такой пользователь уже есть.");
            }
        }
        else {
            labelAlert.setText("Поля не могут быть пустыми.");
        }
    }

}


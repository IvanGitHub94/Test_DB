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
import testdb.repository.UserRepository;
import testdb.service.FileService;
import testdb.service.UserFileService;
import testdb.service.UserService;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import static testdb.service.FileService.*;

public class StartController {

    private final UserService userService = new UserFileService();
    private final UserRepository userRepository = new UserRepository();
    List<User> availableUsers;
    User currentUser;


    public StartController() {
        availableUsers = userService.read(createFile(createDir("/.test"), "fromCode.txt"));
    }

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
    private void singIn(){

        String login = loginField.getText().trim();
        String password = passField.getText().trim();

        if (!login.equals("") && !password.equals("")) {
            if (userExists(login, password)) {
                //TODO: create user service and provide active user to file service in a more civil way

                FileService.setCurrentUser(currentUser);

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
                stage.setOnHiding(e -> writeToFile());
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
            if (!loginExists(login)) {
                User user = new User(login, password);
                labelAlert.setText("Регистрация завершена.");
                availableUsers.add(user);

                Path path = Paths.get(System.getProperty("user.home") + "/.test" + "/fromCode.txt");

                saveAll(availableUsers, path);

            }
            else {
                labelAlert.setText("Такой пользователь уже есть.");
            }
        }
        else {
            labelAlert.setText("Поля не могут быть пустыми.");
        }
    }

    private boolean userExists(String login, String password) {
        Optional<User> matchingUser = availableUsers.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPass().equals(password))
                .findFirst();
       if (matchingUser.isPresent()) {
           currentUser = matchingUser.get();
           return true;
       }
       return false;
    }

    private boolean loginExists(String login) {
        return availableUsers.stream()
                .anyMatch(user -> user.getLogin().equals(login));

    }

    private void saveAll(List<User> users, Path path) {
        userRepository.saveAll(users, path);
    }

}


package sample;

import java.io.*;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.List;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class Controller {

    private UserRepository_1 repository;


    public Controller() {
        repository = new UserRepository_1(new FileManagerMock_1());
    }

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField loginField;

    @FXML
    private PasswordField passField;

    @FXML
    private Button signInButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Label labelAlert;

    @FXML
    void initialize() {
        // действие при нажатии кнопки входа (SIGN IN)
        signInButton.setOnAction(event -> {

            String login = loginField.getText().trim();
            String password = passField.getText().trim();

            if (!login.equals("") && !password.equals("")) {
                    if (loginUser(login, password)) {
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
        });

        // действие при нажатии кнопки регистрации (SIGN UP)
        signUpButton.setOnAction(event -> {
            String login = loginField.getText().trim();
            String password = passField.getText().trim();

            if (!login.equals("") && !password.equals("")) {
                if (!signUpUser(login)) {
                    labelAlert.setText("Регистрация завершена.");

                    Path path = Paths.get(System.getProperty("user.home") + "/.test" + "/fromCode.txt");

                    save(new User(login, password));
                    saveAll(repository.getAll(), path);

                }
                else {
                    labelAlert.setText("Такой пользователь уже есть.");
                }
            }
            else {
                labelAlert.setText("Поля не могут быть пустыми.");
            }
        });
    }

    private boolean loginUser(String login, String password) {
        Optional<User> user = repository.getByLoginAndPass(login, password);
        return  user.isPresent();
    }

    private boolean signUpUser(String login) {
        return  repository.checkLogin(login);
    }


    private void save(User user){
        repository.save(user);
    }

    private void saveAll(List<User> users, Path path) {
        repository.saveAll(users, path);
    }

}


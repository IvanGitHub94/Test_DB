package testdb.controller;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import lombok.SneakyThrows;
import testdb.model.TableRecord;
import testdb.model.User;
import testdb.service.TableRecordService;
import testdb.service.UserService;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;
import java.util.List;

import static testdb.service.FileService.createDir;
import static testdb.service.FileService.createFileIfNotExists;

public class TableController implements Runnable{


    private static TableRecordService tableRecordService = new TableRecordService();
    private static ObservableList<TableRecord> userRecordings;

    @FXML
    public ProgressIndicator progressIndicator;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Label tblAlert;

    @FXML
    private TextField fieldLastName;

    @FXML
    private TextField fieldName;

    @FXML
    private TableColumn<TableRecord, String> secondPropertyColumn;

    @FXML
    private TableColumn<TableRecord, String> firstPropertyColumn;

    @FXML
    private TableView<TableRecord> tblPersons;

    @FXML
    private void editRecord() {
        if(tblPersons.getSelectionModel().isEmpty()) {
            tblAlert.setText("Выберите строку с данными.");
        }else if (fieldName.getText().isEmpty() && fieldLastName.getText().isEmpty() ) {
            tblAlert.setText("Введите данные.");
        }else{
            String strName = userRecordings.get(tblPersons.getSelectionModel().getSelectedIndex()).getFirstProperty();
            String strLastName = userRecordings.get(tblPersons.getSelectionModel().getSelectedIndex()).getSecondProperty();

            if(!fieldName.getText().isEmpty()){
                strName = fieldName.getText();
            }
            if(!fieldLastName.getText().isEmpty()){
                strLastName = fieldLastName.getText();
            }

            userRecordings.set(tblPersons.getSelectionModel().getSelectedIndex(),
                    new TableRecord(strName, strLastName));

            fieldName.clear();
            fieldLastName.clear();
            tblAlert.setText("");
        }

    }

    @FXML
    private void delete() {
        if(tblPersons.getSelectionModel().isEmpty()) {
            tblAlert.setText("Нет элементов для удаления.");
        } else {
            int selectedNumberRow = tblPersons.getSelectionModel().getFocusedIndex();
            tableRecordService.deleteRecord(userRecordings, selectedNumberRow);
            tblAlert.setText("");
        }
    }

    @FXML
    private void add() {
        if (fieldName.getText().isEmpty() || fieldLastName.getText().isEmpty()) {
            tblAlert.setText("Поля не могут быть пустыми.");
        } else {
            userRecordings.add(new TableRecord(
                            fieldName.getText(),
                            fieldLastName.getText()
                    )
            );

            fieldName.clear();
            fieldLastName.clear();
            tblAlert.setText("");
        }
    }

    @Override
    public void run() {
        progressIndicator.setVisible(true);

        User activeUser = UserService.getActiveUser();

        userRecordings = createItems(tableRecordService.findAllUserRecordings(activeUser));

        tblPersons.setItems(userRecordings);

        firstPropertyColumn.setCellValueFactory(new PropertyValueFactory<TableRecord, String>("firstProperty"));
        secondPropertyColumn.setCellValueFactory(new PropertyValueFactory<TableRecord, String>("secondProperty"));

        firstPropertyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        firstPropertyColumn.setOnEditCommit(event -> {
            event.getRowValue().setFirstProperty(event.getNewValue());
        });
        secondPropertyColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        secondPropertyColumn.setOnEditCommit(event -> {
            event.getRowValue().setSecondProperty(event.getNewValue());
        });

        Platform.runLater(() -> {
            progressIndicator.setVisible(false);
        });
    }

    @SneakyThrows
    @FXML
    void initialize() {
        new Thread(this).start();
    }

    private ObservableList<TableRecord> createItems(List<TableRecord> records) {
        ObservableList items = FXCollections.observableArrayList();
        items.addAll(records);
        return items;
    }

    public static void writeToFile() {
        User currentUser = UserService.getActiveUser();
        if (currentUser != null) {
            String dir = createDir("/.test/users_data");
            Path fileToWrite = createFileIfNotExists(dir, currentUser.getLogin() + "_" + currentUser.getRegistrationTime() + ".json");
            tableRecordService.saveAllUserRecords(currentUser, userRecordings);
        }
    }
}


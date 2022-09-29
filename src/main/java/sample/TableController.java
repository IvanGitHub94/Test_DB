package sample;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class TableController {

    static ObservableList<ModelPerson_1> people;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnAdd;

    @FXML
    private Button btnDel;

    @FXML
    private Button btnEdit;

    @FXML
    private TextField fieldLastName;

    @FXML
    private TextField fieldName;

    @FXML
    private TableColumn<ModelPerson_1, String> columnLastName;

    @FXML
    private TableColumn<ModelPerson_1, String> columnName;

    @FXML
    private TableView<ModelPerson_1> tblPersons;

    public void editRecord() {
        int selectedNumberRow = tblPersons.getSelectionModel().getFocusedIndex();
        tblPersons.getSelectionModel().select(selectedNumberRow);
        tblPersons.getFocusModel().focus(selectedNumberRow + 1);
//        tblPersons.edit(selectedNumberRow, columnName);
//        tblPersons.edit(selectedNumberRow, columnLastName);
    }

    public void delete() {
        int selectedNumberRow = tblPersons.getSelectionModel().getFocusedIndex();
        people.remove(selectedNumberRow, selectedNumberRow + 1);
    }


    public static class ModelPerson_1{

        private final SimpleStringProperty name;
        private final SimpleStringProperty lastName;

        private ModelPerson_1(String strName, String strLastName) {
            this.name = new SimpleStringProperty(strName);
            this.lastName = new SimpleStringProperty(strLastName);

        }

        public String getName() {
            return name.get();
        }

        public String getLastName() {
            return lastName.get();
        }

        public void setName(String strName) {
            name.set(strName);
        }

        public void setCountry(String strLastName) {
            lastName.set(strLastName);
        }
    }


    @FXML
    void initialize() {
        people = FXCollections.observableArrayList(
                new ModelPerson_1("Ivan", "Smirnov"),
                new ModelPerson_1("Petr", "Alekseev"),
                new ModelPerson_1("Egor", "Volkov")
        );
        tblPersons.setItems(people);

        // наполнение колонок
        columnName.setCellValueFactory(new PropertyValueFactory<ModelPerson_1, String>("name"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<ModelPerson_1, String>("lastName"));

        //////////////////////////////test
        btnAdd.setOnAction(event -> {
            if(fieldName.getText().isEmpty() || fieldLastName.getText().isEmpty() ) {
                System.out.println("Поля не могут быть пустыми."); // заглушка - исправить
            }else{
                people.add(new ModelPerson_1(
                            fieldName.getText(),
                            fieldLastName.getText()
                        )
                );
            }
        });
    }
}


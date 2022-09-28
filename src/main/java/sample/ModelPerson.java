package sample;

import javafx.beans.property.SimpleStringProperty;

public class ModelPerson{

    private final SimpleStringProperty name;
    private final SimpleStringProperty lastName;

    private ModelPerson(String strName, String strLastName) {
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

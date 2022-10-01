package testdb.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class TableRecord {

    private String firstProperty;
    private String secondProperty;

    public TableRecord(String strName, String strLastName) {
        this.firstProperty = strName;
        this.secondProperty = strLastName;

    }

    public String getFirstProperty() {
        return firstProperty;
    }

    public String getSecondProperty() {
        return secondProperty;
    }

    public void setFirstProperty(String firstProperty) {
        this.firstProperty = firstProperty;
    }

    public void setSecondProperty(String secondProperty) {
        this.secondProperty = secondProperty;
    }
}
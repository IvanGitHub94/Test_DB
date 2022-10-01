package testdb.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@NoArgsConstructor
public class User implements Serializable {
    private String login, pass;
    @Getter
    private String registrationTime;
    //private GregorianCalendar date;

    public User(String login, String pass) {
        this.login = login;
        this.pass = pass;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
        this.registrationTime = LocalDateTime.now().format(formatter);
        // date = new GregorianCalendar();
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String toDataBaseLine(){
        return String.format("%s:::%s",login, pass);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}

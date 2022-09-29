package sample;

import lombok.Getter;

import java.time.LocalDateTime;
import java.util.GregorianCalendar;

public class User {
    private String login, pass;
    @Getter
    private LocalDateTime registrationTime;
    //private GregorianCalendar date;

    public User( String login, String pass) {
        this.login = login;
        this.pass = pass;
        this.registrationTime = LocalDateTime.now();
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


    static User fromDataBaseLine(String str){
        String[] vars = str.split(":::");
        return  new User(vars[0], vars[1]);
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}

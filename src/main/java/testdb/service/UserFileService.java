package testdb.service;

import testdb.model.User;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class UserFileService implements UserService {


    @Override
    public List<User> read(Path path) {

        List<User> userLists = new ArrayList<>();
        String s = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path))) ) {
            if (bufferedReader.ready()) {
                while ((s = bufferedReader.readLine()) != null) {
                    userLists.add(fromDataBaseLine(s));
                }
            }
            else System.out.println("Файл пуст.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userLists;
    }

    @Override
    public void saveAll(List<User> users, Path path) {
        // для перезаписи в файл

        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(String.valueOf(path), true))) {
            // очистка целевого файла
            PrintWriter printWriter = new PrintWriter(String.valueOf(path));
            printWriter.close();
            // костыль

            for (User user : users) {
                bufferedWriter.write(user.getLogin() + ":::" + user.getPass() + "\n");
            }
            bufferedWriter.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private User fromDataBaseLine(String str){
        String[] vars = str.split(":::");
        return  new User(vars[0], vars[1]);
    }
}

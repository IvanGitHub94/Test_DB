package sample;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class CreatorList {

/*    public static String createDir() {
        // создание директории .test в корне папки пользователя (если такой еще нет)
        String dir = System.getProperty("user.home");
        Path path = Paths.get(dir + "/.test");
        try {
            Files.createDirectories(path);
            System.out.println("Directory created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage()); //!!! исправить sout
        }
        return path.toString();
    }

    public static Path createFileOfUsers(String pathDir) {
        // создание файла .txt (если такого еще нет)
        Path usersFile = Paths.get(pathDir + "/fromCode.txt");
        try {
            if (!Files.exists(usersFile)) {
                Files.createFile(usersFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFile;
    }*/

    public static List<User> usersFromFile(Path path) {

        List<User> userLists = new ArrayList<>();
        String s = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(String.valueOf(path))) ) {
            if (bufferedReader.ready()) {
                while ((s = bufferedReader.readLine()) != null) {
                    userLists.add(User.fromDataBaseLine(s));
                }
            }
            else System.out.println("Файл пуст.");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userLists;
    }
}

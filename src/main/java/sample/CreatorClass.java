package sample;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class CreatorClass {

    public static String createDir() {
        // создание директории .test в корне папки пользователя
        // (если такой еще нет)
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
        // создание файла .properties (если такого еще нет)
        // для хранения УЗ пользователей (пара ключ(логин)-значение(пароль))
        // Path propertiesFile = Paths.get(pathDir + "/keyUsers.properties");
        Path usersFile = Paths.get(pathDir + "/fromCode.txt");
        try {
            if (!Files.exists(usersFile)) {
                Files.createFile(usersFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFile;
        //createMap(propertiesFile);
    }

    /*public static List<User> createList(Path propFile) {
        // чтение файла .properties и наполнение парами Map
        List<User> accounts = new ArrayList<>();

        try {
            FileInputStream fileInputStream = new FileInputStream(String.valueOf(propFile));

            if(Files.size(propFile) != 0) {
                for (Object key : prop.keySet()) {
                    //System.out.println(key + ": " + prop.getProperty(key.toString()));
                    accounts.put(key.toString(), prop.getProperty(key.toString()));
                }
            }
            else System.out.println("Файл пуст.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }*/

    /*public static Map<String, String> createMap(Path propFile) {
        // чтение файла .properties и наполнение парами Map
        Map<String, String> accounts = new HashMap<>();

        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(String.valueOf(propFile));
            prop.load(fileInputStream);

            if(Files.size(propFile) != 0) {
                for (Object key : prop.keySet()) {
                    //System.out.println(key + ": " + prop.getProperty(key.toString()));
                    accounts.put(key.toString(), prop.getProperty(key.toString()));
                }
            }
            else System.out.println("Файл пуст.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        return accounts;
    }*/
}

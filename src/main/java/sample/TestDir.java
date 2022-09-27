package sample;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestDir {

    public static void main(String[] args) {

    }

    /*public static void main(String[] args) {
        String dir = createDir();
        System.out.println(dir);

        createFileOfUsers(dir);
    }

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

    public static void createFileOfUsers(String path) {
        // создание файла .properties (если такого еще нет)
        // для хранения УЗ пользователей (пара ключ(логин)-значение(пароль))
            Path propertiesFile = Paths.get(path + "/keyUsers.properties");
            try {
                if (!Files.exists(propertiesFile)) {
                    Files.createFile(propertiesFile);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            createMap(propertiesFile);
    }

    public static void  *//*Map<String, String>*//* createMap(Path propFile) {
        // чтение файла .properties и наполнение парами Map
        Map<String, String> accounts = new HashMap<>();

        Properties prop = new Properties();
        try {
            FileInputStream fileInputStream = new FileInputStream(String.valueOf(propFile));
            prop.load(fileInputStream);

            if(Files.size(propFile) != 0) {
                for (Object key : prop.keySet()) {
                    System.out.println(key + ": " + prop.getProperty(key.toString()));
                    accounts.put(key.toString(), prop.getProperty(key.toString()));
                }
            }
            else System.out.println("Файл пуст.");

        } catch (IOException e) {
            e.printStackTrace();
        }

        String l = "admin";
        String p = "user";
        System.out.println("Есть ли такой логин в памяти? - " + accounts.containsKey(l));
    }*/
}

package testdb.service;

import lombok.Setter;
import testdb.model.User;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    @Setter
    private static User currentUser;

    public static void writeToFile() {
        String dir = createDir("/.test/users_data");
        Path fileToWrite = createFileIfNotExists(dir, currentUser.getLogin() + "_" + currentUser.getRegistrationTime() + ".json");

    };

    public static String createDir(String pathInsideUserDir) {
        // создание директории .test в корне папки пользователя (если такой еще нет)
        String dir = System.getProperty("user.home");
        Path path = Paths.get(dir + pathInsideUserDir);
        try {
            Files.createDirectories(path);
            System.out.println("Directory created!");
        } catch (IOException e) {
            System.err.println("Failed to create directory!" + e.getMessage()); //!!! исправить sout
        }
        return path.toString();
    }

    public static Path createFileIfNotExists(String pathDir, String filename) {
        // создание файла .txt (если такого еще нет)
        Path usersFile = Paths.get(pathDir + "/" + filename);
        try {
            if (!Files.exists(usersFile)) {
                Files.createFile(usersFile);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFile;
    }
}

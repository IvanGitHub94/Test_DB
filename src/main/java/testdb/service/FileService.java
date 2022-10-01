package testdb.service;
import lombok.Setter;
import testdb.model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileService {

    @Setter
    private static User currentUser;

    public static String createDir(String pathInsideUserDir) {
        // создание директории .test в корне папки пользователя (если такой еще нет)
        Path path = Paths.get(System.getProperty("user.home") + pathInsideUserDir);
        try {
            Files.createDirectories(path);
        } catch (IOException e) {
        }
        return path.toString();
    }

    public static Path createFileIfNotExists(String pathDir, String filename) {
        // создание файла (если такого еще нет)
        Path usersFile = Paths.get(pathDir, filename);
        try {
            if (!Files.exists(usersFile)) {
                Files.createFile(usersFile);
                //
                    try (FileWriter file = new FileWriter(String.valueOf(usersFile))) {
                        file.write("[]");
                    }
                //
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersFile;
    }
}

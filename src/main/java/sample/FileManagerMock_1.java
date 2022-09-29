package sample;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.List;

import static sample.FileService.createDir;
import static sample.FileService.createFile;

public class FileManagerMock_1 implements IFileManager{

    private  List<User> users;

    public FileManagerMock_1() {
        users = CreatorList.usersFromFile(createFile(createDir("/.test"), "fromCode.txt"));
        /*users = new ArrayList<User>();
        users.add( new User("admin", "12345"));
        users.add( new User("user", "1111"));*/
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public List<User> getAll() {

        return users;
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
}


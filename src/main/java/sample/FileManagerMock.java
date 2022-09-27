package sample;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FileManagerMock implements IFileManager{

    private  List<User> users;

    public FileManagerMock() {

        users = new ArrayList<User>();
        users.add( new User("admin", "12345"));
        users.add( new User("user", "1111"));
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

    }
}

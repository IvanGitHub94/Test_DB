package sample;

import java.nio.file.Path;
import java.util.List;

public interface IFileManager {

    void save(User user);
    List<User> getAll();
    void saveAll(List<User> users, Path path);
}

package testdb.service;

import testdb.model.User;

import java.nio.file.Path;
import java.util.List;

public interface UserService {

    List<User> read(Path path);
    void saveAll(List<User> users, Path path);
}

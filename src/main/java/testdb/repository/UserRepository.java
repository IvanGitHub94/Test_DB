package testdb.repository;

import testdb.model.User;

import java.nio.file.Path;
import java.util.List;

public interface UserRepository {
    public List<User> findAll();

    void saveAll(List<User> users);
}

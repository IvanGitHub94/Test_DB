package testdb.repository;

import testdb.model.User;
import java.util.List;

public interface UserRepository {
    List<User> findAll();

    void saveAll(List<User> users);
}

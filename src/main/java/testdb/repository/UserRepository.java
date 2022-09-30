package testdb.repository;

import testdb.model.User;
import testdb.service.UserFileService;

import java.nio.file.Path;
import java.util.List;

public class UserRepository {

    private UserFileService fileService = new UserFileService();

    public void saveAll(List<User> users, Path path) {
        fileService.saveAll(users, path);
    }

}


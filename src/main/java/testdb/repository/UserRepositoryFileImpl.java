package testdb.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import testdb.model.User;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserRepositoryFileImpl implements UserRepository {
    private final ObjectMapper mapper = new ObjectMapper();
    private static final Path USER_FILE_PATH = Paths.get(System.getProperty("user.home") + "/.test" + "/fromCode.json");

    @Override
    public List<User> findAll() {
        User[] availableUsers = new User[]{};
        try {
            availableUsers = mapper.readValue(USER_FILE_PATH.toFile(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(Arrays.asList(availableUsers));
    }

    @Override
    public void saveAll(List<User> users) {
        User[] usersToSave = users.toArray(new User[0]);

        try {
            mapper.writeValue(USER_FILE_PATH.toFile(), usersToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


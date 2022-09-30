package testdb.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import testdb.model.User;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserFileService implements UserService {

    private final ObjectMapper mapper = new ObjectMapper();


    @Override
    public List<User> read(Path path) {
        User[] availableUsers = new User[]{};
        try {
           availableUsers = mapper.readValue(path.toFile(), User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>(Arrays.asList(availableUsers));
    }

    @Override
    public void saveAll(List<User> users, Path path) {
        // для перезаписи в файл

        User[] usersToSave = users.toArray(new User[0]);

        try {
            mapper.writeValue(path.toFile(), usersToSave);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

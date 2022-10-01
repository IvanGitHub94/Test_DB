package testdb.service;

import lombok.Getter;
import testdb.model.User;
import testdb.repository.UserRepository;
import testdb.repository.UserRepositoryFileImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    @Getter
    List<User> availableUsers = new ArrayList<>();
    private UserRepository userRepository = new UserRepositoryFileImpl();

    @Getter
    private static User activeUser;

    public UserService() {
        this.availableUsers = userRepository.findAll();
    }

    public boolean loginExists(String login) {
        return availableUsers.stream()
                .anyMatch(user -> user.getLogin().equals(login));
    }

    public void addNewUser(String login, String password) {
        User user = new User(login, password);
        availableUsers.add(user);
        userRepository.saveAll(availableUsers);
    }

    public boolean userExists(String login, String password) {
        Optional<User> matchingUser = availableUsers.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPass().equals(password))
                .findFirst();
        return matchingUser.isPresent();
    }

    public User findByLoginAndPassword(String login, String password) {
        return availableUsers.stream()
                .filter(user -> user.getLogin().equals(login) && user.getPass().equals(password))
                .findFirst().get();
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }
}

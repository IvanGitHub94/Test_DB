package testdb.service;

import lombok.Getter;
import testdb.model.User;
import testdb.repository.UserRepository;
import testdb.repository.UserRepositoryFileImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {

    private List<User> availableUsers = new ArrayList<>(); // решить делать ли приватным лист
    private UserRepository userRepository = new UserRepositoryFileImpl();

    private static User activeUser;

    public UserService() {
        this.availableUsers = userRepository.findAll();
    }

    public boolean loginExists(String login) {
        return availableUsers.stream()
                .anyMatch(user -> user.getLogin().equalsIgnoreCase(login)); // добавлено игнорирование регистра для логина
    }

    public void addNewUser(String login, String password) {
        User user = new User(login, password);
        availableUsers.add(user);
        userRepository.saveAll(availableUsers);
    }

    public boolean userExists(String login, String password) {
        Optional<User> matchingUser = availableUsers.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login) && user.getPass().equals(password)) // добавлено игнорирование регистра для логина
                .findFirst();
        return matchingUser.isPresent();
    }

    public User findByLoginAndPassword(String login, String password) {
        return availableUsers.stream()
                .filter(user -> user.getLogin().equalsIgnoreCase(login) && user.getPass().equals(password)) // добавлено игнорирование регистра для логина
                .findFirst().get();
    }

    public static void setActiveUser(User user) {
        activeUser = user;
    }

    public List<User> getAvailableUsers() {
        return availableUsers;
    }

    public static User getActiveUser() {
        return activeUser;
    }
}

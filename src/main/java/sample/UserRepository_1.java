package sample;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class UserRepository_1 {

    private IFileManager manager;

    public UserRepository_1(IFileManager manager) {
        this.manager = manager;
    }

    public void save(User user) {
        manager.save(user);
    }

    public void saveAll(List<User> users, Path path) {
        manager.saveAll(users, path);
    }

    public List<User> getAll() {
        return manager.getAll();
    }


    public Optional<User> getByLoginAndPass(String login, String pass) {
        return getAll().stream().filter(user1 -> user1.getLogin().equals(login) && user1.getPass().equals(pass)).findAny();

    }

    public boolean checkLogin(String login){
        return getAll().stream().anyMatch(u -> u.getLogin().equals(login));
    }

}


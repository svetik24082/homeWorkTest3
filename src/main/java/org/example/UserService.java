package org.example;

import org.example.exception.UserNonUniqueException;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        User user = new User("hello", "12345");

    }

    public List<String> getUsersLogins() {
        try {
            Collection<User> users = this.userRepository
                    .getAllUsers();
            if (users == null) {
                return null;
            }
            return users.stream()
                    .map(User::getLogin)
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            return null;
        }
    }

    public void addUser(String userLogin, String userPassword) throws UserNonUniqueException {
        User user = new User(userLogin, userPassword);
        if (userLogin == null || userLogin.isBlank() &&
                userPassword == null || userPassword.isBlank()) {
            throw new IllegalArgumentException("Поля логин и пароль не могут быть пустыми!");
        }
        boolean userExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(t -> t.equals(user));
        if (userExist) {
            throw new UserNonUniqueException(" Пользователь с таким логином  уже существует!");
        }
        this.userRepository.addUser(user);

    }

    public String checkLogin(String login, String password) {
        User user = new User(login, password);
        boolean loginExist = this.userRepository
                .getAllUsers()
                .stream()
                .anyMatch(t -> t.equals(user));
        if (loginExist) {
            return String.valueOf(true);
        } else {

        }
        return String.valueOf(false);
    }

    public String getAllLogins() {
        Collection<User> users = this.userRepository
                .getAllUsers();
        if (users == null) {
            return null;
        }
        return users
                .stream()
                .map(User::getLogin)
                .toString();

    }
}


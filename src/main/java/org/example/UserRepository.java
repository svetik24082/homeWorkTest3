package org.example;

import java.util.*;
import java.util.stream.Stream;

public class UserRepository {
    public List<User> users = new ArrayList<>();
    User user = new User("hello", "12345");

    public Collection<User> getAllUsers() {

        return users; // получить всех пользователей

    }

    public Stream<User> getUserByLogin(String login) { // поиск пользователя по логину
        return this.users.stream()
                .filter(user -> user.getLogin()
                        .equals(login));

    }

    public Optional<User> getUserByParameters(String login, String password) { // поиск по логину и паролю
        return this.users.stream().filter(user -> user.getLogin()
                .equals(login)).filter(user -> user.getPassword()
                .equals(password)).findAny();

    }

    public User addUser(User user) { // добавить пользователя
        this.users.add(user);
        return user;
    }

}

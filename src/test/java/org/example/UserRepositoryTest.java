package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

public class UserRepositoryTest {
    UserRepository userRepository = new UserRepository();
    User user;

    @BeforeEach
    public void setUp() {
        user = new User("hello", "567");

    }

    @Test
    void getEmptyUserList() {
        boolean actualResult = userRepository.getAllUsers().isEmpty();
        Assertions.assertEquals(userRepository.getAllUsers(), Collections.EMPTY_LIST);
    }

    @Test
    void getNotEmptyUserList() { // получить не пустой список пользователей
        userRepository.addUser(user);
        Assertions.assertEquals(List.of(user), userRepository.getAllUsers());
    }

    @Test
    void findUserByLoginIfSuchUserExist() { //найдите Пользователя По Логину, Если Такой Пользователь Существует//userRepository.addUser(user);
        String actualResult = userRepository.user.getLogin();
        Assertions.assertEquals(user.getLogin(), actualResult);

    }
}

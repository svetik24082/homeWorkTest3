package org.example;


import org.example.exception.UserNonUniqueException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.NoInteractions;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;


import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserService userService;

    @Test
    void whenCorrectUserIsAddedThenAddUserIsCalledFromRepo() throws UserNonUniqueException { //когда Добавлен Правильный Пользователь, То Add User Вызывается Из Репозитория
        when(userRepository.getAllUsers()).thenReturn(List.of());
        when(userRepository.addUser(any(User.class))).thenReturn(null);
        userService.addUser("hello", "456");
        verify(userRepository).addUser(any());

    }

    @Test
    void whenRepositoryReturnsNullThenSomethingHappened() { // когда репозиторий возвращает нуль, тогда что-то происходит
        when(userRepository.getAllUsers()).thenReturn(null);
        assertThat(userService.getAllLogins()).isEqualTo(null);
    }

    @Test
    void gettingAnEmptyListOfUsers() { // получение Пустого Списка Пользователей
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("hello", "456")));
        assertThat(userService.getAllLogins());

    }

    @Test
    void whenInvalidUserIsPassedThenServiceThrowsException() {
        assertThatThrownBy(() -> userService.addUser("", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Поля логин и пароль не могут быть пустыми!");
        verify(userRepository, new NoInteractions()).getAllUsers();
        verify(userRepository, new NoInteractions()).addUser(any());
        verify(userRepository, new NoInteractions()).getUserByLogin(any());
        verify(userRepository, new NoInteractions()).getUserByParameters("hello", "456");

    }

    @Test
    void whenExistingUserIsPassedThenServiceThrowsException() {
        when(userRepository.getAllUsers()).thenReturn(List.of(new User("hello", "456")));
        assertThatThrownBy(() -> userService.addUser("hello", "456"))
                .isInstanceOf(UserNonUniqueException.class)
                .hasMessage(" Пользователь с таким логином  уже существует!");
    }

    @Test
    void whenNetworkExceptionIsRaisedThenServiceReturnsZero() {
        when(userRepository.getAllUsers()).thenThrow(new RuntimeException());
        assertThat(userService.getUsersLogins()).isEqualTo(null);
    }


    @BeforeEach
    void setUp() {
        User user = new User("hello", "456");
    }


}







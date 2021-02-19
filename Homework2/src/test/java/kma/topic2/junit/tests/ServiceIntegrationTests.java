package kma.topic2.junit.tests;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.exceptions.UserNotFoundException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.service.UserService;
import kma.topic2.junit.validation.UserValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ServiceIntegrationTests {

    @Autowired
    private UserService userService;

    @SpyBean
    private UserRepository userRepository;

    @SpyBean
    private UserValidator userValidator;

    @Test
    public void createValidUser_IsSuccess() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qwerty5")
                .build();

        userService.createNewUser(newUser);

        Mockito.verify(userValidator).validateNewUser(newUser);
        Mockito.verify(userRepository).isLoginExists(login);
        Mockito.verify(userRepository).saveNewUser(newUser);

        var savedUser = userService.getUserByLogin(login);

        assertThat(savedUser.getLogin()).isEqualTo(login);
        assertThat(savedUser.getPassword()).isEqualTo(newUser.getPassword());
        assertThat(savedUser.getFullName()).isEqualTo(newUser.getFullName());
    }

    @Test
    public void createInvalidUser_Error() {
        var login = "abc2@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qw")
                .build();

        assertThatThrownBy(() -> userService.createNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class);

        Mockito.verify(userValidator).validateNewUser(newUser);
        Mockito.verify(userRepository, Mockito.never()).saveNewUser(newUser);

        assertThatThrownBy(() -> userService.getUserByLogin(login))
                .isInstanceOf(UserNotFoundException.class);
    }

    @Test
    public void createExistingLogin_Error() {
        var login = "abc3@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qwerty")
                .build();

        userService.createNewUser(newUser);
        Mockito.verify(userRepository).saveNewUser(newUser);

        Mockito.reset(userRepository);

        assertThatThrownBy(() -> userService.createNewUser(newUser))
                .isInstanceOf(LoginExistsException.class);

        Mockito.verify(userRepository, Mockito.never()).saveNewUser(newUser);
    }

    @Test
    public void getNonExistingUser_Error() {
        var login = "abc4@email.com";

        assertThatThrownBy(() -> userService.getUserByLogin(login))
                .isInstanceOf(UserNotFoundException.class)
                .hasMessageContaining(login);


        Mockito.verify(userRepository).getUserByLogin(login);
    }
}

package kma.topic2.junit.tests;

import kma.topic2.junit.exceptions.ConstraintViolationException;
import kma.topic2.junit.exceptions.LoginExistsException;
import kma.topic2.junit.model.NewUser;
import kma.topic2.junit.repository.UserRepository;
import kma.topic2.junit.validation.UserValidator;
import org.assertj.core.api.InstanceOfAssertFactories;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ValidationTests {

    @Mock
    private UserRepository mockRepository;

    private UserValidator validator;

    @BeforeEach
    void initTestCase() {
        validator = new UserValidator(mockRepository);
    }

    @Test
    public void validUser_letterPassword_success() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qwerty")
                .build();

        validator.validateNewUser(newUser);
    }

    @Test
    public void validUser_digitsPassword_success() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("12345")
                .build();

        validator.validateNewUser(newUser);
    }

    @Test
    public void validUser_letterDigitsPassword_success() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qw32ty")
                .build();

        validator.validateNewUser(newUser);
    }

    @Test
    void invalidUser_shortPassword_fail() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qw")
                .build();

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class)
                .extracting("errors", InstanceOfAssertFactories.ITERABLE)
                .contains("Password has invalid size");
    }

    @Test
    void invalidUser_longPassword_fail() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("12345678")
                .build();

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class)
                .extracting("errors", InstanceOfAssertFactories.ITERABLE)
                .contains("Password has invalid size");
    }

    @Test
    void invalidUser_wrongPasswordFormat_fail() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qwert$")
                .build();

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class)
                .extracting("errors", InstanceOfAssertFactories.ITERABLE)
                .contains("Password doesn't match regex");
    }

    //It probably should not accept that
    @Test
    void invalidUser_nullLogin_fail(){
        var newUser = NewUser.builder()
                .login(null)
                .fullName("Cool Name")
                .password("qwerty")
                .build();

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class);
    }

    //Also this probably should throw ConstraintViolationException
    @Test
    void invalidUser_nullPassword_fail(){
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password(null)
                .build();

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(ConstraintViolationException.class);
    }



    @Test
    void validUser_existingEmail_fail() {
        var login = "abc@email.com";
        var newUser = NewUser.builder()
                .login(login)
                .fullName("Cool Name")
                .password("qwerty")
                .build();

        Mockito.when(mockRepository.isLoginExists(login)).thenReturn(true);

        assertThatThrownBy(() -> validator.validateNewUser(newUser))
                .isInstanceOf(LoginExistsException.class);
    }

}

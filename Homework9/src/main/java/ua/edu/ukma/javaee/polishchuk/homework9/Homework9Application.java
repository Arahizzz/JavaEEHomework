package ua.edu.ukma.javaee.polishchuk.homework9;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class Homework9Application {

    public static void main(String[] args) {
        SpringApplication.run(Homework9Application.class, args);
    }
}

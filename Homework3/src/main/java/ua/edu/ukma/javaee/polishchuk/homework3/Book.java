package ua.edu.ukma.javaee.polishchuk.homework3;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Book {
    private final String Name;
    private final String Author;
    private final String Isbn;
}

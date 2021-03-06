package ua.edu.ukma.javaee.polishchuk.homework4;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode
public class Book {
    private final String Name;
    private final String Author;
    private final String Isbn;
}

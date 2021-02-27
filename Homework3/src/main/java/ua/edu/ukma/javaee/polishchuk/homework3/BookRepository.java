package ua.edu.ukma.javaee.polishchuk.homework3;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {
    private final List<Book> books = new ArrayList<>();

    public void addBook(Book book){
        books.add(book);
    }

    public Iterable<Book> allBooks(){
        return books;
    }
}

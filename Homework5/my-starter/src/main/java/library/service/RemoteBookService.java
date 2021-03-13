package library.service;

import library.client.BooksClient;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class RemoteBookService implements InitializingBean, BookService {

    private final BooksClient libraryClient;

    public RemoteBookService(BooksClient libraryClient) {
        this.libraryClient = libraryClient;
    }

    public void printInfo() {
        System.out.println("Bean " + libraryClient.getClass().getSimpleName() + " was created so " + this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }

    @Override
    public List<String> getBookTitles() {
        return libraryClient.fetchBooks();
    }
}


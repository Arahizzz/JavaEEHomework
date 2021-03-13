package library.client;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class GraphQlBooksClient implements BooksClient, InitializingBean {
    @Override
    public List<String> fetchBooks() {
        return List.of("Moby Dick", "War and Peace");
    }

    public void printInfo() {
        System.out.println("Property application.libraryClient is set to graphql so " + this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }
}

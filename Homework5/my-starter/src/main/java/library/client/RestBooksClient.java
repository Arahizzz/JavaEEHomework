package library.client;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class RestBooksClient implements InitializingBean, BooksClient {
    public void printInfo() {
        System.out.println("Property application.libraryClient is set to rest so " + this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }

    public List<String> fetchBooks(){
        return List.of("Don Quixote", "The Great Gatsby");
    }
}

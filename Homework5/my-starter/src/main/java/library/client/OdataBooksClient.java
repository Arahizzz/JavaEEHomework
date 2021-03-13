package library.client;

import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class OdataBooksClient implements BooksClient, InitializingBean {
    @Override
    public List<String> fetchBooks() {
        return List.of("Ulysses", "In Search of Lost Time");
    }

    public void printInfo() {
        System.out.println("Property application.libraryClient is set to db so " + this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }
}

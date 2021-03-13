package library.service;

import library.client.BooksClient;
import org.springframework.beans.factory.InitializingBean;

import java.util.List;

public class LocalBookService implements InitializingBean, BookService {
    public void printInfo() {
        System.out.println("Bean " + BooksClient.class.getSimpleName() + " wasn't created so " + this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
    }

    @Override
    public List<String> getBookTitles() {
        return List.of("Perfume");
    }
}


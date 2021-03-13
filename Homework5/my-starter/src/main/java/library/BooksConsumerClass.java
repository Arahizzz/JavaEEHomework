package library;

import library.service.BookService;
import org.springframework.beans.factory.InitializingBean;

public class BooksConsumerClass implements InitializingBean {

    private final BookService bookService;

    public BooksConsumerClass(BookService bookService) {
        this.bookService = bookService;
    }

    public void printBooks(){
        System.out.println("Books list: ");
        for (var book : bookService.getBookTitles()){
            System.out.println(book);
        }
    }

    public void printInfo() {
        System.out.println(this.getClass().getSimpleName() + " is created.");
    }

    @Override
    public void afterPropertiesSet() {
        printInfo();
        printBooks();
    }
}

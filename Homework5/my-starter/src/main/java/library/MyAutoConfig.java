package library;

import library.client.BooksClient;
import library.client.OdataBooksClient;
import library.client.GraphQlBooksClient;
import library.client.RestBooksClient;
import library.service.BookService;
import library.service.RemoteBookService;
import library.service.LocalBookService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyAutoConfig {
    @Bean
    MyClass myLibraryClass() {
        return new MyClass();
    }

    @Bean
    BooksConsumerClass booksConsumerClass(BookService bookService){
        return new BooksConsumerClass(bookService);
    }

    @Bean
    @ConditionalOnProperty(prefix = "application", name = "libraryClient", havingValue = "rest")
    BooksClient propertyHttpClass(){
        return new RestBooksClient();
    }

    @Bean
    @ConditionalOnProperty(prefix = "application", name = "libraryClient", havingValue = "odata")
    BooksClient propertyDbClass(){
        return new OdataBooksClient();
    }

    @Bean
    @ConditionalOnProperty(prefix = "application", name = "libraryClient", havingValue = "graphql")
    BooksClient propertyGraphQlClass(){
        return new GraphQlBooksClient();
    }

    @Bean
    @ConditionalOnBean(BooksClient.class)
    BookService presentBeanLibraryClass(BooksClient libraryClient){
        return new RemoteBookService(libraryClient);
    }

    @Bean
    @ConditionalOnMissingBean(BooksClient.class)
    BookService missingBeanLibraryClass(){
        return new LocalBookService();
    }
}
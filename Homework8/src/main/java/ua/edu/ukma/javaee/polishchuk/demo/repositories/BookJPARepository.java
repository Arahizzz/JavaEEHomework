package ua.edu.ukma.javaee.polishchuk.demo.repositories;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import ua.edu.ukma.javaee.polishchuk.demo.models.Book;

public interface BookJPARepository extends PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}

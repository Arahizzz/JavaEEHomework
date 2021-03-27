package ua.ukma.edu.javaee.polishchuk.homework7;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BookJPARepository extends PagingAndSortingRepository<Book, Integer>, JpaSpecificationExecutor<Book> {
}

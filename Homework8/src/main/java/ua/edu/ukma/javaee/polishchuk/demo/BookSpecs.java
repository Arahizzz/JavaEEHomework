package ua.edu.ukma.javaee.polishchuk.demo;

import org.springframework.data.jpa.domain.Specification;
import ua.edu.ukma.javaee.polishchuk.demo.models.Book;

import java.util.Locale;

public class BookSpecs {
    public static Specification<Book> searchBooksSpec(String name) {
        return (root, query, criteriaBuilder) -> {
            var like = '%' + name.toLowerCase(Locale.ROOT) + '%';
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("Name")), like),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("Isbn")), like));
        };
    }
}

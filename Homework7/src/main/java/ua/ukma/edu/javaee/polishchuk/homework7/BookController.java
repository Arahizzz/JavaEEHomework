package ua.ukma.edu.javaee.polishchuk.homework7;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookJPARepository repository;

    @RequestMapping("/")
    public String allBooks(Model model, final HttpServletResponse response){
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        var books = repository.findAll(PageRequest.of(0, 5));
        model.addAttribute("books", new PageResponse<>(books.getContent(), books.getTotalPages()));
        return "books";
    }

    @RequestMapping("/book/{id}")
    public String book(Model model, @PathVariable String id){
        var book = repository.findById(Integer.parseInt(id));
        if (book.isEmpty())
            throw new BookNotFoundException();
        model.addAttribute("book", book.get());
        return "book";
    }

}

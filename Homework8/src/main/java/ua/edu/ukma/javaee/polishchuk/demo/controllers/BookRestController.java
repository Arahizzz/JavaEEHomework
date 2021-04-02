package ua.edu.ukma.javaee.polishchuk.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.javaee.polishchuk.demo.BookSpecs;
import ua.edu.ukma.javaee.polishchuk.demo.services.BookValidator;
import ua.edu.ukma.javaee.polishchuk.demo.models.Book;
import ua.edu.ukma.javaee.polishchuk.demo.models.BookForm;
import ua.edu.ukma.javaee.polishchuk.demo.models.PageResponse;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.BookJPARepository;

import javax.xml.bind.ValidationException;

@RequiredArgsConstructor
@RestController
public class BookRestController {
    private final BookJPARepository repository;
    private final BookValidator validator;


    @ResponseBody
    @GetMapping("/bookList")
    public PageResponse<Book> bookList(@RequestParam String query, @RequestParam Integer page){
        Page<Book> books = query.isEmpty()
                ? repository.findAll(PageRequest.of(page-1, 5))
                : repository.findAll(BookSpecs.searchBooksSpec(query), PageRequest.of(page-1, 5));
        return new PageResponse<>(books.getContent(), books.getTotalPages());
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@RequestBody final BookForm form){
        try {
            validator.validate(form);
            repository.save(new Book(null, form.getName(), form.getAuthor(), form.getIsbn()));
            return new ResponseEntity<>("Added successfully!", HttpStatus.CREATED);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

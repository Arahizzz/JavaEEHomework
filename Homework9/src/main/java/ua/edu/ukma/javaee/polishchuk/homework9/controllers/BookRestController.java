package ua.edu.ukma.javaee.polishchuk.homework9.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ua.edu.ukma.javaee.polishchuk.homework9.BookSpecs;
import ua.edu.ukma.javaee.polishchuk.homework9.models.Book;
import ua.edu.ukma.javaee.polishchuk.homework9.models.BookForm;
import ua.edu.ukma.javaee.polishchuk.homework9.models.PageResponse;
import ua.edu.ukma.javaee.polishchuk.homework9.repositories.BookJPARepository;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@Validated
public class BookRestController {
    private final BookJPARepository repository;


    @ResponseBody
    @GetMapping("/bookList")
    public PageResponse<Book> bookList(@RequestParam String query, @RequestParam Integer page) {
        Page<Book> books = query.isEmpty()
                ? repository.findAll(PageRequest.of(page - 1, 5))
                : repository.findAll(BookSpecs.searchBooksSpec(query), PageRequest.of(page - 1, 5));
        return new PageResponse<>(books.getContent(), books.getTotalPages());
    }

    @PostMapping("/addBook")
    public ResponseEntity<String> addBook(@Valid @RequestBody final BookForm form) {
        repository.save(new Book(null, form.getName(), form.getAuthor(), form.getIsbn()));
        return new ResponseEntity<>("Added successfully!", HttpStatus.CREATED);
    }
}

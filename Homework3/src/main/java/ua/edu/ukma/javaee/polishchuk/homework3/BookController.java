package ua.edu.ukma.javaee.polishchuk.homework3;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BookController {

    private final BookRepository repository;

    public BookController(BookRepository repository) {
        this.repository = repository;
    }

    @RequestMapping("/books")
    public String allBooks(Model model){
        var books = repository.allBooks();
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/addBook")
    public String addBook(@ModelAttribute BookForm form){
        repository.addBook(new Book(form.getName(), form.getAuthor(), form.getIsbn()));
        return "redirect:/books";
    }
}

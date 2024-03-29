package ua.edu.ukma.javaee.polishchuk.demo.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.ukma.javaee.polishchuk.demo.BookNotFoundException;
import ua.edu.ukma.javaee.polishchuk.demo.models.PageResponse;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.BookJPARepository;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.WishlistRepository;
import ua.edu.ukma.javaee.polishchuk.demo.services.WishlistService;

import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class BookController {

    private final BookJPARepository books;
    private final WishlistService wishlists;

    @RequestMapping("/")
    public String allBooks(Model model, final HttpServletResponse response){
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Pragma", "no-cache");
        response.addHeader("Expires", "0");
        var books = this.books.findAll(PageRequest.of(0, 5));
        model.addAttribute("books", new PageResponse<>(books.getContent(), books.getTotalPages()));
        return "books";
    }

    @RequestMapping("/book/{id}")
    public String book(Model model, @PathVariable String id){
        var intId = Integer.parseInt(id);
        var book = books.findById(Integer.parseInt(id));
        if (book.isEmpty())
            throw new BookNotFoundException();
        model.addAttribute("book", book.get());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String user = authentication.getName();
            var wislist = wishlists.hasBook(user, intId);
            model.addAttribute("wishlistAdded", wislist);
        }
        return "book";
    }

}

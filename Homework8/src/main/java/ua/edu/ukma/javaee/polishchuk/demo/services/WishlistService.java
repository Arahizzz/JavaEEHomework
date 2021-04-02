package ua.edu.ukma.javaee.polishchuk.demo.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ukma.javaee.polishchuk.demo.models.Book;
import ua.edu.ukma.javaee.polishchuk.demo.models.User;
import ua.edu.ukma.javaee.polishchuk.demo.models.Wishlist;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.BookJPARepository;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.UserRepository;
import ua.edu.ukma.javaee.polishchuk.demo.repositories.WishlistRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WishlistService {
    private final WishlistRepository wishlists;
    private final BookJPARepository books;
    private final UserRepository users;


    @Transactional
    public void initWishlist(User user){
        var wishlist = new Wishlist();
        wishlist.setUserId(user.getId());
        wishlist.setBooks(List.of());
        wishlists.save(wishlist);
    }

    @Transactional
    public boolean hasBook(String user, Integer bookId){
        var userId = users.findByLogin(user).get().getId();
        return wishlists.existsByLoginAndBook_Id(userId, bookId);
    }

    @Transactional
    public void addBookToWishlist(String user, Integer bookId){
        var userId = users.findByLogin(user).get().getId();
        var wishlist = wishlists.findById(userId).get();
        var book = books.findById(bookId).get();
        wishlist.getBooks().add(book);
        wishlists.save(wishlist);
    }

    @Transactional
    public void removeFromWishlist(String user, Integer bookId){
        var userId = users.findByLogin(user).get().getId();
        var wishlist = wishlists.findById(userId).get();
        var book = wishlist.getBooks().stream().filter(b -> b.getId().equals(bookId)).findFirst().get();
        wishlist.getBooks().remove(book);
        wishlists.save(wishlist);
    }

    @Transactional
    public List<Book> getWishlistBooks(String user){
        var userId = users.findByLogin(user).get().getId();
        return wishlists.getWishlistBooks(userId);
    }
}

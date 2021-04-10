package ua.edu.ukma.javaee.polishchuk.homework9.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.edu.ukma.javaee.polishchuk.homework9.models.Permission;
import ua.edu.ukma.javaee.polishchuk.homework9.models.RegisterForm;
import ua.edu.ukma.javaee.polishchuk.homework9.models.User;
import ua.edu.ukma.javaee.polishchuk.homework9.repositories.PermissionRepository;
import ua.edu.ukma.javaee.polishchuk.homework9.repositories.UserRepository;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository repository;
    private final MyPasswordEncoder encoder;
    private final PermissionRepository permissions;
    private final WishlistService wishlists;

    @Transactional
    public void registerUser(RegisterForm user) {
        var entity = new User();
        entity.setLogin(user.getLogin());
        entity.setPassword(encoder.encode(user.getPassword()));
        var perm = permissions.findByPermission(Permission.WISHLIST).get();
        entity.setPermissions(List.of(perm));
        repository.save(entity);
        wishlists.initWishlist(entity);
    }
}

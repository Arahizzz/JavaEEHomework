package ua.edu.ukma.javaee.polishchuk.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ua.edu.ukma.javaee.polishchuk.demo.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT user FROM User user " + "LEFT JOIN FETCH user.permissions " + "WHERE user.login = :login")
    Optional<User> findByLogin(@Param("login") String login);
}

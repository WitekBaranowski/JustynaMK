package pl.digitaldream.justynamk.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.digitaldream.justynamk.domain.User;

import java.util.List;

/**
 * Spring Data JPA repository for the User entity.
 */
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.activationKey = ?1")
    User getUserByActivationKey(String activationKey);

    List<User> findByLastName(String lastName);
}

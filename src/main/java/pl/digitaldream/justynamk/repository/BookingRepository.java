package pl.digitaldream.justynamk.repository;




import org.springframework.data.jpa.repository.JpaRepository;
import pl.digitaldream.justynamk.domain.Booking;


/**
 * Spring Data JPA repository for the User entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}

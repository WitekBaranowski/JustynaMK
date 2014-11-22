package pl.digitaldream.justynamk.repository;




import org.joda.time.DateTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.digitaldream.justynamk.domain.Booking;

import java.util.List;


/**
 * Spring Data JPA repository for the User entity.
 */
public interface BookingRepository extends JpaRepository<Booking, Integer> {
    @Query("select b from Booking b where b.reservationStart >= ?1 and b.reservationEnd <= ?2")
    public List<Booking> findByDates(DateTime fromDate, DateTime toDate);
}

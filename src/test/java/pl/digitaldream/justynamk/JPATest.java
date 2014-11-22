package pl.digitaldream.justynamk;


import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import org.springframework.transaction.annotation.Transactional;
import pl.digitaldream.justynamk.domain.Booking;
import pl.digitaldream.justynamk.domain.User;
import pl.digitaldream.justynamk.domain.enums.ReservationStatus;
import pl.digitaldream.justynamk.repository.BookingRepository;
import pl.digitaldream.justynamk.repository.UserRepository;


import java.util.List;

import static org.assertj.core.api.Assertions.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("in-memory")
@Transactional
public class JPATest {

    @Autowired
    UserRepository repository;

    @Autowired
    BookingRepository bookingRepository;

    @Test
    public void sampleTestCase() {
        User dave = new User();
        dave.setLogin("daveo");
        dave.setFirstName("dave");
        dave.setLastName("justynamk");

        dave = repository.save(dave);

        List<User> result = repository.findByLastName("justynamk");
        assertThat(result).hasSize(1);
        assertThat(result).contains(dave);
    }
    @Test
    public void bookingTest() {
        Booking b1 = new Booking();
        b1.setEmail("test@tes2.pl");
        b1.setPhone("663-495-345");
        b1.setReservationEnd(new DateTime());
        b1.setReservationStart(new DateTime().minusDays(5));
        b1.setStatus(ReservationStatus.PENDING);

        Booking b2 = new Booking();
        b2.setEmail("test2@tes2.pl");
        b2.setPhone("663-495-34545");
        b2.setReservationEnd(new DateTime());
        b2.setReservationStart(new DateTime().minusDays(2));
        b2.setStatus(ReservationStatus.CONFIRMED);

        bookingRepository.save(b1);

        bookingRepository.save(b2);


    }
}

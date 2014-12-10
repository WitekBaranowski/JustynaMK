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
    public void testSaveNewUser() {
        User user = new User();
        user.setLogin("justynamk");
        user.setFirstName("Justyna");
        user.setLastName("Nowak");


        user = repository.save(user);
        List<User> result = repository.findByLogin("justynamk");


        assertThat(result).hasSize(1);
        assertThat(result).contains(user);
    }
    @Test
    public void testSavePendingBooking() {
        Booking booking = prepareBasicBooking();
        booking.setStatus(ReservationStatus.PENDING);

        booking = bookingRepository.save(booking);
        assertThat(bookingRepository.exists(booking.getId()));


    }
    @Test
    public void testUpdateToConfirmedBooking() {

        Booking booking = prepareBasicBooking();
        booking.setStatus(ReservationStatus.PENDING);
        booking = bookingRepository.save(booking);

        booking.setStatus(ReservationStatus.CONFIRMED);
        bookingRepository.save(booking);
        booking = bookingRepository.getOne(booking.getId());

        assertThat(booking.getStatus()).isEqualTo(ReservationStatus.CONFIRMED);


    }

    private Booking prepareBasicBooking() {
        Booking booking = new Booking();
        booking.setId(1);
        booking.setEmail("test@tes2.pl");
        booking.setPhone("663-495-345");
        booking.setReservationEnd(new DateTime());
        booking.setReservationStart(new DateTime().minusDays(5));
        return booking;
    }

}

package pl.digitaldream.justynamk.service.impl;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.digitaldream.justynamk.domain.Booking;
import pl.digitaldream.justynamk.dto.BookingDTO;
import pl.digitaldream.justynamk.mappers.BookingMapper;
import pl.digitaldream.justynamk.repository.BookingRepository;
import pl.digitaldream.justynamk.service.BookingService;

import java.util.List;

/**
 * Created by Witek on 2014-11-16.
 */
@Service
@Transactional
public class StandardBookingService implements BookingService{

    private BookingRepository repository;

    private BookingMapper mapper;

    @Autowired
    public void setRepository(BookingRepository repository) {
        this.repository = repository;
    }

    @Autowired
    public void setMapper(BookingMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<BookingDTO> findAll() {
        List<Booking> bookings = repository.findAll();
        return mapper.mapToDTO(bookings);
    }

    @Override
    public List<BookingDTO> findByDates(DateTime fromDate, DateTime toDate) {
        List<Booking> bookings = repository.findByDates(fromDate, toDate);
        return mapper.mapToDTO(bookings);
    }

    @Override
    public BookingDTO findOne(Integer id) {
        return null;
    }

    @Override
    public BookingDTO save(BookingDTO bookingDTO) {
        Booking booking = repository.save(mapper.mapToEntity(bookingDTO));
        return mapper.mapToDTO(booking);
    }

    @Override
    public void delete(Integer id) {

    }

}

package pl.digitaldream.justynamk.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.digitaldream.justynamk.domain.Booking;
import pl.digitaldream.justynamk.domain.enums.ReservationStatus;
import pl.digitaldream.justynamk.dto.BookingDTO;
import pl.digitaldream.justynamk.repository.BookingRepository;
import pl.digitaldream.justynamk.service.BookingService;

import javax.validation.Valid;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.*;


@RestController
@RequestMapping(value = "/booking", produces = APPLICATION_JSON_VALUE)
public class BookingController {
    private final Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingService bookingService;

    @RequestMapping(method = GET)
    public ResponseEntity<List<BookingDTO>> findAll() {
        return new ResponseEntity<>(bookingService.findAll(), OK);
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<BookingDTO> get(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(bookingService.findOne(id), OK);
    }


    @RequestMapping(method = POST)
    public ResponseEntity<BookingDTO> create(@RequestBody @Valid BookingDTO booking) {
        booking.setStatus(ReservationStatus.PENDING);
        booking = bookingService.save(booking);
        return new ResponseEntity<>(booking, CREATED);
    }

    @RequestMapping(method = DELETE, value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        bookingService.delete(id);
        return new ResponseEntity<>(String.format("%s has been deleted", id), OK);
    }

}
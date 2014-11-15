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
import pl.digitaldream.justynamk.repository.BookingRepository;

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
    BookingRepository bookingRepository;

    @RequestMapping(method = GET)
    public ResponseEntity<List<Booking>> findAll() {
        return new ResponseEntity<>(bookingRepository.findAll(), OK);
    }

    @RequestMapping(method = GET, value = "/{id}")
    public ResponseEntity<Booking> get(@PathVariable(value = "id") Integer id) {
        return new ResponseEntity<>(bookingRepository.findOne(id), OK);
    }


    @RequestMapping(method = POST)
    public ResponseEntity<Booking> create(@RequestBody @Valid Booking booking) {
        booking = bookingRepository.save(booking);
        return new ResponseEntity<>(booking, CREATED);
    }

    @RequestMapping(method = DELETE, value = "{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id) {

        bookingRepository.delete(id);
        return new ResponseEntity<>(String.format("%s has been deleted", id), OK);
    }

}
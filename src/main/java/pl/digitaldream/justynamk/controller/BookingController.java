package pl.digitaldream.justynamk.controller;


import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import pl.digitaldream.justynamk.domain.enums.ReservationStatus;
import pl.digitaldream.justynamk.dto.BookingDTO;
import pl.digitaldream.justynamk.service.BookingService;
import pl.digitaldream.justynamk.web.propertyeditor.DateTimeEditor;

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

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(DateTime.class, new DateTimeEditor(false));
    }


    @RequestMapping(method = GET)
    public ResponseEntity<List<BookingDTO>> findAll() {
        return new ResponseEntity<>(bookingService.findAll(), OK);
    }
    @RequestMapping(method = GET, value = "/byDates")
    public ResponseEntity<List<BookingDTO>> findByDates(@RequestParam  DateTime fromDate,
                                                        @RequestParam  DateTime toDate) {
        return new ResponseEntity<>(bookingService.findByDates(fromDate, toDate), OK);
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
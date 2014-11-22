package pl.digitaldream.justynamk.service;


import org.joda.time.DateTime;
import pl.digitaldream.justynamk.dto.BookingDTO;

import java.util.List;

/**
 * Created by Witek on 2014-11-16.
 */

public interface BookingService {
    public List<BookingDTO> findAll();
    public List<BookingDTO> findByDates(DateTime fromDate, DateTime toDate);
    public BookingDTO findOne(Integer id);

    public BookingDTO save(BookingDTO booking);


    public void delete(Integer id);

}

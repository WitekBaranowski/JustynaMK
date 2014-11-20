package pl.digitaldream.justynamk.service;


import pl.digitaldream.justynamk.dto.BookingDTO;

import java.util.List;

/**
 * Created by Witek on 2014-11-16.
 */

public interface BookingService {
    public List<BookingDTO> findAll();
    public BookingDTO findOne(Integer id);

    public BookingDTO save(BookingDTO booking);


    public void delete(Integer id);

}

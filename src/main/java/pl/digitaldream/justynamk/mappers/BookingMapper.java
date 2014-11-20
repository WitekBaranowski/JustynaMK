package pl.digitaldream.justynamk.mappers;

import org.springframework.stereotype.Component;
import pl.digitaldream.justynamk.domain.Booking;
import pl.digitaldream.justynamk.dto.BookingDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Witek on 2014-11-16.
 */
@Component
public class BookingMapper {

    public BookingDTO mapToDTO(Booking source) {
        BookingDTO toReturn = new BookingDTO();
        toReturn.setTitle("zarezerwowane !");
        toReturn.setStart(source.getReservationStart());
        toReturn.setEnd(source.getReservationEnd());
        toReturn.setClassName(source.getStatus().toString());
        toReturn.setStatus(source.getStatus());
        toReturn.setId(source.getId());
        return toReturn;
    }

    public List<BookingDTO> mapToDTO(List<Booking> source) {
        List<BookingDTO> toReturn = new ArrayList<>(source.size());
        for (Booking booking : source) {
            toReturn.add(mapToDTO(booking));
        }
        return toReturn;
    }

    public Booking mapToEntity(BookingDTO bookingDTO) {
        Booking toReturn = new Booking();
        toReturn.setEmail(bookingDTO.getEmail());
        toReturn.setReservationStart(bookingDTO.getStart());
        toReturn.setReservationEnd(bookingDTO.getEnd());
        toReturn.setStatus(bookingDTO.getStatus());
        toReturn.setId(bookingDTO.getId());
        return toReturn;
    }

    public List<Booking> mapToEntity(List<BookingDTO> source) {
        List<Booking> toReturn = new ArrayList<>(source.size());
        for (BookingDTO booking : source) {
            toReturn.add(mapToEntity(booking));
        }
        return toReturn;
    }
}

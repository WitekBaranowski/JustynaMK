package pl.digitaldream.justynamk.domain;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.DateTime;
import pl.digitaldream.justynamk.domain.enums.ReservationStatus;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class Booking implements Serializable{


    private Integer id;

    private DateTime reservationStart;

    private DateTime reservationEnd;

    private String email;

    private String phone;

    private ReservationStatus status;



    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getReservationStart() {
        return reservationStart;
    }

    public void setReservationStart(DateTime reservationStart) {
        this.reservationStart = reservationStart;
    }

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    public DateTime getReservationEnd() {
        return reservationEnd;
    }

    public void setReservationEnd(DateTime reservationEnd) {
        this.reservationEnd = reservationEnd;
    }

    @NotNull
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Enumerated(EnumType.STRING)
    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }


}

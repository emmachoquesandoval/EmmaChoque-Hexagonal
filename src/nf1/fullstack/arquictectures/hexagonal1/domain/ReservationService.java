package nf1.fullstack.arquictectures.hexagonal1.domain;
/*
* ReservationService: tiene el métxdo reserve(User, Book), que crea la Reservation y la guarda
*  a través del puerto, y el métxdo getAllReservations(). Recibe ReservationRepository por el
*  constructor (inyección de dependencias) y nunca conoce qué adaptador se utiliza.*/
import java.util.ArrayList;
import java.util.List;

public class ReservationService {
    private final List<nf1.fullstack.arquictectures.hexagonal1.domain.Reservation> reservations = new ArrayList<>();

    public Reservation reserve(User user, Book book) {
        Reservation reservation = new Reservation(user, book);
        reservations.add(reservation);
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return new ArrayList<>(reservations);
    }
}

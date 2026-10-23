package nf1.fullstack.arquictectures.hexagonal1.domain;
/*
* ReservationService: tiene el métxdo reserve(User, Book), que crea la Reservation y la guarda
*  a través del puerto, y el métxdo getAllReservations(). Recibe ReservationRepository por el
*  constructor (inyección de dependencias) y nunca conoce qué adaptador se utiliza.*/
import java.util.List;
import nf1.fullstack.arquictectures.hexagonal1.application.ReservationRepository;

public class ReservationService {
    private final ReservationRepository repository;

    public ReservationService(ReservationRepository repository) {
        this.repository = repository;
    }

    public Reservation reserve(User user, Book book) {
        Reservation reservation = new Reservation(user, book);
        repository.save(reservation);
        return reservation;
    }

    public List<Reservation> getAllReservations() {
        return repository.findAll();
    }
}

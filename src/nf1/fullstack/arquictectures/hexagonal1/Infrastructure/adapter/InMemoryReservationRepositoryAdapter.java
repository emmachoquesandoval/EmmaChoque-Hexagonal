package nf1.fullstack.arquictectures.hexagonal1.Infrastructure.adapter;

import nf1.fullstack.arquictectures.hexagonal1.application.ReservationRepository;
import nf1.fullstack.arquictectures.hexagonal1.domain.Reservation;

import java.util.ArrayList;
import java.util.List;

public class InMemoryReservationRepositoryAdapter implements ReservationRepository {
    private final List<Reservation> reservations = new ArrayList<>();

    @Override
    public void save(Reservation reservation) {
        reservations.add(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return new ArrayList<>(reservations);
    }
}

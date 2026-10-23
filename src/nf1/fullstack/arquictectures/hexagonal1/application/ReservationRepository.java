package nf1.fullstack.arquictectures.hexagonal1.application;

import nf1.fullstack.arquictectures.hexagonal1.domain.Reservation;

import java.util.List;

public interface ReservationRepository {
    public void save(Reservation reservation);
    public List<Reservation> findAll();
}

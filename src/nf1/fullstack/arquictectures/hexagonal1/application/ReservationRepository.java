package nf1.fullstack.arquictectures.hexagonal1.application;

import nf1.fullstack.arquictectures.hexagonal1.domain.Reservation;

public interface ReservationRepository {
    public void save(Reservation reservation);
    public void findAll();
}

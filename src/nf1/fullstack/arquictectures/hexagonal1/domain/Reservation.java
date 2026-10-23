package nf1.fullstack.arquictectures.hexagonal1.domain;

import nf1.fullstack.arquictectures.hexagonal1.application.ReservationRepository;

public class Reservation {
    private final User user;
    private final Book book;

    public Reservation(User user, Book book) {
        this.user = user;
        this.book = book;
    }

    public User getUser() {
        return user;
    }
    public Book getBook() {
        return book;
    }

    @Override
    public String toString() {
        return user.getUsername() + " - " + book.getTitle();
    }
}

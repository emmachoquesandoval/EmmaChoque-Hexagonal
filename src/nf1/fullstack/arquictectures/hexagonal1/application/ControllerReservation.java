package nf1.fullstack.arquictectures.hexagonal1.application;

import nf1.fullstack.arquictectures.hexagonal1.domain.Reservation;
import nf1.fullstack.arquictectures.hexagonal1.domain.ReservationService;
import nf1.fullstack.arquictectures.hexagonal1.domain.User;
import nf1.fullstack.arquictectures.hexagonal1.domain.Book;

import java.util.List;
import java.util.Scanner;

public class ControllerReservation {
    private final ReservationService service;
    private final Scanner scanner;

    public ControllerReservation(ReservationService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public String showMenuAndReadOption() {
        System.out.println("\n--- Reserva de libros ---");
        System.out.println("1. Nueva reserva");
        System.out.println("2. Lista de reservas");
        System.out.println("0. Salir");
        System.out.print("Elige una opción: ");
        return scanner.nextLine().trim();
    }

    public String askUserName() {
        System.out.print("Nombre del usuario: ");
        return scanner.nextLine().trim();
    }

    public String askBookTitle() {
        System.out.print("Título del libro: ");
        return scanner.nextLine().trim();
    }

    public void showConfirmation(Reservation reservation) {
        System.out.println("Reserva realizada: " + reservation.getUser().getUsername()
                + " ha reservado \"" + reservation.getBook().getTitle() + "\"");
    }

    public void showReservations(List<Reservation> reservations) {
        if (reservations.isEmpty()) {
            System.out.println("Todavía no hay reservas.");
            return;
        }

        System.out.println("\n--- Lista de reservas ---");
        for (Reservation reservation : reservations) {
            System.out.println(reservation);
        }
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    public void showGoodbye() {
        System.out.println("Hasta luego.");
    }

    public void run() {
        boolean running = true;
        while (running) {
            String option = showMenuAndReadOption();
            switch (option) {
                case "1" -> createReservation();
                case "2" -> showReservations(service.getAllReservations());
                case "0" -> {
                    showGoodbye();
                    running = false;
                }
                default -> showError("elige 1, 2 o 0.");
            }
        }
    }

    private void createReservation() {
        String username = askUserName();
        String title = askBookTitle();
        if (username.isBlank() || title.isBlank()) {
            showError("el nombre y el título no pueden estar vacíos.");
            return;
        }
        showConfirmation(service.reserve(new User(username), new Book(title)));
    }
}

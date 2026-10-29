package nf1.fullstack.arquictectures.hexagonal1.Infrastructure.adapter;

import nf1.fullstack.arquictectures.hexagonal1.application.ReservationRepository;
import nf1.fullstack.arquictectures.hexagonal1.domain.Book;
import nf1.fullstack.arquictectures.hexagonal1.domain.Reservation;
import nf1.fullstack.arquictectures.hexagonal1.domain.User;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class FileReservationRepositoryAdapter implements ReservationRepository {
    private final File file;

    public FileReservationRepositoryAdapter() {
        file = new File("reservas.txt");
    }

    @Override
    public void save(Reservation reservation) {
        try {
            FileWriter writer = new FileWriter(file, true);
            writer.write(reservation.getUser().getUsername() + "\t"
                    + reservation.getBook().getTitle() + "\n");
            writer.close();
        } catch (IOException e) {
            System.out.println("Error al guardar la reserva.");
        }
    }

    @Override
    public List<Reservation> findAll() {
        List<Reservation> reservations = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int separator = line.indexOf("\t");
                if (separator != -1) {
                    String username = line.substring(0, separator);
                    String bookTitle = line.substring(separator + 1);
                    reservations.add(new Reservation(new User(username), new Book(bookTitle)));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {}
        return reservations;
    }
}

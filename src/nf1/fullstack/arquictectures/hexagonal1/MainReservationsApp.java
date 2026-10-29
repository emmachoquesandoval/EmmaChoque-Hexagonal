package nf1.fullstack.arquictectures.hexagonal1;

import nf1.fullstack.arquictectures.hexagonal1.Infrastructure.adapter.FileReservationRepositoryAdapter;
import nf1.fullstack.arquictectures.hexagonal1.Infrastructure.adapter.InMemoryReservationRepositoryAdapter;
import nf1.fullstack.arquictectures.hexagonal1.application.ControllerReservation;
import nf1.fullstack.arquictectures.hexagonal1.application.ReservationRepository;
import nf1.fullstack.arquictectures.hexagonal1.domain.ReservationService;

import java.util.Scanner;

public class MainReservationsApp {
    public static void main(String[] args) {
        // Opcion con persistencia en fichero
        ReservationRepository repository = new FileReservationRepositoryAdapter();

        // Opcion de memoria pero sin persistencia
         //ReservationRepository repository = new InMemoryReservationRepositoryAdapter();

        ReservationService service = new ReservationService(repository);
        Scanner scanner = new Scanner(System.in);
        ControllerReservation controller = new ControllerReservation(service, scanner);
        controller.run();
    }
}

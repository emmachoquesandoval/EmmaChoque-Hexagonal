package nf1.fullstack.arquictectures.hexagonal1;

import nf1.fullstack.arquictectures.hexagonal1.Infrastructure.adapter.InMemoryReservationRepositoryAdapter;
import nf1.fullstack.arquictectures.hexagonal1.application.ControllerReservation;
import nf1.fullstack.arquictectures.hexagonal1.domain.ReservationService;

import java.util.Scanner;

public class MainReservationsApp {
    public static void main(String[] args) {
        InMemoryReservationRepositoryAdapter repository = new InMemoryReservationRepositoryAdapter();
        ReservationService service = new ReservationService(repository);
        Scanner scanner = new Scanner(System.in);
        ControllerReservation controller = new ControllerReservation(service, scanner);
        controller.run();
    }
}

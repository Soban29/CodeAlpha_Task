import java.util.Scanner;

public class HotelReservationSim{
    public static void main(String[] args) {
        BookingManager manager = new BookingManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Hotel Booking System ---");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. View Reservation");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Guest Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Room Category (Standard/Deluxe/Suite): ");
                    String cat = scanner.nextLine();
                    System.out.print("Number of nights: ");
                    int nights = scanner.nextInt();
                    scanner.nextLine();
                    manager.makeReservation(name, cat, nights);
                    break;
                case 2:
                    System.out.print("Reservation ID: ");
                    String cancelId = scanner.nextLine();
                    manager.cancelReservation(cancelId);
                    break;
                case 3:
                    System.out.print("Reservation ID: ");
                    String viewId = scanner.nextLine();
                    manager.viewReservation(viewId);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    return;
            }
        }
    }
}

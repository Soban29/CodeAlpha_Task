import java.util.Scanner;

public class HotelReservationSim {
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

                    double pricePerNight = getRoomPrice(cat);
                    if (pricePerNight == -1) {
                        System.out.println("Invalid room category.");
                        break;
                    }

                    double totalAmount = pricePerNight * nights;
                    System.out.println("Total Amount: ₹" + totalAmount);

                    // Payment simulation
                    System.out.println("Choose Payment Method:\n1. Credit Card\n2. UPI");
                    int payChoice = scanner.nextInt();
                    scanner.nextLine();

                    PaymentGateway payment;
                    if (payChoice == 1) {
                        payment = new CreditCardPayment();
                    } else {
                        payment = new UpiPayment();
                    }

                    boolean success = payment.processPayment(totalAmount);

                    if (success) {
                        System.out.println("✅ Payment Successful.");
                        manager.makeReservation(name, cat, nights);
                    } else {
                        System.out.println("❌ Payment Failed. Reservation cancelled.");
                    }
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

    // Helper method to assign prices per room type
    private static double getRoomPrice(String category) {
        switch (category.toLowerCase()) {
            case "standard":
                return 2000.0;
            case "deluxe":
                return 3000.0;
            case "suite":
                return 5000.0;
            default:
                return -1;
        }
    }
}


import java.io.*;
import java.util.*;

public class BookingManager {
    private Hotel hotel;
    private List<Reservation> reservations;

    public BookingManager() {
        hotel = new Hotel();
        reservations = loadReservations();
    }

    private List<Reservation> loadReservations() {
        List<Reservation> list = new ArrayList<>();
        File file = new File("reservations.txt");
        if (!file.exists()) return list;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                list.add(Reservation.fromString(line, hotel));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    private void saveReservations() {
        try (PrintWriter pw = new PrintWriter("reservations.txt")) {
            for (Reservation r : reservations) {
                pw.println(r.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Reservation makeReservation(String guestName, String category, int nights) {
        List<Room> available = hotel.searchAvailableRooms(category);
        if (available.isEmpty()) {
            System.out.println("No rooms available in " + category);
            return null;
        }

        Room room = available.get(0);
        hotel.bookRoom(room.roomNumber);
        Reservation res = new Reservation(guestName, room, nights);
        reservations.add(res);
        saveReservations();
        System.out.println("Booking successful. ID: " + res.id);
        return res;
    }

    public boolean cancelReservation(String reservationId) {
        Iterator<Reservation> it = reservations.iterator();
        while (it.hasNext()) {
            Reservation res = it.next();
            if (res.id.equals(reservationId)) {
                hotel.cancelRoom(res.room.roomNumber);
                it.remove();
                saveReservations();
                System.out.println("Reservation canceled.");
                return true;
            }
        }
        return false;
    }

    public void viewReservation(String reservationId) {
        for (Reservation r : reservations) {
            if (r.id.equals(reservationId)) {
                System.out.println("Reservation ID: " + r.id);
                System.out.println("Guest: " + r.guestName);
                System.out.println("Room: " + r.room.roomNumber + " (" + r.room.category + ")");
                System.out.println("Nights: " + r.nights);
                System.out.println("Total: $" + r.totalPrice);
                return;
            }
        }
        System.out.println("Reservation not found.");
    }
}

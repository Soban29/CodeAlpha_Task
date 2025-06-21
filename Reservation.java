import java.util.UUID;

public class Reservation {
    public String id;
    public String guestName;
    public Room room;
    public int nights;
    public double totalPrice;

    public Reservation(String guestName, Room room, int nights) {
        this.id = UUID.randomUUID().toString();
        this.guestName = guestName;
        this.room = room;
        this.nights = nights;
        this.totalPrice = room.price * nights;
    }

    public String toString() {
        return id + "," + guestName + "," + room.roomNumber + "," + room.category + "," +
                nights + "," + totalPrice;
    }

    public static Reservation fromString(String line, Hotel hotel) {
        String[] parts = line.split(",");
        int roomNumber = Integer.parseInt(parts[2]);
        Room room = null;
        for (Room r : hotel.rooms) {
            if (r.roomNumber == roomNumber) {
                room = r;
                break;
            }
        }
        Reservation res = new Reservation(parts[1], room, Integer.parseInt(parts[4]));
        res.id = parts[0];
        res.totalPrice = Double.parseDouble(parts[5]);
        return res;
    }
}

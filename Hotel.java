import java.io.*;
import java.util.*;

public class Hotel {
    public List<Room> rooms;

    public Hotel() {
        this.rooms = loadRooms();
    }

    private List<Room> loadRooms() {
        List<Room> rooms = new ArrayList<>();
        File file = new File("rooms.txt");
        if (!file.exists()) {
            // Create default rooms
            for (int i = 101; i <= 105; i++)
                rooms.add(new Room(i, "Standard", 100));
            for (int i = 201; i <= 203; i++)
                rooms.add(new Room(i, "Deluxe", 150));
            for (int i = 301; i <= 302; i++)
                rooms.add(new Room(i, "Suite", 250));
            saveRooms(rooms);
        } else {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null) {
                    rooms.add(Room.fromString(line));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return rooms;
    }

    public void saveRooms(List<Room> rooms) {
        try (PrintWriter pw = new PrintWriter("rooms.txt")) {
            for (Room room : rooms) {
                pw.println(room.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Room> searchAvailableRooms(String category) {
        List<Room> available = new ArrayList<>();
        for (Room room : rooms) {
            if (room.category.equalsIgnoreCase(category) && room.available)
                available.add(room);
        }
        return available;
    }

    public Room bookRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber && room.available) {
                room.available = false;
                saveRooms(rooms);
                return room;
            }
        }
        return null;
    }

    public boolean cancelRoom(int roomNumber) {
        for (Room room : rooms) {
            if (room.roomNumber == roomNumber) {
                room.available = true;
                saveRooms(rooms);
                return true;
            }
        }
        return false;
    }
}


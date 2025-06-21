public class Room {
    public int roomNumber;
    public String category;
    public double price;
    public boolean available;

    public Room(int roomNumber, String category, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.price = price;
        this.available = true;
    }

    public String toString() {
        return roomNumber + "," + category + "," + price + "," + available;
    }

    public static Room fromString(String line) {
        String[] parts = line.split(",");
        Room room = new Room(Integer.parseInt(parts[0]), parts[1], Double.parseDouble(parts[2]));
        room.available = Boolean.parseBoolean(parts[3]);
        return room;
    }
}

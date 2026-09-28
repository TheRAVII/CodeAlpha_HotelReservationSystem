
import java.util.*;
import java.io.*;

class Room {
    int roomNo;
    String category;
    double price;

    Room(int roomNo, String category, double price) {
        this.roomNo = roomNo;
        this.category = category;
        this.price = price;
    }
}

class Booking implements Serializable {
    private static final long serialVersionUID = 1L;

    int bookingId;
    int roomNo;
    String guestName;
    String category;
    int nights;
    double totalAmount;

    Booking(int bookingId, int roomNo, String guestName,
            String category, int nights, double totalAmount) {
        this.bookingId = bookingId;
        this.roomNo = roomNo;
        this.guestName = guestName;
        this.category = category;
        this.nights = nights;
        this.totalAmount = totalAmount;
    }

    void display() {
        System.out.println("\nBooking ID: " + bookingId);
        System.out.println("Guest Name: " + guestName);
        System.out.println("Room Number: " + roomNo);
        System.out.println("Category: " + category);
        System.out.println("Nights: " + nights);
        System.out.println("Total Amount: Rs. " + totalAmount);
    }
}

public class HotelReservationSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<Room> rooms = new ArrayList<>();
    static ArrayList<Booking> bookings = new ArrayList<>();
    static int nextBookingId = 1001;
    static final String FILE_NAME = "hotel_bookings.dat";

    static void initializeRooms() {
        for (int i = 101; i <= 105; i++) {
            rooms.add(new Room(i, "Standard", 1500));
        }

        for (int i = 201; i <= 204; i++) {
            rooms.add(new Room(i, "Deluxe", 2500));
        }

        for (int i = 301; i <= 303; i++) {
            rooms.add(new Room(i, "Suite", 4000));
        }
    }

    @SuppressWarnings("unchecked")
    static void loadBookings() {
        File file = new File(FILE_NAME);

        if (!file.exists()) return;

        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream(file))) {
            bookings = (ArrayList<Booking>) in.readObject();

            for (Booking b : bookings) {
                if (b.bookingId >= nextBookingId) {
                    nextBookingId = b.bookingId + 1;
                }
            }
            System.out.println("Previous bookings loaded.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Could not load previous bookings.");
        }
    }

    static void saveBookings() {
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            out.writeObject(bookings);
        } catch (IOException e) {
            System.out.println("Error saving bookings: " + e.getMessage());
        }
    }

    static boolean isBooked(int roomNo) {
        for (Booking b : bookings) {
            if (b.roomNo == roomNo) {
                return true;
            }
        }
        return false;
    }

    static void showRooms() {
        System.out.println("\n===== ROOM AVAILABILITY =====");

        for (Room r : rooms) {
            String status = isBooked(r.roomNo)
                    ? "Booked" : "Available";

            System.out.println("Room: " + r.roomNo
                    + " | " + r.category
                    + " | Rs. " + r.price + "/night"
                    + " | " + status);
        }
    }

    static void searchRooms() {
        System.out.println("\n1. Standard - Rs.1500/night");
        System.out.println("2. Deluxe   - Rs.2500/night");
        System.out.println("3. Suite    - Rs.4000/night");
        System.out.print("Select category: ");
        int choice = sc.nextInt();

        String category;
        switch (choice) {
            case 1:
                category = "Standard";
                break;
            case 2:
                category = "Deluxe";
                break;
            case 3:
                category = "Suite";
                break;
            default:
                System.out.println("Invalid category!");
                return;
        }

        System.out.println("\nAvailable " + category + " rooms:");

        boolean found = false;
        for (Room r : rooms) {
            if (r.category.equals(category) && !isBooked(r.roomNo)) {
                System.out.println("Room " + r.roomNo
                        + " | Rs. " + r.price + "/night");
                found = true;
            }
        }

        if (!found) {
            System.out.println("No rooms available.");
        }
    }

    static void bookRoom() {
        System.out.print("Enter guest name: ");
        sc.nextLine();
        String name = sc.nextLine();

        System.out.println("\nSelect category:");
        System.out.println("1. Standard");
        System.out.println("2. Deluxe");
        System.out.println("3. Suite");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();

        String category;
        switch (choice) {
            case 1:
                category = "Standard";
                break;
            case 2:
                category = "Deluxe";
                break;
            case 3:
                category = "Suite";
                break;
            default:
                System.out.println("Invalid category!");
                return;
        }

        ArrayList<Room> available = new ArrayList<>();

        for (Room r : rooms) {
            if (r.category.equals(category) && !isBooked(r.roomNo)) {
                available.add(r);
            }
        }

        if (available.isEmpty()) {
            System.out.println("No rooms available.");
            return;
        }

        System.out.println("\nAvailable rooms:");
        for (Room r : available) {
            System.out.println(r.roomNo + " - Rs. "
                    + r.price + "/night");
        }

        System.out.print("Enter room number: ");
        int roomNo = sc.nextInt();

        Room selected = null;
        for (Room r : available) {
            if (r.roomNo == roomNo) {
                selected = r;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Invalid or unavailable room!");
            return;
        }

        System.out.print("Enter number of nights: ");
        int nights = sc.nextInt();

        if (nights <= 0) {
            System.out.println("Invalid number of nights!");
            return;
        }

        double total = selected.price * nights;

        System.out.println("\n===== BOOKING SUMMARY =====");
        System.out.println("Guest: " + name);
        System.out.println("Room: " + roomNo);
        System.out.println("Category: " + category);
        System.out.println("Nights: " + nights);
        System.out.println("Total: Rs. " + total);

        System.out.print("Proceed with simulated payment? (1=Yes, 0=No): ");
        int payment = sc.nextInt();

        if (payment != 1) {
            System.out.println("Booking cancelled.");
            return;
        }

        Booking booking = new Booking(
                nextBookingId++, roomNo, name,
                category, nights, total
        );

        bookings.add(booking);
        saveBookings();

        System.out.println("\nPayment successful (simulation).");
        System.out.println("Room booked successfully!");
        booking.display();
    }

    static void viewBookings() {
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
            return;
        }

        System.out.println("\n===== ALL BOOKINGS =====");
        for (Booking b : bookings) {
            b.display();
        }
    }

    static void searchBooking() {
        System.out.print("Enter booking ID: ");
        int id = sc.nextInt();

        for (Booking b : bookings) {
            if (b.bookingId == id) {
                b.display();
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    static void cancelBooking() {
        System.out.print("Enter booking ID to cancel: ");
        int id = sc.nextInt();

        for (Booking b : bookings) {
            if (b.bookingId == id) {
                bookings.remove(b);
                saveBookings();
                System.out.println("Booking cancelled successfully.");
                System.out.println("Simulated payment refund is not processed.");
                return;
            }
        }

        System.out.println("Booking not found.");
    }

    public static void main(String[] args) {
        initializeRooms();
        loadBookings();

        while (true) {
            System.out.println("\n===== HOTEL RESERVATION SYSTEM =====");
            System.out.println("1. Show All Rooms");
            System.out.println("2. Search Available Rooms");
            System.out.println("3. Book Room");
            System.out.println("4. View All Bookings");
            System.out.println("5. Search Booking");
            System.out.println("6. Cancel Booking");
            System.out.println("7. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    showRooms();
                    break;
                case 2:
                    searchRooms();
                    break;
                case 3:
                    bookRoom();
                    break;
                case 4:
                    viewBookings();
                    break;
                case 5:
                    searchBooking();
                    break;
                case 6:
                    cancelBooking();
                    break;
                case 7:
                    saveBookings();
                    System.out.println("Thank you!");
                    return;
                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
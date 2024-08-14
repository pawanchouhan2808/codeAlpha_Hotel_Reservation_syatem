package vscode;

import java.util.ArrayList;
import java.util.Scanner;

class Room {
    int roomNumber;
    String category;
    boolean isAvailable;
    double price;

    Room(int roomNumber, String category, boolean isAvailable, double price) {
        this.roomNumber = roomNumber;
        this.category = category;
        this.isAvailable = isAvailable;
        this.price = price;
    }
}

class Reservation {
    String guestName;
    Room room;
    int numberOfNights;
    double totalCost;

    Reservation(String guestName, Room room, int numberOfNights) {
        this.guestName = guestName;
        this.room = room;
        this.numberOfNights = numberOfNights;
        this.totalCost = room.price * numberOfNights;
    }
}

public class HotelReservationSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<Reservation> reservations = new ArrayList<>();

        // Sample rooms
        rooms.add(new Room(1, "Single", true, 1000.0));
        rooms.add(new Room(2, "Double", true, 1500.0));
        rooms.add(new Room(3, "Suite", true, 3000.0));

        while (true) {
            System.out.println("1. Search for available rooms");
            System.out.println("2. Make a reservation");
            System.out.println("3. View booking details");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Available rooms:");
                    for (Room room : rooms) {
                        if (room.isAvailable) {
                            System.out.println("Room Number: " + room.roomNumber + ", Category: " + room.category + ", Price per night: $" + room.price);
                        }
                    }
                    break;
                case 2:
                    System.out.print("Enter your name: ");
                    scanner.nextLine(); // Consume newline
                    String guestName = scanner.nextLine();
                    System.out.print("Enter room number to reserve: ");
                    int roomNumber = scanner.nextInt();
                    System.out.print("Enter number of nights: ");
                    int numberOfNights = scanner.nextInt();

                    Room selectedRoom = null;
                    for (Room room : rooms) {
                        if (room.roomNumber == roomNumber && room.isAvailable) {
                            selectedRoom = room;
                            break;
                        }
                    }

                    if (selectedRoom != null) {
                        Reservation reservation = new Reservation(guestName, selectedRoom, numberOfNights);
                        reservations.add(reservation);
                        selectedRoom.isAvailable = false;
                        System.out.println("Reservation successful! Total cost: $" + reservation.totalCost);
                    } else {
                        System.out.println("Room not available or invalid room number.");
                    }
                    break;
                case 3:
                    System.out.println("Booking details:");
                    for (Reservation reservation : reservations) {
                        System.out.println("Guest Name: " + reservation.guestName + ", Room Number: " + reservation.room.roomNumber + ", Category: " + reservation.room.category + ", Number of Nights: " + reservation.numberOfNights + ", Total Cost: $" + reservation.totalCost);
                    }
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}


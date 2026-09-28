# Hotel Reservation System

A Java-based console application developed as part of the CodeAlpha Java Programming Internship. This project allows users to search for available hotel rooms, make reservations, manage bookings, and simulate payments.

## Features

* Room Management (Standard, Deluxe, Suite)
* Search Available Rooms
* Room Booking
* Cancel Reservations
* Simulated Payment
* View All Bookings
* Search Booking by ID
* Room Availability Management
* Save and Load Booking Data
* File Handling using Object Serialization

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* ArrayList
* File Handling
* Object Serialization
* Scanner
* IntelliJ IDEA

## Room Categories

| Room Type | Room Numbers | Price per Night |
| --------- | ------------ | --------------- |
| Standard  | 101–105      | ₹1,500          |
| Deluxe    | 201–204      | ₹2,500          |
| Suite     | 301–303      | ₹4,000          |

## Project Structure

```text
HotelReservationSystem/
│
├── src/
│   └── HotelReservationSystem.java
│
├── hotel_bookings.dat
│
└── README.md
```

## How to Run

### Prerequisites

* Java JDK 8 or above
* IntelliJ IDEA or any Java IDE

### Steps

1. Clone or download this repository.
2. Open the project in IntelliJ IDEA.
3. Open `HotelReservationSystem.java`.
4. Run the `main()` method.
5. Use the console menu to manage hotel reservations.

## Application Menu

```text
===== HOTEL RESERVATION SYSTEM =====
1. Show All Rooms
2. Search Available Rooms
3. Book Room
4. View All Bookings
5. Search Booking
6. Cancel Booking
7. Exit

Enter choice:
```

## Features Description

* Show All Rooms: Display all hotel rooms with their categories, prices, and availability.
* Search Available Rooms: Find available rooms based on room category.
* Book Room: Enter guest details, select a room, specify the number of nights, and confirm the booking.
* View All Bookings: Display all current reservations.
* Search Booking: Find booking details using a booking ID.
* Cancel Booking: Cancel an existing reservation and make the room available again.
* Payment Simulation: Simulate the payment process without processing real money.

## Data Storage

The application uses Java Object Serialization to save booking records in a local file named `hotel_bookings.dat`. Previously saved bookings are loaded when the program starts.

## Learning Outcomes

* Understanding Java OOP concepts
* Working with Classes and Objects
* Using ArrayList to manage booking records
* Implementing File Handling
* Understanding Object Serialization
* Developing a menu-driven console application
* Applying problem-solving skills

## Future Improvements

* MySQL Database Integration
* Graphical User Interface (GUI)
* User Login and Registration
* Check-in and Check-out Dates
* Real Payment Gateway Integration
* Booking History and Reports

## Developer

**Ravi Maurya**

Java Developer | Aspiring Backend Developer

## Internship

CodeAlpha – Java Programming Internship

## License

This project was developed for educational and internship purposes.

---

Thank you for visiting my project!

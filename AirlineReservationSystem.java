import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AirlineReservationSystem {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "password";
    private static List<Registration> registrations = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

public static void main(String[] args) {
    while (true) {
        System.out.println("Airline Reservation System");
        System.out.println("--------------------------");
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Admin Login");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                register();
                break;
            case 2:
                login();
                break;
            case 3:
                adminLogin();
                break;
            case 4:
                System.out.println("Thank you for using the Airline Reservation System. Goodbye!");
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }

        System.out.println();
    }
}

private static void register() {
    System.out.println("Registration");
    System.out.println("------------");

    System.out.print("First Name: ");
    String firstName = scanner.nextLine();

    System.out.print("Last Name: ");
    String lastName = scanner.nextLine();

    System.out.print("Date of Birth: ");
    String dateOfBirth = scanner.nextLine();

    System.out.print("Country of Birth: ");
    String countryOfBirth = scanner.nextLine();

    System.out.print("Phone Number: ");
    String phoneNumber = scanner.nextLine();

    System.out.print("Email: ");
    String email = scanner.nextLine();

    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    Registration registration = new Registration(firstName, lastName, dateOfBirth, countryOfBirth,
            phoneNumber, email, username, password);
    registrations.add(registration);

    System.out.println("Registration successful! You can now login with your username and password.");
}

private static void login() {
    System.out.println("Login");
    System.out.println("-----");

    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    boolean loggedIn = false;

    for (Registration registration : registrations) {
        if (registration.getUsername().equals(username) && registration.getPassword().equals(password)) {
            loggedIn = true;
            userOptions(username);
            break;
        }
    }

    if (!loggedIn) {
        System.out.println("Invalid username or password. Login failed.");
    }
}

private static void userOptions(String username) {
    while (true) {
        System.out.println("User Options");
        System.out.println("------------");
        System.out.println("1. Book a Ticket");
        System.out.println("2. Cancel a Ticket");
        System.out.println("3. Change Flight Date");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                bookTicket(username);
                break;
            case 2:
                cancelTicket(username);
                break;
            case 3:
                changeDate(username);
                break;
            case 4:
                System.out.println("Logged out successfully.");
                System.out.println();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}

private static void bookTicket(String username) {
    System.out.println("Book a Ticket");
    System.out.println("-------------");

    System.out.print("Airline: ");
    String airline = scanner.nextLine();

    System.out.print("Source: ");
    String source = scanner.nextLine();

    System.out.print("Destination: ");
    String destination = scanner.nextLine();

    System.out.print("Flight Date: ");
    String flightDate = scanner.nextLine();

    System.out.print("Flight Time: ");
    String flightTime = scanner.nextLine();

    System.out.print("Number of Passengers: ");
    int numberOfPassengers = scanner.nextInt();
    scanner.nextLine(); // Consume the newline character

    System.out.print("Class (First Class/Business Class/Economy Class): ");
    String travelClass = scanner.nextLine();

    double basePrice;

    switch (travelClass) {
        case "First Class":
            basePrice = 500.0;
            break;
        case "Business Class":
            basePrice = 300.0;
            break;
        case "Economy Class":
            basePrice = 100.0;
            break;
        default:
            System.out.println("Invalid travel class. Booking failed.");
            return;
    }

    double totalPrice = basePrice * numberOfPassengers;

    System.out.println("Total Price: RM " + totalPrice);

    System.out.print("Confirm Booking (yes/no): ");
    String confirm = scanner.nextLine();

    if (confirm.equalsIgnoreCase("yes")) {
        Registration registration = new Registration(username, airline, source, destination,
                flightDate, flightTime, numberOfPassengers, travelClass, basePrice, totalPrice);
        registrations.add(registration);

        System.out.println("Ticket booked successfully! Thank you for choosing " + airline + ".");
        System.out.println("Your Ticket Details:");
        System.out.println("--------------------");
        System.out.println("Passenger Name: " + registration.getFirstName() + " " + registration.getLastName());
        System.out.println("Airline: " + registration.getAirline());
        System.out.println("Source: " + registration.getSource());
        System.out.println("Destination: " + registration.getDestination());
        System.out.println("Flight Date: " + registration.getFlightDate());
        System.out.println("Flight Time: " + registration.getFlightTime());
        System.out.println("Flight Number: " + generateFlightNumber());
        System.out.println("Class: " + registration.getTravelClass());
        System.out.println("Price per Passenger: RM " + registration.getBasePrice());
        System.out.println("Total Price: RM " + registration.getTotalPrice());
    } else {
        System.out.println("Booking canceled.");
    }
}

private static String generateFlightNumber() {
    // Generate a random 6-digit flight number
    int min = 100000;
    int max = 999999;
    int flightNumber = min + (int) (Math.random() * (max - min + 1));
    return String.valueOf(flightNumber);
}

private static void cancelTicket(String username) {
    System.out.println("Cancel a Ticket");
    System.out.println("---------------");

    System.out.print("Enter the flight number: ");
    String flightNumber = scanner.nextLine();

    boolean ticketCancelled = false;

    for (int i = 0; i < registrations.size(); i++) {
        Registration registration = registrations.get(i);
        if (registration.getUsername().equals(username) && registration.getFlightNumber().equals(flightNumber)) {
            System.out.println("Ticket Details:");
            System.out.println("---------------");
            System.out.println("Passenger Name: " + registration.getFirstName() + " " + registration.getLastName());
            System.out.println("Airline: " + registration.getAirline());
            System.out.println("Source: " + registration.getSource());
            System.out.println("Destination: " + registration.getDestination());
            System.out.println("Flight Date: " + registration.getFlightDate());
            System.out.println("Flight Time: " + registration.getFlightTime());
            System.out.println("Flight Number: " + registration.getFlightNumber());
            System.out.println("Class: " + registration.getTravelClass());
            System.out.println("Price per Passenger: RM " + registration.getBasePrice());
            System.out.println("Total Price: RM " + registration.getTotalPrice());

            System.out.print("Are you sure you want to cancel this flight? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                registrations.remove(i);
                ticketCancelled = true;
                break;
            } else {
                System.out.println("Flight cancellation canceled.");
                return;
            }
        }
    }

    if (ticketCancelled) {
        System.out.println("Flight cancelled successfully.");
    } else {
        System.out.println("No ticket found with the given flight number.");
    }

    System.out.println();
}

private static void changeDate(String username) {
    System.out.println("Change Flight Date");
    System.out.println("------------------");

    System.out.print("Enter the flight number: ");
    String flightNumber = scanner.nextLine();

    boolean dateChanged = false;

    for (Registration registration : registrations) {
        if (registration.getUsername().equals(username) && registration.getFlightNumber().equals(flightNumber)) {
            System.out.println("Current Flight Date: " + registration.getFlightDate());
            System.out.print("Enter the new flight date: ");
            String newDate = scanner.nextLine();
            registration.setFlightDate(newDate);
            dateChanged = true;
            break;
        }
    }

    if (dateChanged) {
        System.out.println("Flight date changed successfully.");
    } else {
        System.out.println("No ticket found with the given flight number.");
    }

    System.out.println();
}

private static void adminLogin() {
    System.out.println("Admin Login");
    System.out.println("-----------");

    System.out.print("Username: ");
    String username = scanner.nextLine();

    System.out.print("Password: ");
    String password = scanner.nextLine();

    if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
        adminOptions();
    } else {
        System.out.println("Invalid admin credentials. Login failed.");
    }
}

private static void adminOptions() {
    while (true) {
        System.out.println("Admin Options");
        System.out.println("-------------");
        System.out.println("1. View Registrations");
        System.out.println("2. View Flight Details");
        System.out.println("3. Cancel a Flight");
        System.out.println("4. Logout");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume the newline character

        switch (choice) {
            case 1:
                viewRegistrations();
                break;
            case 2:
                viewFlightDetails();
                break;
            case 3:
                cancelFlight();
                break;
            case 4:
                System.out.println("Logged out successfully.");
                System.out.println();
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}

private static void viewRegistrations() {
    System.out.println("Registration Details");
    System.out.println("--------------------");

    if (registrations.isEmpty()) {
        System.out.println("No registrations found.");
    } else {
        for (Registration registration : registrations) {
            System.out.println("Username: " + registration.getUsername());
            System.out.println("First Name: " + registration.getFirstName());
            System.out.println("Last Name: " + registration.getLastName());
            System.out.println("Date of Birth: " + registration.getDateOfBirth());
            System.out.println("Country of Birth: " + registration.getCountryOfBirth());
            System.out.println("Phone Number: " + registration.getPhoneNumber());
            System.out.println("Email: " + registration.getEmail());
            System.out.println();
        }
    }

    System.out.println();
}

private static void viewFlightDetails() {
    System.out.println("Flight Details");
    System.out.println("--------------");

    if (registrations.isEmpty()) {
        System.out.println("No flight details found.");
    } else {
        for (Registration registration : registrations) {
            System.out.println("Airline: " + registration.getAirline());
            System.out.println("Source: " + registration.getSource());
            System.out.println("Destination: " + registration.getDestination());
            System.out.println("Flight Date: " + registration.getFlightDate());
            System.out.println("Flight Time: " + registration.getFlightTime());
            System.out.println("Flight Number: " + registration.getFlightNumber());
            System.out.println("Class: " + registration.getTravelClass());
            System.out.println("Price per Passenger: RM " + registration.getBasePrice());
            System.out.println("Total Price: RM " + registration.getTotalPrice());
            System.out.println();
        }
    }

    System.out.println();
}

private static void cancelFlight() {
    System.out.println("Cancel a Flight");
    System.out.println("---------------");

    System.out.print("Enter the flight number: ");
    String flightNumber = scanner.nextLine();

    boolean flightCancelled = false;

    for (int i = 0; i < registrations.size(); i++) {
        Registration registration = registrations.get(i);
        if (registration.getFlightNumber().equals(flightNumber)) {
            System.out.println("Flight Details:");
            System.out.println("---------------");
            System.out.println("Airline: " + registration.getAirline());
            System.out.println("Source: " + registration.getSource());
            System.out.println("Destination: " + registration.getDestination());
            System.out.println("Flight Date: " + registration.getFlightDate());
            System.out.println("Flight Time: " + registration.getFlightTime());
            System.out.println("Flight Number: " + registration.getFlightNumber());
            System.out.println("Class: " + registration.getTravelClass());
            System.out.println("Price per Passenger: RM " + registration.getBasePrice());
            System.out.println("Total Price: RM " + registration.getTotalPrice());

            System.out.print("Are you sure you want to cancel this flight? (yes/no): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("yes")) {
                registrations.remove(i);
                flightCancelled = true;
                break;
            } else {
                System.out.println("Flight cancellation canceled.");
                return;
            }
        }
    }

    if (flightCancelled) {
        System.out.println("Flight cancelled successfully.");
    } else {
        System.out.println("No flight found with the given flight number.");
    }

    System.out.println();
}
}
    class Registration {
        private String firstName;
private String lastName;
private String dateOfBirth;
private String countryOfBirth;
private String phoneNumber;
private String email;
private String username;
private String password;
private String airline;
private String source;
private String destination;
private String flightDate;
private String flightTime;
private String flightNumber;
private int numberOfPassengers;
private String travelClass;
private double basePrice;
private double totalPrice;
public Registration(String firstName, String lastName, String dateOfBirth, String countryOfBirth,
                    String phoneNumber, String email, String username, String password) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.countryOfBirth = countryOfBirth;
    this.phoneNumber = phoneNumber;
    this.email = email;
    this.username = username;
    this.password = password;
}

public Registration(String username, String airline, String source, String destination, String flightDate,
                    String flightTime, int numberOfPassengers, String travelClass, double basePrice, double totalPrice) {
    this.username = username;
    this.airline = airline;
    this.source = source;
    this.destination = destination;
    this.flightDate = flightDate;
    this.flightTime = flightTime;
    this.numberOfPassengers = numberOfPassengers;
    this.travelClass = travelClass;
    this.basePrice = basePrice;
    this.totalPrice = totalPrice;
    this.flightNumber = generateFlightNumber();
}

public String getFirstName() {
    return firstName;
}

public String getLastName() {
    return lastName;
}

public String getDateOfBirth() {
    return dateOfBirth;
}

public String getCountryOfBirth() {
    return countryOfBirth;
}

public String getPhoneNumber() {
    return phoneNumber;
}

public String getEmail() {
    return email;
}

public String getUsername() {
    return username;
}

public String getPassword() {
    return password;
}

public String getAirline() {
    return airline;
}

public String getSource() {
    return source;
}

public String getDestination() {
    return destination;
}

public String getFlightDate() {
    return flightDate;
}

public void setFlightDate(String flightDate) {
    this.flightDate = flightDate;
}

public String getFlightTime() {
    return flightTime;
}

public String getFlightNumber() {
    return flightNumber;
}

public int getNumberOfPassengers() {
    return numberOfPassengers;
}

public String getTravelClass() {
    return travelClass;
}

public double getBasePrice() {
    return basePrice;
}

public double getTotalPrice() {
    return totalPrice;
}

private static String generateFlightNumber() {
    int min = 100000;
    int max = 999999;
    int flightNumber = min + (int) (Math.random() * (max - min + 1));
    return String.valueOf(flightNumber);
}
}
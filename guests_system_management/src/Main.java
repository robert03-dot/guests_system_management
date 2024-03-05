import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Guest> registeredGuests = new ArrayList<>();
        List<Guest> waitingList = new ArrayList<>();

        boolean running = true;

        while (running) {
            System.out.println("Enter a command (help for list of commands):");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "help":
                    displayHelp();
                    break;
                case "add":
                    addGuest(scanner, registeredGuests);
                    break;
                case "check":
                    checkGuest(scanner, registeredGuests, waitingList);
                    break;
                case "remove":
                    removeGuest(scanner, registeredGuests);
                    break;
                case "update":
                    updateGuest(scanner, registeredGuests);
                    break;
                case "guests":
                    displayGuests(registeredGuests);
                    break;
                case "waitlist":
                    displayGuests(waitingList);
                    break;
                case "available":
                    displayAvailableSeats(registeredGuests);
                    break;
                case "guests_no":
                    displayNumberOfGuests(registeredGuests);
                    break;
                case "waitlist_no":
                    displayNumberOfGuests(waitingList);
                    break;
                case "subscribe_no":
                    displaySubscriptionNumber(scanner, registeredGuests);
                    break;
                case "search":
                    searchGuest(scanner, registeredGuests, waitingList);
                    break;
                case "quit":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid command. Type 'help' for list of commands.");
            }
        }
        scanner.close();
    }

    private static void displayHelp() {
        System.out.println("List of commands:");
        System.out.println("help - Display list of commands");
        System.out.println("add - Add a guest");
        System.out.println("check - Check registration status of a guest");
        System.out.println("remove - Remove a guest");
        System.out.println("update - Update details of a guest");
        System.out.println("guests - Display registered guests");
        System.out.println("waitlist - Display guests on waiting list");
        System.out.println("available - Display available seats");
        System.out.println("guests_no - Display number of registered guests");
        System.out.println("waitlist_no - Display number of guests on waiting list");
        System.out.println("subscribe_no - Subscribe to get notified when a seat is available");
        System.out.println("search - Search for a guest");
        System.out.println("quit - Quit the program");
    }

    private static void addGuest(Scanner scanner, List<Guest> registeredGuests) {
        System.out.println("Enter guest details (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            registeredGuests.add(guest);
            System.out.println("Guest added successfully.");
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }

    private static void checkGuest(Scanner scanner, List<Guest> registeredGuests, List<Guest> waitingList) {
        System.out.println("Enter guest details to check (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            int registrationStatus = guest.getRegistrationStatus(registeredGuests, waitingList);
            if (registrationStatus == -1) {
                System.out.println("Guest is registered.");
            } else if (registrationStatus == 0) {
                System.out.println("Guest is on waiting list.");
            } else {
                System.out.println("Guest not found.");
            }
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }

    private static void removeGuest(Scanner scanner, List<Guest> registeredGuests) {
        System.out.println("Enter guest details to remove (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            if (registeredGuests.remove(guest)) {
                System.out.println("Guest removed successfully.");
            } else {
                System.out.println("Guest not found in registered list.");
            }
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }

    private static void updateGuest(Scanner scanner, List<Guest> registeredGuests) {
        System.out.println("Enter guest details to update (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            System.out.println("Enter new details (Leave blank for no change):");
            String[] newDetails = scanner.nextLine().split(", ");
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            for (Guest g : registeredGuests) {
                if (g.equals(guest)) {
                    if (!newDetails[0].isEmpty()) {
                        g.setLast_name(newDetails[0]);
                    }
                    if (!newDetails[1].isEmpty()) {
                        g.setFirst_name(newDetails[1]);
                    }
                    if (!newDetails[2].isEmpty()) {
                        g.setEmail(newDetails[2]);
                    }
                    if (!newDetails[3].isEmpty()) {
                        g.setPhone_number(newDetails[3]);
                    }
                    System.out.println("Guest details updated successfully.");
                    return;
                }
            }
            System.out.println("Guest not found in registered list.");
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }

    private static void displayGuests(List<Guest> guests) {
        for (Guest guest : guests) {
            System.out.println(guest.toString());
        }
    }

    private static void displayAvailableSeats(List<Guest> registeredGuests) {
        int totalSeats = 100; // Assuming total seats available
        int occupiedSeats = registeredGuests.size();
        System.out.println("Available seats: " + (totalSeats - occupiedSeats));
    }

    private static void displayNumberOfGuests(List<Guest> guests) {
        System.out.println("Number of guests: " + guests.size());
    }

    private static void displaySubscriptionNumber(Scanner scanner, List<Guest> registeredGuests) {
        System.out.println("Enter your details to subscribe (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            if (!registeredGuests.contains(guest)) {
                System.out.println("You have been added to the waiting list.");
            } else {
                System.out.println("You are already registered.");
            }
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }

    private static void searchGuest(Scanner scanner, List<Guest> registeredGuests, List<Guest> waitingList) {
        System.out.println("Enter guest details to search (Last Name, First Name, Email, Phone Number):");
        String[] details = scanner.nextLine().split(", ");
        if (details.length == 4) {
            Guest guest = new Guest(details[0], details[1], details[2], details[3]);
            if (registeredGuests.contains(guest)) {
                System.out.println("Guest found in registered list.");
            } else if (waitingList.contains(guest)) {
                System.out.println("Guest found in waiting list.");
            } else {
                System.out.println("Guest not found.");
            }
        } else {
            System.out.println("Invalid input. Please provide all details (Last Name, First Name, Email, Phone Number).");
        }
    }
}
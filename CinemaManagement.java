import java.util.Scanner;

public class CinemaManagement {
    private static final int ROWS = 3;
    private static final int SEATS_PER_ROW = 16;
    private static int[][] seats = new int[ROWS][SEATS_PER_ROW];
    private static int[] prices = {12, 10, 8}; // Prices for each row
    private static Ticket[] ticketsSold = new Ticket[ROWS * SEATS_PER_ROW]; // Array to store all tickets sold
    private static int ticketCount = 0; // Counter for tickets sold

    public static void main(String[] args) {
        // Initialize all seats to 0 (available)
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                seats[i][j] = 0;
            }
        }

        Scanner scanner = new Scanner(System.in);
        int option = 0;

        // Display the menu and ask the user to select an option
        while (option != 8) {
            displayMenu();
            System.out.print("Select an option: ");

            // Validate input
            if (scanner.hasNextInt()) {
                option = scanner.nextInt();
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 8.");
                scanner.next(); // Clear the invalid input
                continue;
            }

            // Handle the selected option
            switch (option) {
                case 1:
                    // Option 1: Buy a ticket
                    buy_ticket();
                    break;
                case 2:
                    // Option 2: Cancel a ticket
                    cancel_ticket();
                    break;
                case 3:
                    // Option 3: Display available seats
                    print_seating_area();
                    break;
                case 4:
                    // Option 4: Find the first available seat
                    find_first_available();
                    break;
                case 5:
                    // Option 5: Print tickets information and total price
                    print_tickets_info();
                    break;
                case 6:
                    // Option 6: Search ticket
                    search_ticket();
                    break;
                case 7:
                    // Option 7: Sort tickets by price (ascending order)
                    sort_tickets();
                    break;
                case 8:
                    // Option 8: Exit the program
                    System.out.println("Exiting the program. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option. Please select a number between 1 and 8.");
                    break;
            }
        }

        scanner.close();
    }

    // Method to display the menu
    public static void displayMenu() {
        System.out.println("\nMenu:");
        System.out.println("1. Buy a ticket");
        System.out.println("2. Cancel ticket");
        System.out.println("3. Find first seat available");
        System.out.println("4. Find the first available seat");
        System.out.println("5. Print tickets information and total price");
        System.out.println("6. Search ticket");
        System.out.println("7. Sort ticket by price");
        System.out.println("8. Exit");
    }

    // Method to buy a ticket
    public static void buy_ticket() {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int seat = -1;
        String name, surname, email;

        // Prompt for a valid row number
        while (true) {
            System.out.print("Enter row number (1-" + ROWS + "): ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (row >= 1 && row <= ROWS) {
                    break;
                } else {
                    System.out.println("Invalid row number. Please enter a number between 1 and " + ROWS + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + ROWS + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Prompt for a valid seat number
        while (true) {
            System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
            if (scanner.hasNextInt()) {
                seat = scanner.nextInt();
                if (seat >= 1 && seat <= SEATS_PER_ROW) {
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Check if the seat is available
        if (seats[row - 1][seat - 1] == 0) {
            System.out.print("Enter customer's name: ");
            name = scanner.next();
            System.out.print("Enter customer's surname: ");
            surname = scanner.next();
            System.out.print("Enter customer's email: ");
            email = scanner.next();

            // Create a Person object
            Person customer = new Person(name, surname, email);

            // Create a Ticket object
            Ticket ticket = new Ticket(row, seat, prices[row - 1], customer);

            // Add ticket to the array of tickets sold
            ticketsSold[ticketCount++] = ticket;

            // Book the seat and associate it with the person
            seats[row - 1][seat - 1] = 1;
            System.out.println("The seat has been booked. Ticket information:");
            ticket.printTicketInfo();
        } else {
            System.out.println("This seat is not available.");
        }
    }

    // Method to cancel a ticket
    public static void cancel_ticket() {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int seat = -1;

        // Prompt for a valid row number
        while (true) {
            System.out.print("Enter row number (1-" + ROWS + "): ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (row >= 1 && row <= ROWS) {
                    break;
                } else {
                    System.out.println("Invalid row number. Please enter a number between 1 and " + ROWS + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + ROWS + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Prompt for a valid seat number
        while (true) {
            System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
            if (scanner.hasNextInt()) {
                seat = scanner.nextInt();
                if (seat >= 1 && seat <= SEATS_PER_ROW) {
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Check if the seat is booked
        if (seats[row - 1][seat - 1] == 1) {
            // Cancel the booking
            seats[row - 1][seat - 1] = 0;

            // Find the corresponding ticket in the array and remove it
            for (int i = 0; i < ticketCount; i++) {
                Ticket ticket = ticketsSold[i];
                if (ticket.getRow() == row && ticket.getSeat() == seat) {
                    // Shift all subsequent tickets to fill the gap
                    for (int j = i; j < ticketCount - 1; j++) {
                        ticketsSold[j] = ticketsSold[j + 1];
                    }
                    ticketsSold[--ticketCount] = null; // Remove the last reference
                    System.out.println("The seat booking has been cancelled.");
                    return;
                }
            }
        } else {
            System.out.println("This seat is already available.");
        }
    }

    // Method to print the seating area
    public static void print_seating_area() {
        System.out.println();
        System.out.println("**********************");
        System.out.println("*       SCREEN       *");
        System.out.println("**********************");

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (j == SEATS_PER_ROW / 2) {
                    System.out.print(" ");
                }
                if (seats[i][j] == 0) {
                    System.out.print("O");
                } else {
                    System.out.print("X");
                }
            }
            System.out.println();
        }
    }

    // Method to find the first available seat
    public static void find_first_available() {
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < SEATS_PER_ROW; j++) {
                if (seats[i][j] == 0) {
                    System.out.println("First available seat: Row " + (i + 1) + ", Seat " + (j + 1));
                    return;
                }
            }
        }
        System.out.println("No available seats found.");
    }

    // Method to print tickets info and calculate total price
    public static void print_tickets_info() {
        if (ticketCount == 0) {
            System.out.println("No tickets have been sold.");
            return;
        }

        System.out.println("\nTickets Sold During the Session:");
        System.out.println("------------------------------");

        int totalSales = 0;
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = ticketsSold[i];
            System.out.println("Ticket " + (i + 1) + ":");
            ticket.printTicketInfo();
            totalSales += ticket.getPrice();
            System.out.println("------------------------------");
        }

        System.out.println("Total Sales: £" + totalSales);
    }

    // Method to search for a ticket
    public static void search_ticket() {
        Scanner scanner = new Scanner(System.in);
        int row = -1;
        int seat = -1;

        // Prompt for a valid row number
        while (true) {
            System.out.print("Enter row number (1-" + ROWS + "): ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (row >= 1 && row <= ROWS) {
                    break;
                } else {
                    System.out.println("Invalid row number. Please enter a number between 1 and " + ROWS + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + ROWS + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Prompt for a valid seat number
        while (true) {
            System.out.print("Enter seat number (1-" + SEATS_PER_ROW + "): ");
            if (scanner.hasNextInt()) {
                seat = scanner.nextInt();
                if (seat >= 1 && seat <= SEATS_PER_ROW) {
                    break;
                } else {
                    System.out.println("Invalid seat number. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and " + SEATS_PER_ROW + ".");
                scanner.next(); // Clear the invalid input
            }
        }

        // Check if the seat is booked
        if (seats[row - 1][seat - 1] == 1) {
            // Search for the ticket
            for (int i = 0; i < ticketCount; i++) {
                Ticket ticket = ticketsSold[i];
                if (ticket.getRow() == row && ticket.getSeat() == seat) {
                    ticket.printTicketInfo();
                    return;
                }
            }
        } else {
            System.out.println("This seat is available.");
        }
    }

    // Method to sort tickets by price using a simple linear sorting algorithm
    public static void sort_tickets() {
        // Implementing bubble sort to sort tickets by price (ascending order)
        for (int i = 0; i < ticketCount - 1; i++) {
            for (int j = 0; j < ticketCount - i - 1; j++) {
                if (ticketsSold[j].getPrice() > ticketsSold[j + 1].getPrice()) {
                    // Swap tickets
                    Ticket temp = ticketsSold[j];
                    ticketsSold[j] = ticketsSold[j + 1];
                    ticketsSold[j + 1] = temp;
                }
            }
        }

        // Printing sorted tickets information
        System.out.println("\nTickets Sorted by Price (Ascending Order):");
        System.out.println("------------------------------------------");
        for (int i = 0; i < ticketCount; i++) {
            Ticket ticket = ticketsSold[i];
            System.out.println("Ticket " + (i + 1) + ":");
            ticket.printTicketInfo();
            System.out.println("------------------------------------------");
        }
    }
}

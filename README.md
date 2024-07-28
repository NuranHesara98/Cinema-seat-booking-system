## Cinema Management System

### Description
The Cinema Management Program is a Java application designed for managing cinema seat bookings. It allows users to buy and cancel tickets, search for tickets, view seating plans, and sort ticket information by price.

### Features
- **Buy a Ticket**: Reserve a seat and generate a ticket.
- **Cancel a Ticket**: Cancel a booked seat and remove the ticket from records.
- **Display Available Seats**: View the seating plan with available and booked seats.
- **Find the First Available Seat**: Locate the first unoccupied seat.
- **Print Ticket Information and Total Price**: Display all sold tickets and calculate the total sales.
- **Search for a Ticket**: Find a ticket based on seat number.
- **Sort Tickets by Price**: Arrange tickets in ascending order by price.

### How to Use
1. **Compile and Run**: Compile the `CinemaManagement.java` file and run the program.
   ```sh
   javac CinemaManagement.java
   java CinemaManagement
   ```
2. **Select an Option**: Follow the on-screen prompts to choose an option from the menu.
3. **Input Details**: Enter the necessary details as prompted by the program.

### Classes Overview
- **CinemaManagement**: Main class that handles the user interface and core functionalities.
- **Person**: Class representing a customer with attributes for name, surname, and email.
- **Ticket**: Class representing a ticket with attributes for row, seat, price, and associated customer.

### Program Flow
1. **Initialization**: All seats are initialized to be available.
2. **Menu**: Users interact with the program through a menu that offers various options.
3. **Operations**: Based on the selected option, the program performs the corresponding action, such as booking or canceling a ticket, displaying seating information, etc.

### Example Usage
- To buy a ticket, select option 1 and follow the prompts to enter row number, seat number, and customer details.
- To cancel a ticket, select option 2 and enter the row and seat number of the ticket to be canceled.
- To display all available seats, select option 3.

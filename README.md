# Progetto ISPW

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=ProgettoISPW&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=ProgettoISPW)

**PrivateEvents** is an application developed for the Ingegneria del Software e Progettazione Web course (2023/24) at the University of Roma Tor Vergata, under the guidance of Professors Falessi and De Angelis. The application allows users to book tickets for private events, with the option to pay at the venue or online. Event organizers can configure single or multiple ticket types, including free tickets, and offer special perks such as access to reserved areas or exclusive gadgets.

## Implemented Use Case

- **Ticket Sales**: Developed the use case for selling tickets, including notifications to the seller.
- **User Registration and Login**: Vendors can register and log in to manage their events and ticket sales.
- **Online Payment**: Users can complete ticket purchases through an online payment system.

## Technical Implementation

### Programming and Design Principles

- **Object-Oriented Programming (OOP)**: The application is designed using OOP principles to ensure modularity and reusability.
- **UML and Software Engineering**: Utilizes UML diagrams (e.g., Use Case, Activity) for modeling and software engineering best practices for development.
- **Design Patterns**: Implements common design patterns to solve recurring design problems.

### Persistence and Data Management

- **File System**: The application can store data using the file system.
- **Relational Database**: Alternatively, the application can use a relational database management system (DBMS) to store and manage data, with SQL queries for data retrieval.

The choice between file system and relational database persistence can be configured by modifying the `PrivateEvents/src/main/resources/properties/start.properties` file. Set `DAO_TYPE` to either `JDBC` for relational database persistence or `FS` for file system persistence.

### User Interface

- **JavaFX**: The graphical user interface is designed using JavaFX, providing a rich and interactive user experience.
- **Command Line Interface (CLI)**: An alternative terminal-style interface is also available.

The interface type can be configured by setting `VIEW_TYPE` to either `FX` for a graphical interface or `CLI` for a terminal-style interface in the `start.properties` file.

## Directory Structure

- **`src/main/java/com/privateevents`**:
  - Contains the main Java source code for the application.
  - **`controller/`**: Manages the application's control logic, handling user inputs and updating the model.
  - **`model/`**: Represents the data model and business logic, encapsulating the core functionality of the application.
  - **`view/`**: Contains classes for the user interface, supporting both JavaFX and Command Line Interface (CLI) views.
  - **`Main.java`**: The entry point of the application, initializing and launching the program.

- **`src/main/resources/`**:
  - **`data/`**: Stores CSV files used for file persistence, ensuring data can be saved and retrieved across sessions.
  - **`logger/`**: Contains log files for tracking application activity and debugging purposes.
  - **`properties/`**: Holds configuration files for various settings.
    - **`start.properties`**: Configuration file for setting `DAO_TYPE` (Data Access Object type) and `VIEW_TYPE` (User Interface type).
    - **`db.properties`**: Configuration file for setting up the MYSQL database connection parameters.
    - **`logging.properties`**: Configuration file for setting up logging preferences and behaviors.

- **`Database/`**:
  - **`entry.sql`**: SQL script to populate the database with fictitious entries, useful for testing and development.
  - **`setup.sql`**: SQL script to build the database.
  - **`privateEvents.mwb`**: MySQL Workbench file to visualize and manage the database schema and data.

- **`Documentazione/`**:
  - **`RelazioneISPW.pdf`**: Detailed documentation report providing insights into the project's design, implementation, and usage.

## Setup Instructions

### Using IntelliJ IDEA and MySQL to run the project

1. **Clone the Repository**:
   ```bash
   git clone <repository-url>
   cd PrivateEvents
   ```

2. **Set Up MySQL Database**:
   - Ensure that MySQL is installed on your system. You can download it from the [official MySQL website](https://dev.mysql.com/downloads/).
   - Use the SQL script provided in `Database/setup.sql` to load the database locally.
   - Use the SQL script provided in `Database/entry.sql` to populate the tables with initial data.

3. **Configure the Application**:
   - Open the project in IntelliJ IDEA.
   - Modify the `start.properties` file located at `PrivateEvents/src/main/resources/properties/start.properties`:
     - Set `DAO_TYPE=JDBC` to use the relational database.
     - Set `VIEW_TYPE=FX` for a graphical interface or `CLI` for a terminal-style interface.
   - Configure the database connection settings in the same file:
     ```properties
     db.url=jdbc:mysql://localhost:3306/your_database_name
     db.user=your_database_user
     db.password=your_database_password
     ```

4. **Build and Run the Application**:
   - Use IntelliJ IDEA to build the project.
   - Run the application using the configured run settings.

For comprehensive technical specifications and development documentation, please refer to the detailed report in 'Documentazione/RelazioneISPW.pdf'.










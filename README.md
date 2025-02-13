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

### Requirements

- **Hardware**: A computer capable of running JAR files and sufficient disk space for storing personal information in the offline version.
- **Software**: Oracle JDK SE 21 must be correctly installed to run the JAR file. A server or local server is required to host the DBMS for the online version.

For comprehensive technical specifications and development documentation, please refer to the detailed report in 'Documentazione/RelazioneISPW.pdf'.










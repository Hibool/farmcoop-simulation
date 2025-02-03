# Agricultural Cooperative Simulation

This project simulates the operations of an agricultural cooperative, including producers, warehouse management, truck deliveries, and logistics.
It models the management of goods flow from producers to the warehouse and then to trucks delivering the goods.

## Prerequisites

To run this application, make sure you have the following installed:

- **Java 17** (the required version for this project)
- **Maven** (for building and running the project)

## Installation

1. Clone this repository to your local machine:

   ```bash
   git clone https://github.com/Hibool/agricultural-cooperative-simulation.git
   cd agricultural-cooperative-simulation
   ```

2. Ensure you are using Java 17. You can check your Java version by running:
   ```bash
   java -version
   ```
   If you are not using Java 17, you can install it by following the instructions for your operating system from the official Java website.

3. Make sure Maven is installed by running:
   ```bash
   mvn -v
   ```
   If Maven is not installed, you can download it from Maven's website.

## Building the project

To build the project and install all dependencies, run:
```bash
mvn spring-boot:run
```
This will start the Spring Boot application, and the simulation will begin running.

## Testing the Application

To run all tests for this application, use the following Maven command:
```bash
mvn test
```
This will execute all unit and integration tests, providing feedback on the success or failure of each test.

## Logs

Logs are managed using java.util.logging.Logger. You can find the logs in the console during runtime or in the directory /logs.
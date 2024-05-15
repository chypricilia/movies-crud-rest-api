# Spring Boot + MySQL: CRUD Example REST APIs

This project provides a CRUD (Create, Read, Update, Delete) API for managing movies using Spring Boot, Spring Web MVC for REST APIs, and Spring Data JPA for interacting with a MySQL database.

## Overview

These are the APIs that the Spring Boot App will export:

| Methods | Urls                              | Actions                                |
|---------|-----------------------------------|----------------------------------------|
| POST    | /api/movies                       | Create a new movie                     |
| GET     | /api/movies                       | Retrieve all movies                    |
| GET     | /api/movies/:id                   | Retrieve a movie by :id                |
| PUT     | /api/movies/:id                   | Update a movie by :id                  |
| DELETE  | /api/movies/:id                   | Delete a movie by :id                  |
| DELETE  | /api/movies                       | Delete all movies                      |
| GET     | /api/movies?title=[keyword]       | Find all movies whose title contains keyword |
| GET     | /api/movies/sorted                | Retrieve a sorted list of all movies   |

## Technology

- Java: 17 / 11 / 8
- Spring Boot: 3 (with Spring Web MVC, Spring Data JPA)
- MySQL
- Maven

## Project Structure

The project follows the standard Spring Boot project structure:

```
movies-crud-rest-api
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── movies
│   │   │               ├── controller      # REST controller classes
│   │   │               ├── model           # Entity classes representing database tables
│   │   │               ├── repository      # Spring Data JPA repositories
│   │   └── resources
│   │       ├── application.properties     # Application properties including database configuration
│   │       ├── static                      # Static resources (if any)
│   │       └── templates                   # HTML templates (if any)
│   └── test
│       └── java
│           └── com
│               └── example
│                   └── movies
│                       └── controller      # Unit and integration tests for controllers
└── pom.xml                                 # Maven build configuration
```

## Usage

1. Clone the repository:

   ```bash
   git clone https://github.com/chypricilia/spring-boot-mysql.git
   cd movies-crud-rest-api
   ```

2. Configure the MySQL database by editing the `application.properties` file in the `src/main/resources` directory.

3. Run the application using Maven:

   ```bash
   mvn spring-boot:run
   ```

4. Once the application is running, you can test the APIs using tools like Postman or cURL. You can access the application at:

   ```
   http://localhost:8080
   ```

## Customization

You can customize the application by adding more endpoints, modifying the existing ones, or adding additional functionality.

## Troubleshooting
- **Port Conflicts:** If the default port 8080 is in use, you can change it by modifying `src/main/resources/application.properties` and adding `server.port=<new_port_number>`.
- If you encounter any issues, ensure that you have configured the MySQL database correctly and that the database server is running.
- Make sure that the required dependencies are correctly configured in the `pom.xml` file.

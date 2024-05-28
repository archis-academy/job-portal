# About User and CommentMapper Classes

This project is a simple Gradle project using PostgreSQL database with Flyway migration. It contains two classes named User and CommentMapper. There is a one-to-many relationship between these classes, meaning a user can have multiple comment mappers.


## Database Migrations (Flyway)

The project uses Flyway for managing the database schema. Migration files create appropriate tables in the PostgreSQL database.

Migration files can be found in the `src/main/resources/db/migration` directory. These files contain SQL commands to create and update the database schema.

## Technologies Used

- Java
- Gradle
- Flyway
- PostgreSQL

## Setup

After cloning the project, create your PostgreSQL database and update the `application.yml` file with your database credentials. Then, you can start the application by running the following command in the project root directory:


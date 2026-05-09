# Todo Spring

A simple Spring Boot todo application built in Java 17.

## Overview

This project is a small Spring Boot service for managing todo items. It demonstrates a basic controller, service, and model structure with an in-memory todo list.

## Project Structure

- `todos/pom.xml` - Maven project configuration
- `todos/src/main/java/org/pragna/todos/TodoApp.java` - Spring Boot application entry point
- `todos/src/main/java/org/pragna/todos/controller/TodoController.java` - REST controller for todo endpoints
- `todos/src/main/java/org/pragna/todos/service/TodoService.java` - Service layer holding and managing todo data
- `todos/src/main/java/org/pragna/todos/model/Todo.java` - Todo model class

## Requirements

- Java 17
- Maven

## Run

From the repository root, start the application with:

```bash
cd todos
mvn spring-boot:run
```

The application will start on port `8080` by default.

## API Endpoints

- `GET /` - Retrieve the current list of todos
- `POST /add` - Add a todo item (endpoint currently stubbed)
- `PUT /update` - Update a todo item (endpoint currently stubbed)
- `DELETE /delete` - Delete a todo item (endpoint currently stubbed)

## Notes

- The service currently stores todo items in memory.
- `TodoService` initializes with sample todos when the list is empty.
- The `/add`, `/update`, and `/delete` controller methods are present but not yet implemented.

## Build

To build the project:

```bash
cd todos
mvn clean package
```

## License

No license specified.

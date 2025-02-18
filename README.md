# Book Management API

## Overview
The Book Management API is a RESTful service built using Spring Boot. It provides CRUD operations for managing books, including searching by title, author, and genre.

## Features
- Create, read, update, and delete books
- Search books by title, author, and genre
- Pagination support for listing books
- Validation for book attributes
- Exception handling for errors like duplicate ISBNs and book not found

## Technologies Used
- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL (or any other relational database)
- Lombok
- Hibernate
- Swagger for API documentation

## Installation and Setup
### Prerequisites
- Java 11 or later
- Maven
- MySQL database

### Steps to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/bettonhezron/Book-Management-API.git
   cd  book-management
   ```
2. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/bookdb
   spring.datasource.username=your_username
   spring.datasource.password=your_password
   ```
3. Build and run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
### Books
| Method | Endpoint                       | Description                     |
|--------|--------------------------------|---------------------------------|
| GET    | `/api/books`                   | Get all books (paginated)       |
| GET    | `/api/books/{id}`              | Get a book by ID                |
| POST   | `/api/books`                   | Create a new book               |
| PUT    | `/api/books/{id}`              | Update an existing book         |
| GET    | `/api/books/search`            | Search books by title or author |
| GET    | `/api/books/genre/{genre}`     | Get books by genre              |
| DELETE | `/api/books/{id}`              | Delete a book                   |


## Error Handling
| Error                | HTTP Status | Message |
|----------------------|------------|---------|
| Book Not Found       | 404 | `Book not found with id: {id}` |
| Duplicate ISBN       | 400 | `Book with ISBN {isbn} already exists` |
| Deleted successfully | 204 | `(No response body, operation successful)` |

## Running Tests
To run tests, use:
```sh
mvn test
```

## API Documentation
Swagger is enabled for API documentation. After running the application, access it at:
```
http://localhost:8080/swagger-ui.html
```

## License
This project is licensed under the MIT License.


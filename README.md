# Product API

## Description
This API provides two REST services:
1. Filter products by price range.
2. Sort products by price.

## Endpoints

### 1. Filter Products by Price

**Endpoint**: `GET /api/products/filter/price/{initial_range}/{final_range}`

**Parameters**:
- `initial_range`: Minimum price.
- `final_range`: Maximum price.

**Successful Response (200)**:
```json
[
  {
    "barcode": "74001755",
    "item": "Ball Gown",
    "category": "Full Body Outfits",
    "price": 3548,
    "discount": 7,
    "available": 1
  }
]
```
### 2. Sort Products by Price

**Endpoint**: `GET /api/products/sort/price`

**Successful Response (200)**:
```json
[
  {
     "Shawl",
     "Ball Gown"
  }
]
```
## Documentation

### Explanation of Solution

This solution provides an API with two endpoints to filter and sort products by price. The first endpoint allows filtering products by a specified price range and the second endpoint sorts products by price in ascending order.

A global exception handler is implemented to handle any invalid input, such as incorrect price ranges. This ensures that invalid requests are responded to with a `400 Bad Request` and a clear error message.

## Technical Overview

This project is built using a variety of modern tools and technologies, which ensure its maintainability, scalability, and robustness. The following is a list of the key technologies and features included:

### Technologies Used:

- **Spring Boot 3**: The project is built with Spring Boot 3, providing a rapid development environment and simplifying dependency management.
- **Docker**: The application is containerized using Docker, allowing for consistent environments across different stages of development and production.
- **OpenAPI**: The project uses OpenAPI 3 for API documentation, making it easier to generate, test, and maintain the API specification.
- **JUnit & Mockito**: Unit tests are written using JUnit 5 and Mockito, ensuring the functionality of components through unit testing and mocking of dependencies.
- **MockMvc**: MockMvc is used for integration testing of Spring MVC controllers, providing an easy way to test the HTTP layer without needing to deploy the application.
- **Liquibase**: Liquibase is integrated to manage database schema changes and migrations, ensuring consistent database versions across all environments.
- **MapStruct**: MapStruct is used for mapping between DTOs and domain objects, ensuring efficient and type-safe object mapping.
- **Logging**: The application uses SLF4J for logging, providing detailed logs that help track application behavior and diagnose issues.
- **Exception Handling**: A global exception handler is implemented to catch and manage different types of exceptions, ensuring a clean and consistent error response to clients.
- **Lombok**: Lombok is used to reduce boilerplate code by automatically generating getters, setters, constructors, and other commonly used methods in Java classes.


### Instructions to Run the Project

1. **Clone the repository and change to the project path**:
   ```bash
   git clone https://github.com/ricardo1021/api-rest.git
   cd api-rest
   ```
2. **Build the project**:
   ```bash
   mvn clean install
   ```
3. **Run the following command**:
   ```bash
   docker-compose up --build -d
   ```
4. **Swagger documentation**:
   ```bash
   http://localhost:5000/swagger-ui/index.html
   ```
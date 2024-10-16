# Prices API

## Overview

This project is a **RESTful API** service designed to provide product pricing information based on a set of conditions, such as the product ID, brand and application date. The service returns the applicable price depending on the input parameters. It is developed in **Java 21**, using **Spring Boot 3.3.4** and follows the **Hexagonal Architecture** pattern (Ports & Adapters).

### Features

- Provides product pricing information via a REST API.
- Supports filtering by product, brand, and date/time.
- Uses an embedded **H2** database for running and testing.
- Data is loaded from a **CSV** file via **Liquibase**.
- Includes **Swagger/OpenAPI** for API documentation.
- Implements **unit and integration tests** using **JUnit 5** and **Mockito**.
- Follows best practices for clean code, including SOLID principles.
  
---

## Table of Contents

- [Technologies](#technologies)
- [Setup](#setup)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [Testing](#testing)
- [Contact](#contact)

<div id='technologies' />
---

## Technologies

- **Java 21**
- **Spring Boot 3.3.4**
- **Maven 3.9.9**
- **H2 Database (Embedded)**
- **Liquibase** (for database migrations)
- **Swagger/OpenAPI** (for API documentation)
- **JUnit 5** and **Mockito** (for testing)
- **MapStruct** (for object mapping)
- **Lombok** (to reduce boilerplate code)

<div id='setup' />
---

## Setup

### Prerequisites

- **Java 21** installed on your system.
- **Maven 3.9.9** installed.
- **Git** for version control.

### Clone the repository

```bash
git clone https://github.com/yourusername/prices-api.git
cd prices-api
```

### Build the project

To build the project and download dependencies:

```bash
mvn clean install
```

<div id='running-the-application' />
---

## Running the Application

### Running in Development Mode

To run the application using Maven:

```bash
mvn spring-boot:run
```

### Access the H2 Console

Once the application is running, you can access the H2 database console at:

- URL: http://localhost:8080/h2-console
- JDBC URL: jdbc:h2:mem:pricesdb
- Username: sa
- Password: (leave empty)

### Swagger-UI

The API is documented using Swagger. You can access the Swagger UI in your browser by navigating to:

```bash
http://localhost:8080/swagger-ui/index.html
```

### OpenAPI Documentation

You can access to the OpenAPI documention (JSON format) in order to be used by some client (such as POSTMAN) or even generate a client by some specific generator

```bash
http://localhost:8080/v3/api-docs
```

<div id='api-endpoints' />
---

## API Endpoints

### GET /prices

This endpoint allows you to query the applicable price for a product based on the product ID, brand, and date/time.

- URL: /prices
- Method: GET
- Query Parameters:
    - productId: The product ID (Long)
    - brandId: The brand ID (Long)
    - applicationDate: The date and time for price application (yyyy-MM-ddTHH:mm:ss)
    
#### Example request
 
```bash
GET /prices?productId=35455&brandId=1&applicationDate=2020-06-14T10:00:00
```

#### Example response

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-06-14T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

#### Error Handling

- 404 Not Found: Returned when no price is applicable for the given criteria.
- 400 Bad Request: Returned when the input parameters are invalid (e.g., invalid date format).

<div id='testing' />
---

## Testing

The project includes comprehensive unit tests and integration tests to ensure code quality and correctness.

### Run the tests

You can run the unit tests and integration tests using:

```bash
mvn clean test
```

<div id='contact' />
---

## Contact

Please, for any doubt or suggestion contact:

- **Name:** Francisco Cilleruelo
- **e-mail:** [francisco.cilleruelo@gmail.com](mailto:francisco.cilleruelo@gmail.com)